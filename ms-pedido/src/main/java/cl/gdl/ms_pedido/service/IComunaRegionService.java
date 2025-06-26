package cl.gdl.ms_pedido.service;

import java.util.List;

import cl.gdl.ms_pedido.dto.ComunaDTO;
import cl.gdl.ms_pedido.dto.RegionDTO;

public interface IComunaRegionService {

    RegionDTO getRegionById(Long id);

    ComunaDTO getComunaById(Long id);

    List<RegionDTO> getAllRegiones();

    List<ComunaDTO> getComunasByRegion(Long idRegion);

}
