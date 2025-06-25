package cl.gdl.ms_pedido.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.gdl.ms_pedido.Mapper.PedidoMapper;
import cl.gdl.ms_pedido.client.ICartaClient;
import cl.gdl.ms_pedido.client.IUsuarioClient;
import cl.gdl.ms_pedido.dto.CartaDTO;
import cl.gdl.ms_pedido.dto.PedidoConUsuarioDTO;
import cl.gdl.ms_pedido.dto.PedidoDTO;
import cl.gdl.ms_pedido.dto.UsuarioDTO;
import cl.gdl.ms_pedido.entity.DetallePedidoEntity;
import cl.gdl.ms_pedido.entity.EntregaEntity;
import cl.gdl.ms_pedido.entity.EstadoPedidoEntity;
import cl.gdl.ms_pedido.entity.MedioDePagoEntity;
import cl.gdl.ms_pedido.entity.PedidoEntity;
import cl.gdl.ms_pedido.errors.NoDataException;
import cl.gdl.ms_pedido.errors.NotFoundException;
import cl.gdl.ms_pedido.repository.IEntregaRepository;
import cl.gdl.ms_pedido.repository.IEstadoPedidoRepository;
import cl.gdl.ms_pedido.repository.IMedioDePagoRepository;
import cl.gdl.ms_pedido.repository.IPedidoRepository;
import cl.gdl.ms_pedido.service.IPedidoService;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class PedidoServiceImpl implements IPedidoService {

    @Autowired private IPedidoRepository pedidoRepository;
    @Autowired private IMedioDePagoRepository medioRepo;
    @Autowired private IEntregaRepository entregaRepo;
    @Autowired private IEstadoPedidoRepository estadoRepo;
    @Autowired private ICartaClient cartaClient;
    @Autowired private IUsuarioClient usuarioClient;

    @Override
    public PedidoDTO insert(PedidoDTO dto) {
        PedidoEntity pedido = new PedidoEntity();

        pedido.setIdUsuario(dto.getIdUsuario());
        pedido.setMedioDePago(buscarMedioDePago(dto.getIdMedioDePago()));
        pedido.setEntrega(buscarEntrega(dto.getIdEntrega()));
        pedido.setEstadoPedido(buscarEstadoPedido(dto.getIdEstadoPedido()));

        AtomicReference<BigDecimal> total = new AtomicReference<>(BigDecimal.ZERO);

        if (dto.getDetalles() != null && !dto.getDetalles().isEmpty()) {
            List<DetallePedidoEntity> detalles = dto.getDetalles().stream().map(d -> {
                CartaDTO carta = cartaClient.getCartaPorId(d.getIdProducto());
                BigDecimal precio = carta.getAverageSellPrice();
                BigDecimal subtotal = precio.multiply(BigDecimal.valueOf(d.getCantidad()));

                DetallePedidoEntity detalle = new DetallePedidoEntity();
                detalle.setIdProducto(d.getIdProducto());
                detalle.setCantidad(d.getCantidad());
                detalle.setPrecioUnitario(precio);
                detalle.setSubtotal(subtotal);
                detalle.setPedido(pedido);

                total.set(total.get().add(subtotal));
                return detalle;
            }).collect(Collectors.toList());

            pedido.setDetalles(detalles);
        }

        pedido.setTotal(total.get());

        PedidoEntity saved = pedidoRepository.save(pedido);
        return PedidoMapper.toDTO(saved);
    }

   @Override
public PedidoDTO update(UUID id, PedidoDTO dto) {
    PedidoEntity existing = checkPedidoExists(id);

    existing.setIdUsuario(dto.getIdUsuario());
    existing.setMedioDePago(buscarMedioDePago(dto.getIdMedioDePago()));
    existing.setEntrega(buscarEntrega(dto.getIdEntrega()));
    existing.setEstadoPedido(buscarEstadoPedido(dto.getIdEstadoPedido()));

    AtomicReference<BigDecimal> total = new AtomicReference<>(BigDecimal.ZERO);

    // Asegúrate que la lista detalles no sea nula para evitar NullPointerException
    if (existing.getDetalles() == null) {
        existing.setDetalles(new ArrayList<>());
    }

    if (dto.getDetalles() != null && !dto.getDetalles().isEmpty()) {
        // Limpia la lista existente en lugar de reemplazarla
        existing.getDetalles().clear();

        List<DetallePedidoEntity> nuevosDetalles = dto.getDetalles().stream().map(d -> {
            CartaDTO carta = cartaClient.getCartaPorId(d.getIdProducto());
            BigDecimal precio = carta.getAverageSellPrice();
            BigDecimal subtotal = precio.multiply(BigDecimal.valueOf(d.getCantidad()));

            DetallePedidoEntity detalle = new DetallePedidoEntity();
            detalle.setIdProducto(d.getIdProducto());
            detalle.setCantidad(d.getCantidad());
            detalle.setPrecioUnitario(precio);
            detalle.setSubtotal(subtotal);
            detalle.setPedido(existing);

            total.set(total.get().add(subtotal));
            return detalle;
        }).collect(Collectors.toList());

        // Añade los nuevos detalles a la lista existente
        existing.getDetalles().addAll(nuevosDetalles);
    } else {
        // Si no hay detalles nuevos, limpia la lista y totaliza en cero
        existing.getDetalles().clear();
        total.set(BigDecimal.ZERO);
    }

    existing.setTotal(total.get());

    PedidoEntity actualizado = pedidoRepository.save(existing);
    return PedidoMapper.toDTO(actualizado);
}

    @Override
    public PedidoDTO delete(UUID id) {
        PedidoEntity existing = checkPedidoExists(id);
        pedidoRepository.deleteById(id);
        return PedidoMapper.toDTO(existing);
    }

    @Override
    public PedidoDTO getById(UUID id) {
        return PedidoMapper.toDTO(checkPedidoExists(id));
    }

    public PedidoConUsuarioDTO getPedidoConUsuario(UUID idPedido) {
        PedidoEntity pedido = checkPedidoExists(idPedido);
        System.out.println("LLAMANDO A MS-USERS");
        System.out.println("Id usuario pedido: " + pedido.getIdUsuario());
        UsuarioDTO usuario = usuarioClient.getUsuarioById(pedido.getIdUsuario());
        System.out.println("Usuario obtenido: " + usuario);        

        return new PedidoConUsuarioDTO(pedido, usuario);
    }    

    @Override
    public List<PedidoDTO> getAll() {
        List<PedidoEntity> pedidos = pedidoRepository.findAll();
        if (pedidos.isEmpty()) throw new NoDataException();
        return pedidos.stream().map(PedidoMapper::toDTO).collect(Collectors.toList());
    }

    private PedidoEntity checkPedidoExists(UUID idPedido) {
        return pedidoRepository.findById(idPedido)
            .orElseThrow(() -> new NotFoundException("Pedido con ID " + idPedido + " no encontrado"));
    }

    private MedioDePagoEntity buscarMedioDePago(UUID id) {
        return medioRepo.findById(id)
            .orElseThrow(() -> new NotFoundException("Medio de pago no encontrado"));
    }

    private EntregaEntity buscarEntrega(UUID id) {
        return entregaRepo.findById(id)
            .orElseThrow(() -> new NotFoundException("Entrega no encontrada"));
    }

    private EstadoPedidoEntity buscarEstadoPedido(UUID id) {
        return estadoRepo.findById(id)
            .orElseThrow(() -> new NotFoundException("Estado de pedido no encontrado"));
    }
}
