package cl.gdl.ms_pedido.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private String username;

    private String email;

    private String firstName;

    private String lastName;
    
} 