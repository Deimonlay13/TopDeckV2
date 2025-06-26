package cl.gdl.ms_pedido.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.gdl.ms_pedido.dto.ComunaDTO;
import cl.gdl.ms_pedido.dto.RegionDTO;
import cl.gdl.ms_pedido.service.IComunaRegionService;

@RestController
@RequestMapping("/api")
public class ComunaRegionController {

    @Autowired
    private IComunaRegionService comunaRegionService;


    @GetMapping("/regiones")
    public ResponseEntity<List<RegionDTO>> getAllRegiones() {
        return ResponseEntity.ok(comunaRegionService.getAllRegiones());
    }

    @GetMapping("/{idRegion}/comunas")
    public ResponseEntity<List<ComunaDTO>> getComunasByRegion(@PathVariable Long idRegion) {
        return ResponseEntity.ok(comunaRegionService.getComunasByRegion(idRegion));
    }

    @GetMapping("/regiones/{id}")
    public ResponseEntity<RegionDTO> getRegionById(@PathVariable Long id) {
        RegionDTO region = comunaRegionService.getRegionById(id);
        return ResponseEntity.ok(region);
    }

    @GetMapping("/comunas/{id}")
    public ResponseEntity<ComunaDTO> getComunaById(@PathVariable Long id) {
        ComunaDTO comuna = comunaRegionService.getComunaById(id);
        return ResponseEntity.ok(comuna);
    }
}
