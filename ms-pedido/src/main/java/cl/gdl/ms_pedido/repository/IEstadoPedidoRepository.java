package cl.gdl.ms_pedido.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.gdl.ms_pedido.dto.EstadoPedidoDTO;
import cl.gdl.ms_pedido.entity.EstadoPedidoEntity;

@Repository
public interface IEstadoPedidoRepository extends JpaRepository<EstadoPedidoEntity, UUID> {
    
    EstadoPedidoDTO findByNameEstadoPedido(String nameEstadoPedidoDTO);

    Optional<EstadoPedidoDTO> findByNameEstadoPedidoIgnoreCase(String nameEstadoPedido);
}
