package cl.gdl.ms_pedido.unitary;

import cl.gdl.ms_pedido.dto.MedioDePagoDTO;
import cl.gdl.ms_pedido.entity.MedioDePagoEntity;
import cl.gdl.ms_pedido.errors.DuplicatedNameException;
import cl.gdl.ms_pedido.errors.NameNumberException;
import cl.gdl.ms_pedido.errors.NotFoundException;
import cl.gdl.ms_pedido.errors.NameNullException;
import cl.gdl.ms_pedido.repository.IMedioDePagoRepository;
import cl.gdl.ms_pedido.service.impl.MedioDePagoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MedioDePagoServiceTest {

    private IMedioDePagoRepository repository;
    private MedioDePagoService service;

    @BeforeEach
    void setup() {
        repository = Mockito.mock(IMedioDePagoRepository.class);
        service = new MedioDePagoService(repository);
    }

    @Test
    void insertarMedioDePagoCorrectamente() {
        MedioDePagoDTO dto = new MedioDePagoDTO();
        dto.setNombre("Tarjeta Débito");

        MedioDePagoEntity savedEntity = new MedioDePagoEntity();
        savedEntity.setId(UUID.randomUUID());
        savedEntity.setNombre(dto.getNombre());

        when(repository.findByNombreIgnoreCase("Tarjeta Débito")).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(savedEntity);

        MedioDePagoDTO resultado = service.insert(dto);

        assertNotNull(resultado);
        assertEquals("Tarjeta Débito", resultado.getNombre());
    }

    @Test
    void insertarNombreDuplicadoLanzaExcepcion() {
        MedioDePagoDTO dto = new MedioDePagoDTO();
        dto.setNombre("Transferencia");

        MedioDePagoEntity existente = new MedioDePagoEntity();
        existente.setId(UUID.randomUUID());
        existente.setNombre("Transferencia");

        when(repository.findByNombreIgnoreCase("Transferencia")).thenReturn(Optional.of(existente));

        assertThrows(DuplicatedNameException.class, () -> service.insert(dto));
    }

    @Test
    void insertarNombreConNumerosLanzaExcepcion() {
        MedioDePagoDTO dto = new MedioDePagoDTO();
        dto.setNombre("Efectivo123");

        assertThrows(NameNumberException.class, () -> service.insert(dto));
    }

    @Test
    void insertarNombreNuloOLimpioLanzaExcepcion() {
        MedioDePagoDTO dto = new MedioDePagoDTO();
        dto.setNombre("   "); // espacio vacío

        assertThrows(NameNullException.class, () -> service.insert(dto));
    }
    
    @Test
    void delete_Existente_EliminaYRetornaDTO() {
        UUID id = UUID.randomUUID();
        MedioDePagoEntity entity = new MedioDePagoEntity();
        entity.setId(id);
        entity.setNombre("Tarjeta");

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        doNothing().when(repository).deleteById(id);

        MedioDePagoDTO dto = service.delete(id);

        assertEquals(id, dto.getId());
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void delete_NoExistente_LanzaNotFoundException() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.delete(id));
    }
    
    @Test
    void getById_Existente_RetornaDTO() {
        UUID id = UUID.randomUUID();
        MedioDePagoEntity entity = new MedioDePagoEntity();
        entity.setId(id);
        entity.setNombre("Efectivo");

        when(repository.findById(id)).thenReturn(Optional.of(entity));

        MedioDePagoDTO dto = service.getById(id);

        assertEquals(id, dto.getId());
        assertEquals("Efectivo", dto.getNombre());
    }

    @Test
    void getById_NoExistente_LanzaNotFoundException() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.getById(id));
    }

}
