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


import cl.gdl.ms_pedido.entity.EstadoPedidoEntity;
import cl.gdl.ms_pedido.service.IEstadoPedidoService;


@RestController
@RequestMapping("/api/estado-pedido")
public class EstadoPedidoController {
    
    @Autowired
    private IEstadoPedidoService estadoPedidoService;

    @PostMapping("/insert")
    public EstadoPedidoEntity insert(@RequestBody EstadoPedidoEntity estadoPedido) {
        return estadoPedidoService.insert(estadoPedido);
    }

    @PutMapping("/update/{id}")
    public EstadoPedidoEntity update(@PathVariable UUID id, @RequestBody EstadoPedidoEntity estadoPedido) {
        return estadoPedidoService.update(id, estadoPedido);
    }

    @DeleteMapping("/delete/{id}")
    public EstadoPedidoEntity delete(@PathVariable UUID id) {
        return estadoPedidoService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public EstadoPedidoEntity getById(@PathVariable UUID id) {
        return estadoPedidoService.getById(id);
    }

    @GetMapping
    public List<EstadoPedidoEntity> getAll() {
        return estadoPedidoService.getAll();
    }
}