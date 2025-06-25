package cl.gdl.ms_pedido.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.gdl.ms_pedido.entity.EntregaEntity;
import cl.gdl.ms_pedido.errors.DuplicatedNameException;
import cl.gdl.ms_pedido.errors.NameNullException;
import cl.gdl.ms_pedido.errors.NameNumberException;
import cl.gdl.ms_pedido.errors.NoDataException;
import cl.gdl.ms_pedido.errors.NotFoundException;
import cl.gdl.ms_pedido.repository.IEntregaRepository;
import cl.gdl.ms_pedido.service.IEntregaService;

@Service
public class EntregaServiceImpl implements IEntregaService {

    @Autowired
    private IEntregaRepository entregaRepository;

    @Override
    public EntregaEntity insert(EntregaEntity entrega) {
        checkDescripcionNotNullOrEmpty(entrega.getDescripcion());
        checkDescripcionSinNumeros(entrega.getDescripcion());
        checkDescripcionNotExists(entrega.getDescripcion());

        return entregaRepository.save(entrega);
    }

    @Override
    public EntregaEntity update(UUID id, EntregaEntity entrega) {
        EntregaEntity existing = checkEntregaExists(id);

        checkDescripcionNotNullOrEmpty(entrega.getDescripcion());
        checkDescripcionSinNumeros(entrega.getDescripcion());

        if (!existing.getDescripcion().equalsIgnoreCase(entrega.getDescripcion())) {
            checkDescripcionNotExists(entrega.getDescripcion());
        }

        existing.setDescripcion(entrega.getDescripcion());

        return entregaRepository.save(existing);
    }

    @Override
    public EntregaEntity delete(UUID id) {
        EntregaEntity existing = checkEntregaExists(id);
        entregaRepository.deleteById(id);
        return existing;
    }

    @Override
    public EntregaEntity getById(UUID id) {
        return checkEntregaExists(id);
    }

    @Override
    public List<EntregaEntity> getAll() {
        List<EntregaEntity> entregas = (List<EntregaEntity>) entregaRepository.findAll();
        if (entregas.isEmpty()) {
            throw new NoDataException();
        }
        return entregas;
    }

    // Validaciones privadas
    private void checkDescripcionNotNullOrEmpty(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new NameNullException("descripcion");
        }
    }

    private void checkDescripcionSinNumeros(String descripcion) {
        if (descripcion.matches("^\\d+$") || descripcion.matches(".*\\d.*")) {
            throw new NameNumberException("descripcion");
        }
    }

    private void checkDescripcionNotExists(String descripcion) {
        entregaRepository.findByDescripcionIgnoreCase(descripcion)
            .ifPresent(e -> {
                throw new DuplicatedNameException(descripcion);
            });
    }

    private EntregaEntity checkEntregaExists(UUID id) {
        return entregaRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("La Entrega con el ID: " + id + " no existe"));
    }
}


