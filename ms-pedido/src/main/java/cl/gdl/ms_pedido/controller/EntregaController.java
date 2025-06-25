package cl.gdl.ms_pedido.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.gdl.ms_pedido.dto.EntregaDTO;
import cl.gdl.ms_pedido.service.IEntregaService;

@RestController
@RequestMapping("/api/entrega")
public class EntregaController {
    
    @Autowired
    private IEntregaService entregaService;

    @PostMapping("/insert")
    public EntregaDTO insert(@RequestBody EntregaDTO entrega) {
        return entregaService.insert(entrega);
    }

    @PutMapping("/update/{id}")
    public EntregaDTO update(@PathVariable UUID id, @RequestBody EntregaDTO entrega) {
        return entregaService.update(id, entrega);
    }

    @DeleteMapping("/delete/{id}")
    public EntregaDTO delete(@PathVariable UUID id) {
        return entregaService.delete(id);
    }

    @GetMapping("/{id}")
    public EntregaDTO getById(@PathVariable UUID id) {
        return entregaService.getById(id);
    }

    @GetMapping
    public List<EntregaDTO> getAll() {
        return entregaService.getAll();
    }
}