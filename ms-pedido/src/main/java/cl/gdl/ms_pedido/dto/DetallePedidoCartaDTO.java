package cl.gdl.ms_pedido.dto;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;

@Data
public class DetallePedidoCartaDTO {

    private UUID id;
    private String idProducto;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;

    private CartaDTO carta;
}
