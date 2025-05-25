package cl.gdl.ms_pedido.dto;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ENTREGA")
public class EntregaDTO {
    @Id
    @GeneratedValue
    @Column(name = "ID_ENTREGA")
    private UUID idEntrega;

    @Column(name = "ENTREGA")
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
