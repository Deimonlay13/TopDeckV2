package cl.gdl.ms_pedido.dto;

import cl.gdl.ms_pedido.entity.PedidoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PedidoConUsuarioDTO {
    private PedidoEntity pedido;
    private UsuarioDTO usuario;
}
