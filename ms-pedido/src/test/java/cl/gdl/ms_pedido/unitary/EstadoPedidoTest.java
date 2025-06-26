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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cl.gdl.ms_pedido.service.IEstadoPedidoService;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test") // Usa el profile test
public class EstadoPedidoTest {

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

    @Test
    @DisplayName("Buscar Estado Pedido por ID manual")
    void testEstadoPedidoGetbyId() throws Exception {
    // UUID manual que ya debe existir en la base de datos
    String idEstadoPedido = "48c444ed-91ed-48d8-8802-93fabdec5063";

    mockMvc.perform(MockMvcRequestBuilders.get("/api/estado-pedido/getById/" + idEstadoPedido)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.idEstadoPedido").value(idEstadoPedido.toString()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.nameEstadoPedido").exists());

    System.out.println("Se buscó el estado pedido con ID: " + idEstadoPedido);
    }

    @Test
         void deleteOneRowFound() throws Exception {
              String idEstadoPedido = "48c444ed-91ed-48d8-8802-93fabdec5063";
                mockMvc.perform(MockMvcRequestBuilders.delete("/api/estado-pedido/delete/" + idEstadoPedido)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); 
    }

}
