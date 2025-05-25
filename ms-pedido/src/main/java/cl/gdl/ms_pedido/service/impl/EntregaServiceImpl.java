package cl.gdl.ms_pedido.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.gdl.ms_pedido.dto.EntregaDTO;
import cl.gdl.ms_pedido.errors.DuplicatedNameException;
import cl.gdl.ms_pedido.errors.NameNullException;
import cl.gdl.ms_pedido.errors.NoDataException;
import cl.gdl.ms_pedido.errors.NotFoundException;
import cl.gdl.ms_pedido.repository.IEntregaRepository;
import cl.gdl.ms_pedido.service.IEntregaService;

@Service
public class EntregaServiceImpl implements IEntregaService{
    @Autowired
    private IEntregaRepository entregaRepository;

    @Override
    public EntregaDTO insert(EntregaDTO entrega) {
        checkNameEntregaNotNullOrEmpty(entrega.getEntrega());

        checkEntregaNameNotExists(entrega.getEntrega());

        return entregaRepository.save(entrega);
    }

    @Override
    public EntregaDTO update(UUID id, EntregaDTO entrega) {
        checkEntregaExists(id);

        checkNameEntregaNotNullOrEmpty(entrega.getEntrega());

        checkEntregaNameNotExists(entrega.getEntrega());

        entrega.setIdEntrega(id);
        return entregaRepository.save(entrega);
    }

    @Override
    public EntregaDTO delete(UUID id) {
        checkEntregaExists(id);

        entregaRepository.deleteById(id);
        return null;
    }

    @Override
    public EntregaDTO getById(UUID id) {
        checkEntregaExists(id);
        return entregaRepository.findById(id).get();
    }

    @Override
    public List<EntregaDTO> getAll() {
        List<EntregaDTO> entregas = (List<EntregaDTO>) entregaRepository.findAll();
        if (entregas.isEmpty()) {
            throw new NoDataException();
        }
        return entregas;
    }

    private void checkNameEntregaNotNullOrEmpty(String nameEntrega) {
        if (nameEntrega == null || nameEntrega.trim().isEmpty()) {
            throw new NameNullException("nameEstadoPedido");
        }
    }

    private EntregaDTO checkEntregaExists(UUID id) {
        return entregaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El Medio De Pago con el ID: " + id + " no existe"));
    }

    private void checkEntregaNameNotExists(String nameEntrega) {
        entregaRepository.findByNameEntregaIgnoreCase(nameEntrega)
                .ifPresent(existingEntrega -> {
                    throw new DuplicatedNameException(nameEntrega);
                });
    }
}
