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
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cl.gdl.ms_pedido.service.IMedioDePagoService;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test") // Usa el profile test
public class MedioDePagoTest {

    @Autowired
    IMedioDePagoService medioDePagoService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void createOneRow() throws Exception {  
    String requestBody = "{\"nombre\":\"arroz\"}";

    mockMvc.perform(MockMvcRequestBuilders.post("/api/medio-de-pago") 
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Buscar Medio de Pago por ID manual")
    void testBuscarMedioDePagoPorId() throws Exception {
    // UUID manual de un medio de pago que ya debe existir en la base de datos
    String idMedioDePago = "3eb98d4e-8a27-4b47-ac21-5cd17f5afc2e";

    mockMvc.perform(MockMvcRequestBuilders.get("/api/medio-de-pago/" + idMedioDePago)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idMedioDePago))
            .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").exists());

    System.out.println("Se buscó medio de pago con ID: " + idMedioDePago);
}

        // este test puede generar error si no el medio de pago esta asociado a un pedido 
        @Test
         void deleteOneRowFound() throws Exception {
              String idMedioDePago = "ef20b952-490b-427f-86f2-0272adfd20c7";
                mockMvc.perform(MockMvcRequestBuilders.delete("/api/medio-de-pago/" + idMedioDePago)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); 
    }


}