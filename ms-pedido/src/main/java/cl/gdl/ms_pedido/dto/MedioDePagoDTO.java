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

public class MedioDePagoDTO {

    private UUID id;

    private String nameMedioDePago;


}
