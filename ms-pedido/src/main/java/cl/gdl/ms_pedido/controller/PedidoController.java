package cl.gdl.ms_pedido.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.gdl.ms_pedido.dto.PedidoConUsuarioDTO;
import cl.gdl.ms_pedido.entity.PedidoEntity;
import cl.gdl.ms_pedido.service.IPedidoService;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {
    @Autowired
    IPedidoService pedidoService;

    
    @PostMapping
    public ResponseEntity<PedidoEntity> createPedido(@RequestBody PedidoEntity pedido) {
        PedidoEntity nuevoPedido = pedidoService.insert(pedido);
        return ResponseEntity.status(201).body(nuevoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoEntity> updatePedido(@PathVariable UUID id, @RequestBody PedidoEntity pedido) {
        PedidoEntity pedidoActualizado = pedidoService.update(id, pedido);
        return ResponseEntity.ok(pedidoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable UUID id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoEntity> getPedidoById(@PathVariable UUID id) {
        PedidoEntity pedido = pedidoService.getById(id);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping
    public ResponseEntity<List<PedidoEntity>> getAllPedidos() {
        List<PedidoEntity> pedidos = pedidoService.getAll();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}/con-usuario")
    public ResponseEntity<PedidoConUsuarioDTO> getPedidoConUsuario(
            @PathVariable UUID id,
            @AuthenticationPrincipal(expression = "tokenValue") String token) {
        // token aquí si lo necesitas
        PedidoConUsuarioDTO dto = pedidoService.getPedidoConUsuario(id);
        return ResponseEntity.ok(dto);
    }    

}