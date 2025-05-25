package cl.gdl.ms_pedido.service;

import java.util.List;
import java.util.UUID;

import cl.gdl.ms_pedido.dto.PedidoDTO;

public interface IPedidoService {

    PedidoDTO insert(PedidoDTO pedido);

    PedidoDTO update(UUID id, PedidoDTO pedido);

    PedidoDTO delete(UUID id);

    PedidoDTO getById(UUID id);

    List<PedidoDTO> getAll();

}
