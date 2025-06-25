package cl.gdl.ms_pedido.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.gdl.ms_pedido.dto.RegionDTO;
import cl.gdl.ms_pedido.service.IComunaRegionService;

@RestController
@RequestMapping("/api")
public class ComunaRegionController {

    @Autowired
    private IComunaRegionService comunaRegionService;

    @GetMapping("/regiones/{id}")
    public RegionDTO getById(@PathVariable String id) {
        return comunaRegionService.getRegionById(id);
    }

}
