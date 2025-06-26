package cl.gdl.ms_pedido.dto;

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

public class MedioDePagoDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UUID id;

    private String nombre;


}
