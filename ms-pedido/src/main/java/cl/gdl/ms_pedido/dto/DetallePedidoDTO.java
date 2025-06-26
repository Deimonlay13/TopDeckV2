package cl.gdl.ms_pedido.dto;

import java.math.BigDecimal;
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
public class DetallePedidoDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UUID idDetalle;

    private String idProducto;   

    private int cantidad;

    private BigDecimal precio_unitario;

    private BigDecimal subtotal;
}
