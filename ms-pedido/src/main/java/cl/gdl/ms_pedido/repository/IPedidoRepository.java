package cl.gdl.ms_pedido.repository;


import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.gdl.ms_pedido.dto.PedidoDTO;

@Repository
public interface IPedidoRepository extends CrudRepository<PedidoDTO, UUID> {

    PedidoDTO findByIdPedido(UUID idPedido);
}
