package cl.gdl.ms_pedido.service.impl;

import cl.gdl.ms_pedido.entity.DireccionEntity;
import cl.gdl.ms_pedido.errors.DuplicatedNameException;
import cl.gdl.ms_pedido.errors.NameNullException;
import cl.gdl.ms_pedido.errors.NoDataException;
import cl.gdl.ms_pedido.errors.NotFoundException;
import cl.gdl.ms_pedido.repository.IDireccionRepository;
import cl.gdl.ms_pedido.service.IDireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DireccionServiceImpl implements IDireccionService {

    @Autowired
    private IDireccionRepository direccionRepository;

    @Override
    public DireccionEntity insert(DireccionEntity direccion) {
        checkDireccionNotNullOrEmpty(direccion.getDireccion());
        checkDireccionNotExists(direccion.getDireccion());
        return direccionRepository.save(direccion);
    }

    @Override
    public DireccionEntity update(UUID id, DireccionEntity direccion) {
        DireccionEntity existente = checkDireccionExists(id);

        checkDireccionNotNullOrEmpty(direccion.getDireccion());

        if (!existente.getDireccion().equalsIgnoreCase(direccion.getDireccion())) {
            checkDireccionNotExists(direccion.getDireccion());
        }

        existente.setIdPersona(direccion.getIdPersona());
        existente.setIdComuna(direccion.getIdComuna());
        existente.setDireccion(direccion.getDireccion());
        existente.setTelefono(direccion.getTelefono());

        return direccionRepository.save(existente);
    }

    @Override
    public DireccionEntity delete(UUID id) {
        DireccionEntity existente = checkDireccionExists(id);
        direccionRepository.deleteById(id);
        return existente;
    }

    @Override
    public DireccionEntity getById(UUID id) {
        return checkDireccionExists(id);
    }

    @Override
    public List<DireccionEntity> getAll() {
        List<DireccionEntity> direcciones = (List<DireccionEntity>) direccionRepository.findAll();
        if (direcciones.isEmpty()) {
            throw new NoDataException();
        }
        return direcciones;
    }

    // ---------------------- Validaciones privadas ------------------------

    private void checkDireccionNotNullOrEmpty(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new NameNullException("direccion");
        }
    }

    private DireccionEntity checkDireccionExists(UUID id) {
        return direccionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La dirección con ID: " + id + " no existe"));
    }

    private void checkDireccionNotExists(String direccion) {
        direccionRepository.findByDireccionIgnoreCase(direccion)
                .ifPresent(d -> {
                    throw new DuplicatedNameException(direccion);
                });
    }
}


