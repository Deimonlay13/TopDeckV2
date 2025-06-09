package cl.gdl.ms_pedido.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.gdl.ms_pedido.dto.PedidoDTO;
import cl.gdl.ms_pedido.errors.NoDataException;
import cl.gdl.ms_pedido.errors.NotFoundException;
import cl.gdl.ms_pedido.repository.IPedidoRepository;
import cl.gdl.ms_pedido.service.IPedidoService;

@Service
public class PedidoServiceImpl implements IPedidoService {
    @Autowired
    IPedidoRepository pedidoRepository;

    @Override
    public PedidoDTO insert(PedidoDTO pedido) {
        checkPedidoIdNotExists(pedido.getIdPedido());
        checkPedidoTotalNotNull(pedido.getTotal());
        return pedidoRepository.save(pedido);
    }

    @Override
    public PedidoDTO update(UUID id, PedidoDTO pedido) {
        checkPedidoExists(id);

        pedido.setIdPedido(id);
        return pedidoRepository.save(pedido);
    }

    @Override
    public PedidoDTO delete(UUID id) {
        checkPedidoExists(id);

        pedidoRepository.deleteById(id);
        return null;
    }

    @Override
    public PedidoDTO getById(UUID id) {
        checkPedidoExists(id);
        return pedidoRepository.findById(id).get();
    }

    @Override
    public List<PedidoDTO> getAll() {
        List<PedidoDTO> pedidos = (List<PedidoDTO>) pedidoRepository.findAll();
        if (pedidos.isEmpty()) {
            throw new NoDataException();
        }
        return pedidos;
    }

    private void checkPedidoExists(UUID idPedido) {
        pedidoRepository.findById(idPedido)
                .ifPresent(existingPedido -> {
                    throw new NotFoundException("El Pedido con el ID: " + idPedido + " ya existe");
                });
    }
    private void checkPedidoTotalNotNull(Float total) {
        if (total == null) {
            throw new   NotFoundException("El total no puede ser nulo"); }
    }


    private PedidoDTO checkPedidoIdNotExists(UUID id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El Pedido con el ID: " + id + " no existe"));
    }

}
