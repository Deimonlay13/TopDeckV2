package cl.gdl.ms_pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoConUsuarioDTO {
    private PedidoDTO pedido;
    private UsuarioDTO usuario;
}
