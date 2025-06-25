package cl.gdl.ms_pedido.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "direccion")
public class DireccionEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name = "id_direccion", columnDefinition = "RAW(16)")
    private UUID idDireccion;

    @Column(name = "id_persona", nullable = false, length = 255)
    private String idPersona;

    @Column(name = "id_comuna", nullable = false, length = 255)
    private String idComuna;

    @Column(name = "id_region", nullable = false, length = 255)
    private String idRegion;

    @Column(name = "direccion", nullable = false, length = 255)
    private String direccion;

    @Column(name = "telefono", length = 25)
    private String telefono;
}

