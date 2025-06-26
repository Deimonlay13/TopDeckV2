package cl.gdl.ms_pedido.unitary;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cl.gdl.ms_pedido.service.IEntregaService;
import org.springframework.transaction.annotation.Transactional;


@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("unit-test") // Usa el profile test
public class EntregaTest {
    @Autowired
    ObjectMapper objectMapper;
    
    @Autowired
    IEntregaService entregaService;

    @Autowired
    MockMvc mockMvc;

    @Transactional
    @Test
    void createOneRow() throws Exception {  
    String requestBody = "{\"entrega\":\"despachado\"}";

    mockMvc.perform(MockMvcRequestBuilders.post("/api/entrega/insert") 
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk());
    }

    
    @Transactional
    @Test
    @DisplayName("Buscar entrega por ID recién insertado")
    void testEntregaGetbyId() throws Exception {
        // 1. Inserta una entrega
        String requestBody = "{\"entrega\":\"entregado\"}";
    
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/entrega/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    
        // 2. Extrae el ID desde la respuesta
        JsonNode jsonNode = objectMapper.readTree(response);
        String idEntrega = jsonNode.get("idEntrega").asText();
    
        // 3. Ahora sí, llama al GET con el ID real
        mockMvc.perform(MockMvcRequestBuilders.get("/api/entrega/" + idEntrega)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idEntrega").value(idEntrega))
                .andExpect(MockMvcResultMatchers.jsonPath("$.entrega").value("entregado"));
    
        System.out.println("Se buscó la entrega con ID: " + idEntrega);
    }
    

    @Transactional
    @Test
    void deleteOneRowFound() throws Exception {
            // 1. Insertamos una entrega
            String requestBody = "{\"entrega\":\"por eliminar\"}";

            String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/entrega/insert")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

            // 2. Extraemos el ID de la respuesta
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response);
            String idEntrega = jsonNode.get("idEntrega").asText();

            // 3. Ejecutamos el DELETE
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/entrega/delete/" + idEntrega)
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk());
    }
    
    @Test
    void deleteEntregaNotFound() throws Exception {
            // Usamos un UUID aleatorio que no existe en la base H2
            String idInexistente = "00000000-0000-0000-0000-000000000000";

            mockMvc.perform(MockMvcRequestBuilders.delete("/api/entrega/delete/" + idInexistente)
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isNotFound()); // <--- esperamos que falle con 404
    }

}
