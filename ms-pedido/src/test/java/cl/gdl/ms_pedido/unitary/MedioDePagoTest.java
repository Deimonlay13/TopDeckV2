package cl.gdl.ms_pedido.unitary;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.gdl.ms_pedido.service.IMedioDePagoService;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("unit-test") // Usa el profile test
public class MedioDePagoTest {

    @Autowired
    IMedioDePagoService medioDePagoService;

    @Autowired
    MockMvc mockMvc;
    
    @Transactional
    @Test
    void createOneRow() throws Exception {  
    String requestBody = "{\"nombre\":\"arroz\"}";

    mockMvc.perform(MockMvcRequestBuilders.post("/api/medio-de-pago") 
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk());
        System.out.println("POST Response: " + requestBody);
            
    }

    @Test
    @Transactional
    @DisplayName("Buscar Medio de Pago por ID recién insertado")
    void testBuscarMedioDePagoPorId() throws Exception {
        // 1. Insertar un medio de pago
        String requestBody = "{\"nombre\":\"Tarjeta Crédito\"}";
    
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/medio-de-pago")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    
        // 2. Extraer el ID generado
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response);
        String idMedioDePago = jsonNode.get("id").asText();
    
        // 3. Hacer la búsqueda GET con el ID generado
        mockMvc.perform(MockMvcRequestBuilders.get("/api/medio-de-pago/" + idMedioDePago)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idMedioDePago))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Tarjeta Crédito"));
    
        System.out.println("Se buscó medio de pago con ID: " + idMedioDePago);
    }

    @Test
    @Transactional
    void deleteOneRowFound() throws Exception {
        // 1. Insertar medio de pago
        String requestBody = "{\"nombre\":\"Medio para borrar\"}";
    
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/medio-de-pago")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    
        // 2. Extraer el ID generado
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response);
        String idMedioDePago = jsonNode.get("id").asText();
    
        // 3. Borrar el medio de pago recién creado
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/medio-de-pago/" + idMedioDePago)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    
        // 4. validar que ya no exista (GET espera 404)
        mockMvc.perform(MockMvcRequestBuilders.get("/api/medio-de-pago/" + idMedioDePago)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

  
}