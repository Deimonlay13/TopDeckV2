package cl.gdl.ms_pedido.Mapper;

import cl.gdl.ms_pedido.dto.DireccionDTO;
import cl.gdl.ms_pedido.entity.DireccionEntity;

public class DireccionMapper {

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

private DireccionEntity toEntity(DireccionDTO dto) {
    DireccionEntity entity = new DireccionEntity();
    entity.setIdDireccion(dto.getIdDireccion());
    entity.setIdPersona(dto.getIdPersona());
    entity.setIdComuna(dto.getIdComuna());
    entity.setIdRegion(dto.getIdRegion());
    entity.setDireccion(dto.getDireccion());
    entity.setTelefono(dto.getTelefono());
    return entity;
}


}
