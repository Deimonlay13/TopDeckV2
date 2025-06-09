package cl.gdl.ms_pedido.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private String idUsuario;

    private String email;

    private String firstName;

    private String lastName;
    
    private UUID idPedido;
} 