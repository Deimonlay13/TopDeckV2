package cl.gdl.ms_pedido.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.gdl.ms_pedido.dto.EstadoPedidoDTO;
import cl.gdl.ms_pedido.entity.EstadoPedidoEntity;
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
    public EstadoPedidoDTO insert(EstadoPedidoDTO dto) {
        checkNombreNotExists(dto.getNameEstadoPedido());

        // Creamos entidad sin asignar ID manualmente
        EstadoPedidoEntity entidad = new EstadoPedidoEntity();
        entidad.setNombre(dto.getNameEstadoPedido());

        entidad = estadoPedidoRepository.save(entidad); // aquí se genera el UUID

        return toDto(entidad);
    }

    @Override
public EstadoPedidoDTO update(UUID id, EstadoPedidoDTO dto) {
    EstadoPedidoEntity existing = checkEstadoPedidoExists(id);

    // Validar que el nombre no sea nulo o vacío
    checkNameEstadoPedidoNotNullOrEmpty(dto.getNameEstadoPedido());

    // Si el nombre cambió, validar que no exista otro igual
    if (!existing.getNombre().equalsIgnoreCase(dto.getNameEstadoPedido())) {
        checkEstadoPedidoNameNotExists(dto.getNameEstadoPedido());
    }

    // Actualizar entidad con los datos del DTO
    existing.setNombre(dto.getNameEstadoPedido());

    // Guardar y mapear a DTO para respuesta
    EstadoPedidoEntity updated = estadoPedidoRepository.save(existing);
    return toDto(updated);
}

    private EstadoPedidoDTO toDto(EstadoPedidoEntity entity) {
        EstadoPedidoDTO dto = new EstadoPedidoDTO();
        dto.setIdEstadoPedido(entity.getId());
        dto.setNameEstadoPedido(entity.getNombre());
        return dto;
    }

     @Override
    public EstadoPedidoDTO delete(UUID id) {
        EstadoPedidoEntity existing = checkEstadoPedidoExists(id);
        estadoPedidoRepository.deleteById(id);
        return toDto(existing);
    }    

     @Override
    public EstadoPedidoDTO getById(UUID id) {
        EstadoPedidoEntity entity = checkEstadoPedidoExists(id);
        return toDto(entity);
    }  

     @Override
public List<EstadoPedidoDTO> getAll() {
    List<EstadoPedidoEntity> entidades = new ArrayList<>();
    estadoPedidoRepository.findAll().forEach(entidades::add);

    if (entidades.isEmpty()) {
        throw new NoDataException();
    }

    return entidades.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
}

    private void checkNameEstadoPedidoNotNullOrEmpty(String nameEstadoPedido) {
    if (nameEstadoPedido == null || nameEstadoPedido.trim().isEmpty()) {
        throw new NameNullException("nameEstadoPedido");
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
    private void checkNombreNotExists(String nombre) {
        estadoPedidoRepository.findByNombreIgnoreCase(nombre)
            .ifPresent(e -> {
                throw new DuplicatedNameException(nombre);
            });
    }
}