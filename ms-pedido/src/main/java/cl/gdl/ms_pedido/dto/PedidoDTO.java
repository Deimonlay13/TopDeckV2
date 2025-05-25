package cl.gdl.ms_pedido.dto;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name="PEDIDO")
public class PedidoDTO {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)

    @Column(name = "ID_PEDIDO")
    private UUID idPedido;

    @Column(name = "Total")
    private Float total;


}
