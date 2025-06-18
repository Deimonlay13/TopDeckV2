package cl.gdl.ms_pedido.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.gdl.ms_pedido.client.IUsuarioClient;
import cl.gdl.ms_pedido.dto.PedidoConUsuarioDTO;
import cl.gdl.ms_pedido.dto.UsuarioDTO;
import cl.gdl.ms_pedido.entity.DetallePedidoEntity;
import cl.gdl.ms_pedido.entity.PedidoEntity;
import cl.gdl.ms_pedido.errors.NoDataException;
import cl.gdl.ms_pedido.errors.NotFoundException;
import cl.gdl.ms_pedido.repository.IPedidoRepository;
import cl.gdl.ms_pedido.service.IPedidoService;

@Service
public class PedidoServiceImpl implements IPedidoService {

    @Autowired
    private IUsuarioClient usuarioClient;


    @Autowired
    IPedidoRepository pedidoRepository;

    @Override
    public PedidoEntity insert(PedidoEntity pedido) {
        if (pedido.getId() != null && pedidoRepository.existsById(pedido.getId())) {
            throw new NotFoundException("El Pedido con el ID: " + pedido.getId() + " ya existe");
        }
        checkPedidoTotalNotNull(pedido.getTotal());
        
    // Asociar el pedido en cada detalle
        if (pedido.getDetalles() != null) {
        for (DetallePedidoEntity detalle : pedido.getDetalles()) {
            detalle.setPedido(pedido);

            // Calcular subtotal si no viene
            if (detalle.getSubtotal() == null) {
                BigDecimal subtotal = detalle.getPrecioUnitario().multiply(BigDecimal.valueOf(detalle.getCantidad()));
                detalle.setSubtotal(subtotal);
            }
        }}

        
        
        return pedidoRepository.save(pedido);
        }

    @Override
    public PedidoEntity update(UUID id, PedidoEntity pedido) {
        PedidoEntity existing = checkPedidoExists(id);

        checkPedidoTotalNotNull(pedido.getTotal());

        // Actualizamos campos, salvo el id que no debe cambiar
        existing.setTotal(pedido.getTotal());
        existing.setIdUsuario(pedido.getIdUsuario());
        existing.setMedioDePago(pedido.getMedioDePago());
        existing.setEntrega(pedido.getEntrega());
        existing.setEstadoPedido(pedido.getEstadoPedido());
        existing.setDetalles(pedido.getDetalles());

        return pedidoRepository.save(existing);
    }

    @Override
    public PedidoEntity delete(UUID id) {
        PedidoEntity existing = checkPedidoExists(id);
        pedidoRepository.deleteById(id);
        return existing;
    }

    @Override
    public PedidoEntity getById(UUID id) {
        return checkPedidoExists(id);
    }

    @Override
    public List<PedidoEntity> getAll() {
        List<PedidoEntity> pedidos = (List<PedidoEntity>) pedidoRepository.findAll();
        if (pedidos.isEmpty()) {
            throw new NoDataException();
        }
        return pedidos;
    }
    
    public PedidoConUsuarioDTO getPedidoConUsuario(UUID idPedido) {
        PedidoEntity pedido = checkPedidoExists(idPedido);
        System.out.println("LLAMANDO A MS-USERS");
        System.out.println("Id usuario pedido: " + pedido.getIdUsuario());
        UsuarioDTO usuario = usuarioClient.getUsuarioById(pedido.getIdUsuario());
        System.out.println("Usuario obtenido: " + usuario);        

        return new PedidoConUsuarioDTO(pedido, usuario);
    }    

    private PedidoEntity checkPedidoExists(UUID idPedido) {
        return pedidoRepository.findById(idPedido)
            .orElseThrow(() -> new NotFoundException("El Pedido con el ID: " + idPedido + " no existe"));
    }

    private void checkPedidoTotalNotNull(BigDecimal total) {
        if (total == null) {
            throw new NotFoundException("El total no puede ser nulo");
        }
    }
}