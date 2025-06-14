package cl.gdl.ms_pedido.entity;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detalle_pedido")
public class DetallePedidoEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name = "id_detalle")
    private UUID id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_pedido")
    private PedidoEntity pedido;
    
    @Column(name = "id_producto")
    private String idProducto;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

}
