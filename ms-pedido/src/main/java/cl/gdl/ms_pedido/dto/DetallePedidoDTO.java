package cl.gdl.ms_pedido.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoDTO {

    private UUID idDetalle;

    private String idProducto;   

    private int cantidad;

    private BigDecimal precio_unitario;

    private BigDecimal subtotal;
}
