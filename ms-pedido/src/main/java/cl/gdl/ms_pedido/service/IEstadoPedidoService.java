package cl.gdl.ms_pedido.service;

import java.util.List;
import java.util.UUID;


import cl.gdl.ms_pedido.entity.EstadoPedidoEntity;

public interface IEstadoPedidoService {

    EstadoPedidoEntity insert(EstadoPedidoEntity estadoPedido);

    EstadoPedidoEntity update(UUID id, EstadoPedidoEntity estadoPedido);

    EstadoPedidoEntity delete(UUID id);

    EstadoPedidoEntity getById(UUID id);

    List<EstadoPedidoEntity> getAll();
}