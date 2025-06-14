package cl.gdl.ms_pedido.repository;

import java.util.List;
// import java.math.BigDecimal;
// import java.util.Optional;
import java.util.UUID;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.gdl.ms_pedido.entity.DetallePedidoEntity;


@Repository
public interface IDetallepedidoRepository extends CrudRepository<DetallePedidoEntity, UUID> {
    public interface IDetallePedidoService {
    List<DetallePedidoEntity> insertAll(List<DetallePedidoEntity> detalles);
}
}