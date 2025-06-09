package cl.gdl.ms_pedido.service;

import java.util.List;
import java.util.UUID;

import cl.gdl.ms_pedido.dto.MedioDePagoDTO;

public interface IMedioDePagoService {

    MedioDePagoDTO insert(MedioDePagoDTO medioDePago);

    MedioDePagoDTO update(UUID id, MedioDePagoDTO medioDePago);

    MedioDePagoDTO delete(UUID id);

    MedioDePagoDTO getById(UUID id);

    List<MedioDePagoDTO> getAll();

}
