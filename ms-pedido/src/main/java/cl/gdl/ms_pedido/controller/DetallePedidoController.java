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

import cl.gdl.ms_pedido.dto.DetallePedidoCartaDTO;
import cl.gdl.ms_pedido.entity.DetallePedidoEntity;
import cl.gdl.ms_pedido.service.IDetallePedidoService;

@RestController
@RequestMapping("/api/detalle-pedido")
public class DetallePedidoController {
    
    @Autowired
    private IDetallePedidoService detallePedidoService;

    @PostMapping("/insert")
    public List<DetallePedidoEntity> insert(@RequestBody List<DetallePedidoEntity> detallePedido) {
        return detallePedidoService.insert(detallePedido);
    }
    

    @PutMapping("/update/{id}")
    public DetallePedidoEntity update(@PathVariable UUID id, @RequestBody DetallePedidoEntity detallePedido) {
        return detallePedidoService.update(id, detallePedido);
    }

    @DeleteMapping("/delete/{id}")
    public DetallePedidoEntity delete(@PathVariable UUID id) {
        return detallePedidoService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public DetallePedidoEntity getById(@PathVariable UUID id) {
        return detallePedidoService.getById(id);
    }

    @GetMapping
    public List<DetallePedidoEntity> getAll() {
        return detallePedidoService.getAll();
    }

    @GetMapping("/pedido/{idPedido}")
    public List<DetallePedidoEntity> getByPedidoId(@PathVariable UUID idPedido) {
        return detallePedidoService.getByPedidoId(idPedido);
    }
    

    @GetMapping("/pedido/{idPedido}/con-cartas")
    public List<DetallePedidoCartaDTO> getDetallesConCartasPorPedido(@PathVariable UUID idPedido) {
        return detallePedidoService.getDetallesConCartasPorPedido(idPedido);
    }
}

