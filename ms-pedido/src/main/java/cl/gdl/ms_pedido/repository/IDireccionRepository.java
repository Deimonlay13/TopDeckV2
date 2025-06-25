package cl.gdl.ms_pedido.repository;

import cl.gdl.ms_pedido.entity.DireccionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.google.common.base.Optional;
import java.util.UUID;

@Repository
public interface IDireccionRepository extends CrudRepository<DireccionEntity, UUID> {
    Optional<DireccionEntity> findByDireccionIgnoreCase(String direccion);

}


