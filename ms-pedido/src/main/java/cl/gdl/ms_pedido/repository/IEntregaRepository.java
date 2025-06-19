package cl.gdl.ms_pedido.repository;

import java.util.Optional;
import java.util.UUID;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import cl.gdl.ms_pedido.entity.EntregaEntity;

@Repository
public interface IEntregaRepository extends CrudRepository<EntregaEntity, UUID> {
    Optional<EntregaEntity> findByDescripcionIgnoreCase(String descripcion);
}

