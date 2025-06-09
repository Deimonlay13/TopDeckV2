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
import cl.gdl.ms_pedido.dto.PedidoDTO;
import cl.gdl.ms_pedido.service.IPedidoService;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {
    @Autowired
    IPedidoService pedidoService;

    @PostMapping("/insert")
    public PedidoDTO insert(@RequestBody PedidoDTO pedido){
        return pedidoService.insert(pedido);
    }

    @PutMapping("/update/{id}")
    public PedidoDTO update(@PathVariable UUID id, @RequestBody PedidoDTO pedido){
        return pedidoService.update(id,pedido);
    }

    @DeleteMapping("/delete/{id}")
    public PedidoDTO delete(@PathVariable UUID id){
        return pedidoService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public PedidoDTO getById(@PathVariable UUID id){
        return  pedidoService.getById(id);
    }

    @GetMapping
    public List<PedidoDTO> getAll(){
        return pedidoService.getAll();
    }
}
