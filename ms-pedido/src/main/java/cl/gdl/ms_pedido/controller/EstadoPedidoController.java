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

import cl.gdl.ms_pedido.dto.EstadoPedidoDTO;
import cl.gdl.ms_pedido.service.IEstadoPedidoService;


@RestController
@RequestMapping("/api/estado-pedido")
public class EstadoPedidoController {
    
    @Autowired
    private IEstadoPedidoService estadoPedidoService;

    @PostMapping("/insert")
    public EstadoPedidoDTO insert(@RequestBody EstadoPedidoDTO estadoPedido) {
        return estadoPedidoService.insert(estadoPedido);
    }

    @PutMapping("/update/{id}")
    public EstadoPedidoDTO update(@PathVariable UUID id, @RequestBody EstadoPedidoDTO estadoPedido) {
        return estadoPedidoService.update(id, estadoPedido);
    }

    @DeleteMapping("/delete/{id}")
    public EstadoPedidoDTO delete(@PathVariable UUID id) {
        return estadoPedidoService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public EstadoPedidoDTO getById(@PathVariable UUID id) {
        return estadoPedidoService.getById(id);
    }

    @GetMapping
    public List<EstadoPedidoDTO> getAll() {
        return estadoPedidoService.getAll();
    }
}