package cl.gdl.ms_pedido.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.gdl.ms_pedido.dto.EstadoPedidoDTO;

@Repository
public interface IEstadoPedidoRepository extends CrudRepository<EstadoPedidoDTO, UUID> {
    
    EstadoPedidoDTO findByNameEstadoPedido(String nameEstadoPedidoDTO);

    Optional<EstadoPedidoDTO> findByNameEstadoPedidoIgnoreCase(String nameEstadoPedido);
}
