package cl.gdl.ms_pedido.service;

import java.util.List;
import java.util.UUID;


import cl.gdl.ms_pedido.entity.EntregaEntity;

public interface IEntregaService {

    EntregaEntity insert(EntregaEntity entrega);

    EntregaEntity update(UUID id, EntregaEntity entrega);

    EntregaEntity delete(UUID id);

    EntregaEntity getById(UUID id);

    List<EntregaEntity> getAll();

}
