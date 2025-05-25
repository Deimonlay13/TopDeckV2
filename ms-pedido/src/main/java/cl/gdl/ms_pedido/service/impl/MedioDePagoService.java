package cl.gdl.ms_pedido.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.gdl.ms_pedido.dto.MedioDePagoDTO;
import cl.gdl.ms_pedido.errors.DuplicatedNameException;
import cl.gdl.ms_pedido.errors.NameNullException;
import cl.gdl.ms_pedido.errors.NoDataException;
import cl.gdl.ms_pedido.errors.NotFoundException;
import cl.gdl.ms_pedido.repository.IMedioDePagoRepository;
import cl.gdl.ms_pedido.service.IMedioDePagoService;

@Service
public class MedioDePagoService implements IMedioDePagoService{

    @Autowired
    IMedioDePagoRepository medioDePagoRepository;

    @Override
    public MedioDePagoDTO insert(MedioDePagoDTO medioDePago) {
        checkNameMedioDePagoNotNullOrEmpty(medioDePago.getNameMedioDePago());

        checkMedioDePagoNameNotExists(medioDePago.getNameMedioDePago());

        return medioDePagoRepository.save(medioDePago);
    }

    
    @Override
    public MedioDePagoDTO update(UUID id, MedioDePagoDTO medioDePago) {
        checkMedioDePagoExists(id);

        checkNameMedioDePagoNotNullOrEmpty(medioDePago.getNameMedioDePago());

        checkMedioDePagoNameNotExists(medioDePago.getNameMedioDePago());

        medioDePago.setIdMedioDePago(id);
        return medioDePagoRepository.save(medioDePago);
    }

    @Override
    public MedioDePagoDTO delete(UUID id) {
        checkMedioDePagoExists(id);

        medioDePagoRepository.deleteById(id);
        return null;
    }

    @Override
    public MedioDePagoDTO getById(UUID id) {
        checkMedioDePagoExists(id);
        return medioDePagoRepository.findById(id).get();
    }

    @Override
    public List<MedioDePagoDTO> getAll() {
        List<MedioDePagoDTO> mediosDePagos = (List<MedioDePagoDTO>) medioDePagoRepository.findAll();
        if (mediosDePagos.isEmpty()) {
            throw new NoDataException();
        }
        return mediosDePagos;
    }

    private void checkMedioDePagoNameNotExists(String nameMedioDePago) {
        medioDePagoRepository.findByNameMedioDePagoIgnoreCase(nameMedioDePago)
                .ifPresent(existingMedioDePago -> {
                    throw new DuplicatedNameException(nameMedioDePago);
                });
    }

    private void checkNameMedioDePagoNotNullOrEmpty(String nameMedioDePago) {
        if (nameMedioDePago == null || nameMedioDePago.trim().isEmpty()) {
            throw new NameNullException("nameMedioDePago");
        }
    }

    private MedioDePagoDTO checkMedioDePagoExists(UUID id) {
        return medioDePagoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El Medio De Pago con el ID: " + id + " no existe"));
    }

}
