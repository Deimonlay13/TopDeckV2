package cl.gdl.ms_pedido.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.gdl.ms_pedido.entity.EstadoPedidoEntity;
import cl.gdl.ms_pedido.errors.DuplicatedNameException;
import cl.gdl.ms_pedido.errors.NameNullException;
import cl.gdl.ms_pedido.errors.NameNumberException;
import cl.gdl.ms_pedido.errors.NoDataException;
import cl.gdl.ms_pedido.errors.NotFoundException;
import cl.gdl.ms_pedido.repository.IEstadoPedidoRepository;
import cl.gdl.ms_pedido.service.IEstadoPedidoService;

@Service
public class EstadoPedidoServiceImpl implements IEstadoPedidoService {

    @Autowired
    IEstadoPedidoRepository estadoPedidoRepository;

    @Override
    public EstadoPedidoEntity insert(EstadoPedidoEntity estadoPedido) {
        checkNombreEstadoPedidoValido(estadoPedido.getNombre());
        checkEstadoPedidoNameNotExists(estadoPedido.getNombre());

        return estadoPedidoRepository.save(estadoPedido);
    }

    @Override
    public EstadoPedidoEntity update(UUID id, EstadoPedidoEntity estadoPedido) {
        EstadoPedidoEntity existing = checkEstadoPedidoExists(id);

        checkNombreEstadoPedidoValido(estadoPedido.getNombre());

        if (!existing.getNombre().equalsIgnoreCase(estadoPedido.getNombre())) {
            checkEstadoPedidoNameNotExists(estadoPedido.getNombre());
        }

        existing.setNombre(estadoPedido.getNombre());

        return estadoPedidoRepository.save(existing);
    }

    @Override
    public EstadoPedidoEntity delete(UUID id) {
        EstadoPedidoEntity existing = checkEstadoPedidoExists(id);
        estadoPedidoRepository.deleteById(id);
        return existing;
    }

    @Override
    public EstadoPedidoEntity getById(UUID id) {
        return checkEstadoPedidoExists(id);
    }

    @Override
    public List<EstadoPedidoEntity> getAll() {
        List<EstadoPedidoEntity> estados = (List<EstadoPedidoEntity>) estadoPedidoRepository.findAll();
        if (estados.isEmpty()) {
            throw new NoDataException();
        }
        return estados;
    }

    // Validaciones privadas
    private void checkNombreEstadoPedidoValido(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NameNullException("nombre");
        }

        // Si contiene números o es solo números
        if (nombre.matches("^\\d+$") || nombre.matches(".*\\d.*")) {
            throw new NameNumberException("nombre");
        }
    }

    private EstadoPedidoEntity checkEstadoPedidoExists(UUID id) {
        return estadoPedidoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El EstadoPedido con el ID: " + id + " no existe"));
    }

    private void checkEstadoPedidoNameNotExists(String nameEstadoPedido) {
        estadoPedidoRepository.findByNombreIgnoreCase(nameEstadoPedido)
            .ifPresent(existingEstadoPedido -> {
                throw new DuplicatedNameException(nameEstadoPedido);
            });
    }
}
