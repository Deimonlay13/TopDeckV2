package cl.gdl.ms_pedido.controller;

import cl.gdl.ms_pedido.dto.DireccionDTO;
import cl.gdl.ms_pedido.service.DireccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/direcciones")
public class DireccionController {

    private final DireccionService direccionService;

    @GetMapping
    public ResponseEntity<List<DireccionDTO>> getAll() {
        return ResponseEntity.ok(direccionService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DireccionDTO> getById(@PathVariable String id) {
        DireccionDTO direccion = direccionService.obtenerPorId(id);
        return ResponseEntity.ok(direccion); // Considera controlar si es null
    }

    @PostMapping
    public ResponseEntity<DireccionDTO> insert(@Valid @RequestBody DireccionDTO dto) {
        return ResponseEntity.ok(direccionService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DireccionDTO> update(@PathVariable String id, @Valid @RequestBody DireccionDTO dto) {
        return ResponseEntity.ok(direccionService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        direccionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}





