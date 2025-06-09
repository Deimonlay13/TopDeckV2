package cl.gdl.ms_pedido.service;

import java.util.List;
import java.util.UUID;

import cl.gdl.ms_pedido.dto.EstadoPedidoDTO;

public interface IEstadoPedidoService {

    EstadoPedidoDTO insert(EstadoPedidoDTO estadoPedido);

    EstadoPedidoDTO update(UUID id, EstadoPedidoDTO estadoPedido);

    EstadoPedidoDTO delete(UUID id);

    EstadoPedidoDTO getById(UUID id);

    List<EstadoPedidoDTO> getAll();

}
