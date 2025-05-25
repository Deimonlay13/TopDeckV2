package cl.gdl.ms_pedido.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import cl.gdl.ms_pedido.dto.DetallePedidoDTO;
import cl.gdl.ms_pedido.dto.EntregaDTO;
import cl.gdl.ms_pedido.dto.EstadoPedidoDTO;
import cl.gdl.ms_pedido.dto.MedioDePagoDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido")
public class PedidoEntity {
    
    @Id
    @Column(name = "id_pedido")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "id_medio_de_pago")
    private MedioDePagoDTO medioDePago;

    @ManyToOne
    @JoinColumn(name = "id_entrega")
    private EntregaDTO entrega;

    @ManyToOne
    @JoinColumn(name = "id_estado_pedido")
    private EstadoPedidoDTO estadoPedido;

    private BigDecimal total;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallePedidoDTO> detalles;
}
