package cl.gdl.ms_pedido.service;

import java.util.List;
import java.util.UUID;

import cl.gdl.ms_pedido.dto.EntregaDTO;

public interface IEntregaService {

    EntregaDTO insert(EntregaDTO entrega);

    EntregaDTO update(UUID id, EntregaDTO entrega);

    EntregaDTO delete(UUID id);

    EntregaDTO getById(UUID id);

    List<EntregaDTO> getAll();

}
