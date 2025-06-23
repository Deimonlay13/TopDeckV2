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
import cl.gdl.ms_pedido.errors.NoDataException;
import cl.gdl.ms_pedido.errors.NotFoundException;
import cl.gdl.ms_pedido.repository.IMedioDePagoRepository;
import cl.gdl.ms_pedido.service.IMedioDePagoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedioDePagoService implements IMedioDePagoService{

    @Autowired
    IMedioDePagoRepository medioDePagoRepository;

    @Override
    public MedioDePagoDTO insert(MedioDePagoDTO dto) {
        checkNombreNotExists(dto.getNameMedioDePago());

        // Creamos entidad sin asignar ID manualmente
        MedioDePagoEntity entidad = new MedioDePagoEntity();
        entidad.setNombre(dto.getNameMedioDePago());

        entidad = medioDePagoRepository.save(entidad); // aquí se genera el UUID
        log.info("Insertado MedioDePago con ID: {}", entidad.getId());

        return toDto(entidad);
    }

    private MedioDePagoDTO toDto(MedioDePagoEntity entity) {
        MedioDePagoDTO dto = new MedioDePagoDTO();
        dto.setId(entity.getId());
        dto.setNameMedioDePago(entity.getNombre());
        return dto;
    }

    @Override
    public MedioDePagoEntity update(UUID id, MedioDePagoEntity medioDePago) {
        MedioDePagoEntity existing = checkExists(id);

        checkNombreNotNullOrEmpty(medioDePago.getNombre());

        if (!existing.getNombre().equalsIgnoreCase(medioDePago.getNombre())) {
            checkNombreNotExists(medioDePago.getNombre());
        }

        existing.setNombre(medioDePago.getNombre());

        return medioDePagoRepository.save(existing);
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