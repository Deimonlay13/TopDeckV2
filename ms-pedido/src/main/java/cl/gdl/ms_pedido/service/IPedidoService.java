package cl.gdl.ms_pedido.service;

import java.util.List;
import java.util.UUID;


import cl.gdl.ms_pedido.entity.PedidoEntity;

public interface IPedidoService {

    PedidoEntity insert(PedidoEntity pedido);

    PedidoEntity update(UUID id, PedidoEntity pedido);

    PedidoEntity delete(UUID id);

    PedidoEntity getById(UUID id);

    List<PedidoEntity> getAll();
}
