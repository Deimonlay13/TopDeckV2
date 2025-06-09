package cl.gdl.ms_pedido.service;

import java.util.List;
import java.util.UUID;


import cl.gdl.ms_pedido.entity.MedioDePagoEntity;

public interface IMedioDePagoService {

    MedioDePagoEntity insert(MedioDePagoEntity medioDePago);

    MedioDePagoEntity update(UUID id, MedioDePagoEntity medioDePago);

    MedioDePagoEntity delete(UUID id);

    MedioDePagoEntity getById(UUID id);

    List<MedioDePagoEntity> getAll();
}
