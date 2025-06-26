package cl.gdl.ms_pedido.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "REGION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegionEntity {

    @Id
    @Column(name = "ID_REGION")
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "NUMERO", length = 5)
    private String numero;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ComunaEntity> comunas;
}
