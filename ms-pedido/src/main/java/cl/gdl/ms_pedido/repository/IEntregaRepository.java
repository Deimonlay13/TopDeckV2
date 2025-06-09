package cl.gdl.ms_pedido.repository;

import java.util.Optional;
import java.util.UUID;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.gdl.ms_pedido.dto.EntregaDTO;
import cl.gdl.ms_pedido.entity.EntregaEntity;

@Repository
public interface IEntregaRepository extends CrudRepository<EntregaEntity, UUID> {

    EntregaDTO findByNameEntrega(String nameEntregaDTO);

    Optional<EntregaDTO> findByNameEntregaIgnoreCase(String nameEntrega);

}
