package cl.gdl.ms_pedido.dto;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PedidoDTO {

    private UUID idPedido;
    
    private String idUsuario;

    private MedioDePagoDTO idMedioDePago;

    private DireccionDTO direccion;

    private EntregaDTO idEntrega;

    private EstadoPedidoDTO idEstadoPedido;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal total;
    

    private List<DetallePedidoDTO> detalles;


}
