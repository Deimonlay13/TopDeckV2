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
public class EntregaDTO {
    
    private UUID idEntrega;

    private String entrega;

    // @Column(name = "TELEFONO")
    // private String telefono;

    // @Column(name = "FECHA_ENTREGA")
    // private String fechaEntrega;

    // @Column(name = "HORA_ENTREGA")
    // private String horaEntrega;

    // @Column(name = "DIRECCION")
    // private String direccion;

}
