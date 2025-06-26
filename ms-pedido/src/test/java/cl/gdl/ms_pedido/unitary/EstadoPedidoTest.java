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
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cl.gdl.ms_pedido.service.IEstadoPedidoService;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("unit-test") // Usa el profile test
public class EstadoPedidoTest {
    @Autowired
    ObjectMapper objectMapper;
    
    @Autowired
    IEstadoPedidoService estadoPedidoService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void createOneRow() throws Exception {  
    String requestBody = "{\"nameEstadoPedido\":\"despachado\"}";

    mockMvc.perform(MockMvcRequestBuilders.post("/api/estado-pedido/insert") 
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk());
    }

    @Transactional
    @Test
    @DisplayName("Buscar Estado Pedido por ID recién insertado")
    void testEstadoPedidoGetbyId() throws Exception {
            // 1. Inserta un estado de pedido
            String requestBody = "{\"nameEstadoPedido\": \"pendiente\"}";

            String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/estado-pedido/insert")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

            // 2. Extrae el ID del estado insertado
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response);
            String idEstadoPedido = jsonNode.get("idEstadoPedido").asText();

            // 3. Ejecuta el GET con el ID real
            mockMvc.perform(MockMvcRequestBuilders.get("/api/estado-pedido/getById/" + idEstadoPedido)
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.idEstadoPedido").value(idEstadoPedido))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.nameEstadoPedido").value("pendiente"));

            System.out.println("Se buscó el estado pedido con ID: " + idEstadoPedido);
    }

    @Transactional
    @Test
    @DisplayName("Eliminar Estado Pedido recién insertado")
    void deleteOneRowFound() throws Exception {
            // 1. Insertamos un EstadoPedido
            String requestBody = "{\"nameEstadoPedido\": \"en reparto\"}";

            String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/estado-pedido/insert")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

            // 2. Extraemos el ID generado
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response);
            String idEstadoPedido = jsonNode.get("idEstadoPedido").asText();

            // 3. Eliminamos ese EstadoPedido
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/estado-pedido/delete/" + idEstadoPedido)
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk());
    }

}
