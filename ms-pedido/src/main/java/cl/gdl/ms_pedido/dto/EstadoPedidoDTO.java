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
@Table(name = "ESTADO_PEDIDO")
public class EstadoPedidoDTO {
    @Id
    @GeneratedValue
    @Column(name = "ID_ESTADO_PEDIDO")
    private UUID idEstadoPedido;

    @Column(name = "ESTADO")
    private String nameEstadoPedido;
}
