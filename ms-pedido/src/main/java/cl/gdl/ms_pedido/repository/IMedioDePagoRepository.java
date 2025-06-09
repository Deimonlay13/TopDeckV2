package cl.gdl.ms_pedido.repository;

import java.util.Optional;
import java.util.UUID;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.gdl.ms_pedido.entity.MedioDePagoEntity;

@Repository
public interface IMedioDePagoRepository extends CrudRepository<MedioDePagoEntity, UUID> {
    
    Optional<MedioDePagoEntity> findByNombreIgnoreCase(String nombre);
}
