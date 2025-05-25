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
@Table(name = "MEDIO_DE_PAGO")
public class MedioDePagoDTO {

    @Id
    @GeneratedValue
    @Column(name = "ID_MEDIO_DE_PAGO")
    private UUID idMedioDePago;

    @Column(name = "MEDIO_DE_PAGO")
    private String nameMedioDePago;


}
