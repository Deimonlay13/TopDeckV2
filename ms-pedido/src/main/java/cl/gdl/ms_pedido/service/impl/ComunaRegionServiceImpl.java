package cl.gdl.ms_pedido.service.impl;

import cl.gdl.ms_pedido.dto.ComunaDTO;
import cl.gdl.ms_pedido.dto.RegionDTO;
import cl.gdl.ms_pedido.entity.ComunaEntity;
import cl.gdl.ms_pedido.entity.RegionEntity;
import cl.gdl.ms_pedido.repository.IComunaRepository;
import cl.gdl.ms_pedido.repository.IRegionRepository;
import cl.gdl.ms_pedido.service.IComunaRegionService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComunaRegionServiceImpl implements IComunaRegionService {

    private final IRegionRepository regionRepository;
    private final IComunaRepository comunaRepository;

    public ComunaRegionServiceImpl(IRegionRepository regionRepository, IComunaRepository comunaRepository) {
        this.regionRepository = regionRepository;
        this.comunaRepository = comunaRepository;
    }

    @Override
    public List<RegionDTO> getAllRegiones() {
        return regionRepository.findAll().stream().map(region -> {
            RegionDTO dto = new RegionDTO();
            dto.setCodigoRegion(region.getId());
            dto.setNombreRegion(region.getNombre());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ComunaDTO> getComunasByRegion(Long idRegion) {
        return comunaRepository.findByRegionId(idRegion).stream().map(comuna -> {
            ComunaDTO dto = new ComunaDTO();
            dto.setCodigoComuna(comuna.getId());
            dto.setNombreComuna(comuna.getNombre());
            dto.setRegionId(comuna.getRegion().getId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public RegionDTO getRegionById(Long id) {
        RegionEntity region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Region no encontrada"));
        RegionDTO dto = new RegionDTO(region.getId(), region.getNombre());
        return dto;
    }

    @Override
    public ComunaDTO getComunaById(Long id) {
        ComunaEntity comuna = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada"));
        ComunaDTO dto = new ComunaDTO(comuna.getId(), comuna.getNombre(), comuna.getRegion().getId());
        return dto;
    }
}