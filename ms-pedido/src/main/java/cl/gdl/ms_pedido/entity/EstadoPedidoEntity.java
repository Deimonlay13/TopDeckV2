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
@Table(name = "estado_pedido")
public class EstadoPedidoEntity {
    @Id
    @Column(name = "id_estado_pedido")
    private UUID id;

    @Column(name = "nombre")
    private String nombre;
    
}
