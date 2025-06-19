package cl.gdl.ms_pedido.repository;

import java.util.Optional;
import java.util.UUID;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.gdl.ms_pedido.entity.EstadoPedidoEntity;

@Repository
public interface IEstadoPedidoRepository extends CrudRepository<EstadoPedidoEntity, UUID> {

    Optional<EstadoPedidoEntity> findByNombreIgnoreCase(String nombre);
}
