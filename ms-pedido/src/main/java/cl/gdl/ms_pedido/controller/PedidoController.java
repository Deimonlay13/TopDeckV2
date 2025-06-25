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
import cl.gdl.ms_pedido.dto.PedidoDTO;
import cl.gdl.ms_pedido.service.IPedidoService;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {
    @Autowired
    IPedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> getAllPedidos() {
        List<PedidoDTO> pedidos = pedidoService.getAll();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getPedidoById(@PathVariable UUID id) {
        PedidoDTO pedido = pedidoService.getById(id);
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> createPedido(@RequestBody PedidoDTO dto) {
        PedidoDTO nuevoPedido = pedidoService.insert(dto);
        return ResponseEntity.ok(nuevoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> updatePedido(@PathVariable UUID id, @RequestBody PedidoDTO dto) {
        PedidoDTO pedidoActualizado = pedidoService.update(id, dto);
        return ResponseEntity.ok(pedidoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PedidoDTO> deletePedido(@PathVariable UUID id) {
        PedidoDTO eliminado = pedidoService.delete(id);
        return ResponseEntity.ok(eliminado);
    }

    @GetMapping("/{id}/con-usuario")
    public ResponseEntity<PedidoConUsuarioDTO> getPedidoConUsuario(
            @PathVariable UUID id,
            @AuthenticationPrincipal(expression = "tokenValue") String token) {
        PedidoConUsuarioDTO dto = pedidoService.getPedidoConUsuario(id);
        return ResponseEntity.ok(dto);
    }    
    
}