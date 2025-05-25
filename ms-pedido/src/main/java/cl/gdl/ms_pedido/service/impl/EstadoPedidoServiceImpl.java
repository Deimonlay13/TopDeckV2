package cl.gdl.ms_pedido.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.gdl.ms_pedido.dto.EstadoPedidoDTO;
import cl.gdl.ms_pedido.errors.DuplicatedNameException;
import cl.gdl.ms_pedido.errors.NameNullException;
import cl.gdl.ms_pedido.errors.NoDataException;
import cl.gdl.ms_pedido.errors.NotFoundException;
import cl.gdl.ms_pedido.repository.IEstadoPedidoRepository;
import cl.gdl.ms_pedido.service.IEstadoPedidoService;

@Service
public class EstadoPedidoServiceImpl  implements IEstadoPedidoService {
    @Autowired
    IEstadoPedidoRepository estadoPedidoRepository;

    @Override
    public EstadoPedidoDTO insert(EstadoPedidoDTO estadoPedido) {
        checkNameEstadoPedidoNotNullOrEmpty(estadoPedido.getNameEstadoPedido());

        checkEstadoPedidoNameNotExists(estadoPedido.getNameEstadoPedido());

        return estadoPedidoRepository.save(estadoPedido);
    }

    @Override
    public EstadoPedidoDTO update(UUID id, EstadoPedidoDTO estadoPedido) {
        checkEstadoPedidoExists(id);

        checkNameEstadoPedidoNotNullOrEmpty(estadoPedido.getNameEstadoPedido());

        checkEstadoPedidoNameNotExists(estadoPedido.getNameEstadoPedido());

        estadoPedido.setIdEstadoPedido(id);
        return estadoPedidoRepository.save(estadoPedido);
    }

    @Override
    public EstadoPedidoDTO delete(UUID id) {
        checkEstadoPedidoExists(id);

        estadoPedidoRepository.deleteById(id);
        return null;
    }

    @Override
    public EstadoPedidoDTO getById(UUID id) {
        checkEstadoPedidoExists(id);
        return estadoPedidoRepository.findById(id).get();
    }

    @Override
    public List<EstadoPedidoDTO> getAll() {
        List<EstadoPedidoDTO> estadoPedidos = (List<EstadoPedidoDTO>) estadoPedidoRepository.findAll();
        if (estadoPedidos.isEmpty()) {
            throw new NoDataException();
        }
        return estadoPedidos;
    }

    private void checkNameEstadoPedidoNotNullOrEmpty(String nameEstadoPedido) {
        if (nameEstadoPedido == null || nameEstadoPedido.trim().isEmpty()) {
            throw new NameNullException("nameEstadoPedido");
        }
    }

    private EstadoPedidoDTO checkEstadoPedidoExists(UUID id) {
        return estadoPedidoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El Medio De Pago con el ID: " + id + " no existe"));
    }

    private void checkEstadoPedidoNameNotExists(String nameEstadoPedido) {
        estadoPedidoRepository.findByNameEstadoPedidoIgnoreCase(nameEstadoPedido)
                .ifPresent(existingEstadoPedido -> {
                    throw new DuplicatedNameException(nameEstadoPedido);
                });
    }
}
