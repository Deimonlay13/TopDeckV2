package cl.gdl.ms_pedido.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido")
public class PedidoEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name = "id_pedido")
    private UUID id;

    @Column(name = "id_usuario")
    private String idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_medio_de_pago")
    private MedioDePagoEntity medioDePago;

    @ManyToOne
    @JoinColumn(name = "id_entrega")
    private EntregaEntity entrega;

    @ManyToOne
    @JoinColumn(name = "id_estado_pedido")
    private EstadoPedidoEntity estadoPedido;

    @Column(name = "total")
    private BigDecimal total; 

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetallePedidoEntity> detalles;

}
