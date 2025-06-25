package cl.gdl.ms_pedido.service;

import java.util.List;
import java.util.UUID;
import cl.gdl.ms_pedido.dto.DireccionDTO;

public interface IDireccionService {

    DireccionDTO insert(DireccionDTO direccion);

    DireccionDTO update(UUID id, DireccionDTO direccion);

    DireccionDTO delete(UUID id);

    DireccionDTO getById(UUID id);

    List<DireccionDTO> getAll();
}

