package cl.gdl.ms_pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComunaDTO {

    private Long codigoComuna;
    private String nombreComuna;
    private Long regionId;
}
