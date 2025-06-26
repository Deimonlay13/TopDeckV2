package cl.gdl.ms_pedido.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.gdl.ms_pedido.dto.MedioDePagoDTO;
import cl.gdl.ms_pedido.entity.MedioDePagoEntity;
import cl.gdl.ms_pedido.errors.DuplicatedNameException;
import cl.gdl.ms_pedido.errors.NameNullException;
import cl.gdl.ms_pedido.errors.NameNumberException;
import cl.gdl.ms_pedido.errors.NoDataException;
import cl.gdl.ms_pedido.errors.NotFoundException;
import cl.gdl.ms_pedido.repository.IMedioDePagoRepository;
import cl.gdl.ms_pedido.service.IMedioDePagoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedioDePagoService implements IMedioDePagoService {

    @Autowired
    IMedioDePagoRepository medioDePagoRepository;

    public MedioDePagoService(IMedioDePagoRepository medioDePagoRepository) {
        this.medioDePagoRepository = medioDePagoRepository;
    }

    @Override

    public MedioDePagoDTO insert(MedioDePagoDTO dto) {
        checkNombreNotNullOrEmpty(dto.getNombre());
        checkNombreSinNumeros(dto.getNombre());
        checkNombreNotExists(dto.getNombre());

        MedioDePagoEntity entidad = new MedioDePagoEntity();
        entidad.setNombre(dto.getNombre());

        entidad = medioDePagoRepository.save(entidad);

        return toDto(entidad);
    }

    private MedioDePagoDTO toDto(MedioDePagoEntity entity) {
        MedioDePagoDTO dto = new MedioDePagoDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        return dto;
    }

    @Override
    public MedioDePagoDTO update(UUID id, MedioDePagoDTO medioDePago) {
        MedioDePagoEntity existing = checkExists(id);

        checkNombreNotNullOrEmpty(medioDePago.getNombre());
        checkNombreSinNumeros(medioDePago.getNombre());

        if (!existing.getNombre().equalsIgnoreCase(medioDePago.getNombre())) {
            checkNombreNotExists(medioDePago.getNombre());
        }

        existing.setNombre(medioDePago.getNombre());

        MedioDePagoEntity updated = medioDePagoRepository.save(existing);

        return toDto(updated);
    }

    @Override
    public MedioDePagoDTO delete(UUID id) {
        MedioDePagoEntity existing = checkExists(id);
        medioDePagoRepository.deleteById(id);
        return toDto(existing);
    }

    @Override
    public MedioDePagoDTO getById(UUID id) {
        MedioDePagoEntity entity = checkExists(id);
        return toDto(entity);
    }

    @Override
    public List<MedioDePagoDTO> getAll() {
        List<MedioDePagoEntity> entidades = new ArrayList<>();
        medioDePagoRepository.findAll().forEach(entidades::add);

        if (entidades.isEmpty()) {
            throw new NoDataException();
        }

        return entidades.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Validaciones privadas
    private void checkNombreNotNullOrEmpty(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NameNullException("nombre");
        }
    }

    private void checkNombreSinNumeros(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NameNullException("nombre");
        }

        // Si es completamente numérico o contiene números
        if (nombre.matches("^\\d+$") || nombre.matches(".*\\d.*")) {
            throw new NameNumberException("nombre");
        }
    }

    private void checkNombreNotExists(String nombre) {
        medioDePagoRepository.findByNombreIgnoreCase(nombre)
                .ifPresent(e -> {
                    throw new DuplicatedNameException(nombre);
                });
    }

    private MedioDePagoEntity checkExists(UUID id) {
        return medioDePagoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El Medio De Pago con el ID: " + id + " no existe"));
    }

}
