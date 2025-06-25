package cl.gdl.ms_pedido.service;

import cl.gdl.ms_pedido.dto.ComunaDTO;
import cl.gdl.ms_pedido.dto.RegionDTO;

public interface IComunaRegionService {

    RegionDTO getRegionById(String id);

    ComunaDTO getComunaById(String id);
}
