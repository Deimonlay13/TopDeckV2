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


import cl.gdl.ms_pedido.entity.EntregaEntity;
import cl.gdl.ms_pedido.service.IEntregaService;

@RestController
@RequestMapping("/api/entrega")
public class EntregaController {
    
    @Autowired
    private IEntregaService entregaService;

    @PostMapping("/insert")
    public EntregaEntity insert(@RequestBody EntregaEntity entrega) {
        return entregaService.insert(entrega);
    }

    @PutMapping("/update/{id}")
    public EntregaEntity update(@PathVariable UUID id, @RequestBody EntregaEntity entrega) {
        return entregaService.update(id, entrega);
    }

    @DeleteMapping("/delete/{id}")
    public EntregaEntity delete(@PathVariable UUID id) {
        return entregaService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public EntregaEntity getById(@PathVariable UUID id) {
        return entregaService.getById(id);
    }

    @GetMapping
    public List<EntregaEntity> getAll() {
        return entregaService.getAll();
    }
}