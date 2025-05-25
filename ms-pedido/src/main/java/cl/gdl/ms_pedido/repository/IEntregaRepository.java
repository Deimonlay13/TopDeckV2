package cl.gdl.ms_pedido.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.gdl.ms_pedido.dto.EntregaDTO;

@Repository
public interface IEntregaRepository extends CrudRepository<EntregaDTO, UUID> {

    EntregaDTO findByNameEstadoPedido(String nameEntregaDTO);

    Optional<EntregaDTO> findByNameEntregaIgnoreCase(String nameEntrega);

}
