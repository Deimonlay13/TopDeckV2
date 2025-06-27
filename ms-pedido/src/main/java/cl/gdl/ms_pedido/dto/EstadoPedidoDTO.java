package cl.gdl.ms_pedido.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstadoPedidoDTO {

    private UUID idEstadoPedido;
    
    private String nameEstadoPedido;
}
