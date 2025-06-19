package cl.gdl.ms_pedido.service;

import java.util.List;
import java.util.UUID;

import cl.gdl.ms_pedido.dto.DetallePedidoCartaDTO;
import cl.gdl.ms_pedido.entity.DetallePedidoEntity;

public interface IDetallePedidoService {

    List<DetallePedidoEntity> insert(List<DetallePedidoEntity> detallePedido);

    DetallePedidoEntity update(UUID id, DetallePedidoEntity detallePedido);

    DetallePedidoEntity delete(UUID id);

    DetallePedidoEntity getById(UUID id);

    List<DetallePedidoEntity> getAll();

    List<DetallePedidoEntity> getByPedidoId(UUID idPedido);
    List<DetallePedidoCartaDTO> getDetallesConCartasPorPedido(UUID idPedido);


}
