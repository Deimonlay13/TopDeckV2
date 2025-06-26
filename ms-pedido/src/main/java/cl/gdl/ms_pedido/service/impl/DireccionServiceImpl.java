package cl.gdl.ms_pedido.service.impl;

import cl.gdl.ms_pedido.entity.DireccionEntity;
import cl.gdl.ms_pedido.repository.IDireccionRepository;
import cl.gdl.ms_pedido.service.IDireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

import cl.gdl.ms_pedido.dto.DireccionDTO;

@Service
public class DireccionServiceImpl implements IDireccionService {

    @Autowired
    private IDireccionRepository direccionRepository;

    @Override
    public DireccionDTO insert(DireccionDTO dto) {
        DireccionEntity entity = new DireccionEntity();
        entity.setIdPersona(dto.getIdPersona());
        entity.setIdComuna(dto.getIdComuna());
        entity.setIdRegion(dto.getIdRegion());
        entity.setDireccion(dto.getDireccion());
        entity.setTelefono(dto.getTelefono());
        entity = direccionRepository.save(entity);
        return toDto(entity);
    }

    @Override
    public DireccionDTO update(UUID id, DireccionDTO dto) {
        DireccionEntity entity = direccionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Dirección con ID " + id + " no encontrada"));

        // Actualizamos solo los campos modificables
        entity.setIdPersona(dto.getIdPersona());
        entity.setIdComuna(dto.getIdComuna());
        entity.setIdRegion(dto.getIdRegion());
        entity.setDireccion(dto.getDireccion());
        entity.setTelefono(dto.getTelefono());

        DireccionEntity updated = direccionRepository.save(entity);
        return toDto(updated);
    }

    @Override
    public DireccionDTO delete(UUID id) {
        DireccionEntity entity = direccionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Dirección con ID " + id + " no encontrada para eliminar"));

        direccionRepository.deleteById(id);
        return toDto(entity);
    }

    @Override
    public DireccionDTO getById(UUID id) {
        DireccionEntity entity = direccionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Dirección con ID " + id + " no encontrada"));
        return toDto(entity);
    }

    @Override
    public List<DireccionDTO> getAll() {
        List<DireccionEntity> entidades = (List<DireccionEntity>) direccionRepository.findAll();
        return entidades.stream().map(this::toDto).toList();
    }

    // Métodos de mapeo: DTO ↔ Entity (puedes moverlos a una clase util o mapper después)
    private DireccionDTO toDto(DireccionEntity entity) {
        DireccionDTO dto = new DireccionDTO();
        dto.setIdDireccion(entity.getIdDireccion());
        dto.setIdPersona(entity.getIdPersona());
        dto.setIdComuna(entity.getIdComuna());
        dto.setIdRegion(entity.getIdRegion());
        dto.setDireccion(entity.getDireccion());
        dto.setTelefono(entity.getTelefono());
        return dto;
    }

}




