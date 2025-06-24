package cl.gdl.ms_pedido.dto.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import cl.gdl.ms_pedido.dto.DetallePedidoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResponseDTO {

    private UUID id;
    private UUID idUsuario;
    private String nombreUsuario;

    private UUID idMedioDePago;
    private String nombreMedioDePago;

    private UUID idEntrega;
    private String tipoEntrega;

    private UUID idEstadoPedido;
    private String nombreEstadoPedido;

    private BigDecimal total;
    private List<DetallePedidoDTO> detalles;
}