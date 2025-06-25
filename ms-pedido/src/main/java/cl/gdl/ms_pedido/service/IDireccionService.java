package cl.gdl.ms_pedido.service;

import cl.gdl.ms_pedido.entity.DireccionEntity;

import java.util.List;
import java.util.UUID;

public interface IDireccionService {

    DireccionEntity insert(DireccionEntity direccion);

    DireccionEntity update(UUID id, DireccionEntity direccion);

    DireccionEntity delete(UUID id);

    DireccionEntity getById(UUID id);

    List<DireccionEntity> getAll();
}

