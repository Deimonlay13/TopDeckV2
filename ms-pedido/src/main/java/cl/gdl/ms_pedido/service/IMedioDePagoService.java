package cl.gdl.ms_pedido.service;

import java.util.List;
import java.util.UUID;

import cl.gdl.ms_pedido.dto.MedioDePagoDTO;
import cl.gdl.ms_pedido.entity.MedioDePagoEntity;

public interface IMedioDePagoService {

    MedioDePagoDTO insert(MedioDePagoDTO medioDePago);

    MedioDePagoEntity update(UUID id, MedioDePagoEntity medioDePago);

    MedioDePagoDTO delete(UUID id);

    MedioDePagoDTO getById(UUID id);

    List<MedioDePagoDTO> getAll();
}
