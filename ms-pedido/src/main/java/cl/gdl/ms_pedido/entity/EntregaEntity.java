package cl.gdl.ms_pedido.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entrega")
public class EntregaEntity {

    @Column(name = "id_entrega")
    @Id
    private UUID id;

    @Column(name = "descripcion")
    private String descripcion;

}
