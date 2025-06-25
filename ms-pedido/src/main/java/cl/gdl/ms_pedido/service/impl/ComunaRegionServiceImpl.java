package cl.gdl.ms_pedido.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.gdl.ms_pedido.dto.ComunaDTO;
import cl.gdl.ms_pedido.dto.RegionDTO;
import cl.gdl.ms_pedido.service.IComunaRegionService;

@Service
public class ComunaRegionServiceImpl implements IComunaRegionService{

private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "https://apis.digital.gob.cl/dpa";

    public RegionDTO getRegionById(String codigoRegion) {
        String url = BASE_URL + "/regiones/" + codigoRegion;
        return restTemplate.getForObject(url, RegionDTO.class);
    }

    public ComunaDTO getComunaById(String codigoComuna) {
        String url = BASE_URL + "/comunas/" + codigoComuna;
        return restTemplate.getForObject(url, ComunaDTO.class);
    }

}
