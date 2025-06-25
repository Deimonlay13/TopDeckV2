package cl.gdl.ms_pedido.controller;

import cl.gdl.ms_pedido.service.impl.DireccionServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import cl.gdl.ms_pedido.dto.DireccionDTO;


@RestController
@RequestMapping("/api/direcciones")
public class DireccionController {

    @Autowired
    private  DireccionServiceImpl direccionService;

    @PostMapping("/insert")
    public DireccionDTO insert(@RequestBody DireccionDTO direccion) {
        return direccionService.insert(direccion);
    }

    @PutMapping("/update/{id}")
    public DireccionDTO update(@PathVariable UUID id, @RequestBody DireccionDTO direccion) {
        return direccionService.update(id, direccion);
    }

    @DeleteMapping("/delete/{id}")
    public DireccionDTO delete(@PathVariable UUID id) {
        return direccionService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public DireccionDTO getById(@PathVariable UUID id) {
        return direccionService.getById(id);
    }

    @GetMapping
    public List<DireccionDTO> getAll() {
        return direccionService.getAll();
    }

}





