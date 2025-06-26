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

import cl.gdl.ms_pedido.service.IEntregaService;


@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test") // Usa el profile test
public class EntregaTest {

    @Autowired
    IEntregaService entregaService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void createOneRow() throws Exception {  
    String requestBody = "{\"entrega\":\"despachado\"}";

    mockMvc.perform(MockMvcRequestBuilders.post("/api/entrega/insert") 
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Buscar entrega por ID manual")
    void testEntregaGetbyId() throws Exception {
    // UUID manual que ya debe existir en la base de datos
    String idEntrega = "112f143e-699c-4711-81ee-4234732a47be";

    mockMvc.perform(MockMvcRequestBuilders.get("/api/entrega/" + idEntrega)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.idEntrega").value(idEntrega.toString()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.entrega").exists());

    System.out.println("Se buscó la entrega con ID: " + idEntrega);
    }

    @Test
         void deleteOneRowFound() throws Exception {
              String idEntrega = "112f143e-699c-4711-81ee-4234732a47be";
                mockMvc.perform(MockMvcRequestBuilders.delete("/api/entrega/delete/" + idEntrega)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); 
    }
}
