package cl.gdl.ms_pedido.integration;

import cl.gdl.ms_pedido.dto.DetallePedidoDTO;
import cl.gdl.ms_pedido.dto.DireccionDTO;
import cl.gdl.ms_pedido.dto.EntregaDTO;
import cl.gdl.ms_pedido.dto.EstadoPedidoDTO;
import cl.gdl.ms_pedido.dto.MedioDePagoDTO;
import cl.gdl.ms_pedido.dto.PedidoDTO;
import cl.gdl.ms_pedido.service.IPedidoService;

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

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test") // Usa el profile test
public class PedidoIntegrationTest {

  @Autowired
  private IPedidoService pedidoService;

  @Autowired
  MockMvc mockMvc;

  @Test
  void testInsertPedidoConDetalle() {
    PedidoDTO pedido = new PedidoDTO();
    pedido.setIdUsuario("833f220c-dee9-4223-bdd8-cd49146c0cd0");

    // Medio de pago
    MedioDePagoDTO medio = new MedioDePagoDTO();
    medio.setId(UUID.fromString("e599cada-932b-4f44-9e01-5a09afe37942"));
    pedido.setIdMedioDePago(medio);

    // Entrega
    EntregaDTO entrega = new EntregaDTO();
    entrega.setIdEntrega(UUID.fromString("02a244a3-a3a8-445f-b311-2c40c8b8d4df"));
    pedido.setIdEntrega(entrega);

    // Estado pedido
    EstadoPedidoDTO estado = new EstadoPedidoDTO();
    estado.setIdEstadoPedido(UUID.fromString("0ccd9100-cb4c-4373-817e-fc94cae2640f"));
    pedido.setIdEstadoPedido(estado);
    DetallePedidoDTO detalle = new DetallePedidoDTO();
    detalle.setIdProducto("sm3-4"); // Carta ID como String
    detalle.setCantidad(6);
    pedido.setDetalles(List.of(detalle));
    
    // Dirección
    DireccionDTO direccion = new DireccionDTO();
    direccion.setIdDireccion(UUID.fromString("351D6631-1BCA-4CA1-B662-07AD80560EEC"));
    pedido.setDireccion(direccion);
    PedidoDTO inserted = pedidoService.insert(pedido);

    assertNotNull(inserted.getIdPedido());
    assertEquals("833f220c-dee9-4223-bdd8-cd49146c0cd0", inserted.getIdUsuario());
    assertEquals(1, inserted.getDetalles().size());
    assertTrue(inserted.getTotal().compareTo(BigDecimal.ZERO) > 0);

    // muestra en la consola el pedido insertado
    System.out.println("=== PEDIDO INSERTADO ===");
    System.out.println("ID: " + inserted.getIdPedido());
    System.out.println("Cliente ID: " + inserted.getIdUsuario());
    System.out.println("Medio de Pago ID: " + inserted.getIdMedioDePago());
    System.out.println("Estado Pedido: " + inserted.getIdEstadoPedido());

    System.out.println("--- Detalles ---");
    inserted.getDetalles().forEach(det -> {
      System.out.println("Producto ID: " + det.getIdProducto());
      System.out.println("Cantidad: " + det.getCantidad());
      System.out.println(" ");
    });
    PedidoIdStorage.saveId(inserted.getIdPedido());
  }

  @Test
  @DisplayName("Buscar pedido leyendo ID de archivo")
  void testgetbyid() throws Exception {
    UUID pedidoId = PedidoIdStorage.readId();

    mockMvc.perform(MockMvcRequestBuilders.get("/api/pedido/" + pedidoId)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.idPedido").value(pedidoId.toString()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.detalles").isArray());
    System.out.println("Id buscado " + pedidoId);

  }

  @Test
  void getAll() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/pedido")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers
            .content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }

  @Test
  void deleteOneRowFound() throws Exception {
    UUID pedidoId = PedidoIdStorage.readId();
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/pedido/" + pedidoId)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
