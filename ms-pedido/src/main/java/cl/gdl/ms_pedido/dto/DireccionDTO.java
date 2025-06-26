package cl.gdl.ms_pedido.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DireccionDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UUID idDireccion;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String idPersona;
    private String idComuna;
    private String idRegion;
    private String direccion;
    private String telefono;
}

