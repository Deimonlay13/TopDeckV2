package cl.gdl.ms_pedido.Mapper;

import java.util.List;
import java.util.stream.Collectors;

import cl.gdl.ms_pedido.dto.DetallePedidoDTO;
import cl.gdl.ms_pedido.dto.DireccionDTO;
import cl.gdl.ms_pedido.dto.EntregaDTO;
import cl.gdl.ms_pedido.dto.EstadoPedidoDTO;
import cl.gdl.ms_pedido.dto.MedioDePagoDTO;
import cl.gdl.ms_pedido.dto.PedidoDTO;
import cl.gdl.ms_pedido.entity.DetallePedidoEntity;
import cl.gdl.ms_pedido.entity.PedidoEntity;

public class PedidoMapper {

    public static PedidoDTO toDTO(PedidoEntity entity) {
        PedidoDTO dto = new PedidoDTO();

        dto.setIdPedido(entity.getId());
        dto.setIdUsuario(entity.getIdUsuario());

        // Medio de pago
        if (entity.getMedioDePago() != null) {
            MedioDePagoDTO medioDto = new MedioDePagoDTO();
            medioDto.setId(entity.getMedioDePago().getId());
            medioDto.setNombre(entity.getMedioDePago().getNombre());
            dto.setIdMedioDePago(medioDto);
        }

        // Entrega
        if (entity.getEntrega() != null) {
            EntregaDTO entregaDto = new EntregaDTO();
            entregaDto.setIdEntrega(entity.getEntrega().getId());
            entregaDto.setEntrega(entity.getEntrega().getDescripcion());
            dto.setIdEntrega(entregaDto);
        }

        // Estado pedido
        if (entity.getEstadoPedido() != null) {
            EstadoPedidoDTO estadoDto = new EstadoPedidoDTO();
            estadoDto.setIdEstadoPedido(entity.getEstadoPedido().getId());
            estadoDto.setNameEstadoPedido(entity.getEstadoPedido().getNombre());
            dto.setIdEstadoPedido(estadoDto);
        }

        // Dirección
        if (entity.getDireccion() != null) {
            DireccionDTO direccionDTO = new DireccionDTO();
            direccionDTO.setIdDireccion(entity.getDireccion().getIdDireccion());
            direccionDTO.setIdComuna(entity.getDireccion().getIdComuna());
            direccionDTO.setIdRegion(entity.getDireccion().getIdRegion());
            direccionDTO.setDireccion(entity.getDireccion().getDireccion());
            direccionDTO.setTelefono(entity.getDireccion().getTelefono());
            dto.setDireccion(direccionDTO);
        }


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
        // Los demás campos se setean en el service

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
        dto.setIdDetalle(entity.getId());
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
        // El precio y subtotal se calculan en el service
        return entity;
    }
}

