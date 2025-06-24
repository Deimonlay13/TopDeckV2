package cl.gdl.ms_pedido.Mapper;

import java.util.List;
import java.util.stream.Collectors;



import cl.gdl.ms_pedido.dto.DetallePedidoDTO;
import cl.gdl.ms_pedido.dto.PedidoDTO;
import cl.gdl.ms_pedido.entity.DetallePedidoEntity;
import cl.gdl.ms_pedido.entity.PedidoEntity;

public class PedidoMapper {

 public static PedidoDTO toDTO(PedidoEntity entity) {
        PedidoDTO dto = new PedidoDTO();
        dto.setIdPedido(entity.getId());
        dto.setIdUsuario(entity.getIdUsuario());
        dto.setIdMedioDePago(entity.getMedioDePago() != null ? entity.getMedioDePago().getId() : null);
        dto.setIdEntrega(entity.getEntrega() != null ? entity.getEntrega().getId() : null);
        dto.setIdEstadoPedido(entity.getEstadoPedido() != null ? entity.getEstadoPedido().getId() : null);
        dto.setTotal(entity.getTotal());

        if (entity.getDetalles() != null) {
            List<DetallePedidoDTO> detalles = entity.getDetalles().stream()
                .map(PedidoMapper::toDTO)
                .collect(Collectors.toList());
            dto.setDetalles(detalles);
        }

        return dto;
    }

    public static PedidoEntity toEntity(PedidoDTO dto) {
        PedidoEntity entity = new PedidoEntity();
        entity.setIdUsuario(dto.getIdUsuario());
        // Los demás campos (medios de pago, entrega, estado) se deben setear externamente en el service

        if (dto.getDetalles() != null) {
            List<DetallePedidoEntity> detalles = dto.getDetalles().stream()
                .map(PedidoMapper::toEntity)
                .collect(Collectors.toList());
            entity.setDetalles(detalles);
        }

        return entity;
    }

    private static DetallePedidoDTO toDTO(DetallePedidoEntity entity) {
        DetallePedidoDTO dto = new DetallePedidoDTO();
        dto.setIdProducto(entity.getIdProducto());
        dto.setCantidad(entity.getCantidad());
        dto.setPrecio_unitario(entity.getPrecioUnitario());
        dto.setSubtotal(entity.getSubtotal());
        return dto;
    }

    private static DetallePedidoEntity toEntity(DetallePedidoDTO dto) {
        DetallePedidoEntity entity = new DetallePedidoEntity();
        entity.setIdProducto(dto.getIdProducto());
        entity.setCantidad(dto.getCantidad());
        // El precio y subtotal se deben calcular externamente en el service
        return entity;
    }
}
