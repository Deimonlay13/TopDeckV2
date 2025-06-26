package cl.gdl.ms_pedido.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.gdl.ms_pedido.dto.EntregaDTO;
import cl.gdl.ms_pedido.entity.EntregaEntity;
import cl.gdl.ms_pedido.errors.DuplicatedNameException;
import cl.gdl.ms_pedido.errors.NameNullException;
import cl.gdl.ms_pedido.errors.NoDataException;
import cl.gdl.ms_pedido.errors.NotFoundException;
import cl.gdl.ms_pedido.repository.IEntregaRepository;
import cl.gdl.ms_pedido.service.IEntregaService;

@Service
public class EntregaServiceImpl implements IEntregaService {
    @Autowired
    private IEntregaRepository entregaRepository;

    @Override
    public EntregaDTO insert(EntregaDTO dto) {
        checkDescripcionNotNullOrEmpty(dto.getEntrega());
        checkDescripcionNotExists(dto.getEntrega());
        checkDescripcionNotExists(dto.getEntrega());

        EntregaEntity entidad = new EntregaEntity();
        entidad.setDescripcion(dto.getEntrega());

        entidad = entregaRepository.save(entidad);
        return toDto(entidad);
    }

    @Override
    public EntregaDTO update(UUID id, EntregaDTO entrega) {
        EntregaEntity existing = checkEntregaExists(id);

        checkDescripcionNotNullOrEmpty(entrega.getEntrega());

        // Invertimos para evitar NullPointerException
        if (!entrega.getEntrega().equalsIgnoreCase(existing.getDescripcion())) {
            checkDescripcionNotExists(entrega.getEntrega());
        }

        existing.setDescripcion(entrega.getEntrega());

        EntregaEntity updated = entregaRepository.save(existing);
        return toDto(updated);
    }

    @Override
    public EntregaDTO delete(UUID id) {
        EntregaEntity existing = checkEntregaExists(id); // Validamos que exista

        entregaRepository.deleteById(id); // Borramos por id

        return toDto(existing); // Retornamos la entidad que borramos
    }

    @Override
    public EntregaDTO getById(UUID id) {
        EntregaEntity entrega = checkEntregaExists(id);
        return toDto(entrega); // Si no existe, lanza excepción
    }

    @Override
    public List<EntregaDTO> getAll() {
        List<EntregaEntity> entregas = (List<EntregaEntity>) entregaRepository.findAll();
        if (entregas.isEmpty()) {
            throw new NoDataException();
        }
        return entregas.stream()
                .map(this::toDto)
                .toList();
    }

    // Validaciones privadas
    private void checkDescripcionNotNullOrEmpty(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new NameNullException("descripcion");
        }
    }

    private EntregaEntity checkEntregaExists(UUID id) {
        return entregaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La Entrega con el ID: " + id + " no existe"));
    }

    private void checkDescripcionNotExists(String descripcion) {
        entregaRepository.findByDescripcionIgnoreCase(descripcion)
                .ifPresent(e -> {
                    throw new DuplicatedNameException(descripcion);
                });
    }

    private EntregaDTO toDto(EntregaEntity entity) {
        EntregaDTO dto = new EntregaDTO();
        dto.setIdEntrega(entity.getId());
        dto.setEntrega(entity.getDescripcion());
        return dto;
    }
}


