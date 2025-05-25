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
@Table(name="DETALLE_PEDIDO")
public class DetallePedidoDTO {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)

    @Column(name = "ID_DETALLE")
    private UUID idDetalle;

    @Column(name = "NRO_CARTA")
    private int nro_carta;
    
    @Column(name = "CANTIDAD")
    private int cantidad;

    @Column(name = "PRECIO_UNITARIO")
    private int precio_unitario;

    @Column(name = "SUBTOTAL")
    private int subtotal;
}
