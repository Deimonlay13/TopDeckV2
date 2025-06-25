package cl.gdl.ms_pedido.repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import cl.gdl.ms_pedido.entity.PedidoEntity;

@Repository
public interface IPedidoRepository  extends JpaRepository<PedidoEntity, UUID> {

}
