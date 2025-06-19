package cl.gdl.ms_pedido.entity;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medio_de_pago")
public class MedioDePagoEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name = "id_medio_de_pago")
    private UUID id;

    @Column(name = "nombre")
    private String nombre;
}
