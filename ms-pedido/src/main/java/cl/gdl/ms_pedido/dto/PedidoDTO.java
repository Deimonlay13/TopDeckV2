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

public class PedidoDTO {

    private UUID idPedido;

    private Float total;


}
