package cl.gdl.ms_pedido.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DireccionDTO {

    private UUID idDireccion;
    private String idPersona;
    private String idComuna;
    private String idRegion;
    private String direccion;
    private String telefono;
}

