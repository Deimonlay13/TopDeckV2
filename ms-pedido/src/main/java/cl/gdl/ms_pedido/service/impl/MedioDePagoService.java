package cl.gdl.ms_pedido.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.gdl.ms_pedido.entity.MedioDePagoEntity;
import cl.gdl.ms_pedido.errors.DuplicatedNameException;
import cl.gdl.ms_pedido.errors.NameNullException;
import cl.gdl.ms_pedido.errors.NameNumberException;
import cl.gdl.ms_pedido.errors.NoDataException;
import cl.gdl.ms_pedido.errors.NotFoundException;
import cl.gdl.ms_pedido.repository.IMedioDePagoRepository;
import cl.gdl.ms_pedido.service.IMedioDePagoService;

@Service
public class MedioDePagoService implements IMedioDePagoService {

    @Autowired
    IMedioDePagoRepository medioDePagoRepository;

    @Override
    public MedioDePagoEntity insert(MedioDePagoEntity medioDePago) {
        checkNombreNotNullOrEmpty(medioDePago.getNombre());
        checkNombreSinNumeros(medioDePago.getNombre());
        checkNombreNotExists(medioDePago.getNombre());

        return medioDePagoRepository.save(medioDePago);
    }

    @Override
    public MedioDePagoEntity update(UUID id, MedioDePagoEntity medioDePago) {
        MedioDePagoEntity existing = checkExists(id);

        checkNombreNotNullOrEmpty(medioDePago.getNombre());
        checkNombreSinNumeros(medioDePago.getNombre());

        if (!existing.getNombre().equalsIgnoreCase(medioDePago.getNombre())) {
            checkNombreNotExists(medioDePago.getNombre());
        }

        existing.setNombre(medioDePago.getNombre());

        return medioDePagoRepository.save(existing);
    }

    @Override
    public MedioDePagoEntity delete(UUID id) {
        MedioDePagoEntity existing = checkExists(id);
        medioDePagoRepository.deleteById(id);
        return existing;
    }

    @Override
    public MedioDePagoEntity getById(UUID id) {
        return checkExists(id);
    }

    @Override
    public List<MedioDePagoEntity> getAll() {
        List<MedioDePagoEntity> medios = (List<MedioDePagoEntity>) medioDePagoRepository.findAll();
        if (medios.isEmpty()) {
            throw new NoDataException();
        }
        return medios;
    }

    // Validaciones privadas
    private void checkNombreNotNullOrEmpty(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NameNullException("nombre");
        }
    }

    private void checkNombreSinNumeros(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NameNullException("nombre");
        }

        // Si es completamente numérico o contiene números
        if (nombre.matches("^\\d+$") || nombre.matches(".*\\d.*")) {
            throw new NameNumberException("nombre");
        }
    }

    private void checkNombreNotExists(String nombre) {
        medioDePagoRepository.findByNombreIgnoreCase(nombre)
            .ifPresent(e -> {
                throw new DuplicatedNameException(nombre);
            });
    }

    private MedioDePagoEntity checkExists(UUID id) {
        return medioDePagoRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("El Medio De Pago con el ID: " + id + " no existe"));
    }
}
