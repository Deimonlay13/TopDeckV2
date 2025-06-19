package cl.gdl.ms_pedido.repository;


import java.util.UUID;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import cl.gdl.ms_pedido.entity.PedidoEntity;

@Repository
public interface IPedidoRepository  extends CrudRepository<PedidoEntity, UUID> {

}
