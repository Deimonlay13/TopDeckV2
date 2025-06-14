package cl.gdl.ms_pedido.service.impl;

import java.math.BigDecimal;
// import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.gdl.ms_pedido.entity.DetallePedidoEntity;
// import cl.gdl.ms_pedido.errors.DuplicatedNameException;
// import cl.gdl.ms_pedido.errors.NameNullException;
import cl.gdl.ms_pedido.errors.NoDataException;
import cl.gdl.ms_pedido.errors.NotFoundException;
import cl.gdl.ms_pedido.repository.IDetallepedidoRepository;
import cl.gdl.ms_pedido.service.IDetallePedidoService;

@Service
public class DetallePedidoServiceimpl implements IDetallePedidoService {

    @Autowired
    private IDetallepedidoRepository detallePedidoRepository;

    @Override
    public List<DetallePedidoEntity> insert(List<DetallePedidoEntity> detalles) {
        for (DetallePedidoEntity d : detalles) {
            BigDecimal subtotal = d.getPrecioUnitario().multiply(BigDecimal.valueOf(d.getCantidad()));
            d.setSubtotal(subtotal);
        }
        return (List<DetallePedidoEntity>) detallePedidoRepository.saveAll(detalles);
    }
    @Override
public DetallePedidoEntity update(UUID id, DetallePedidoEntity detalle) {
    DetallePedidoEntity existing = checkEntregaExists(id);  // valida que exista

    //checkDescripcionNotNullOrEmpty(detalle.getId());

    return detallePedidoRepository.save(existing);
}

    @Override
    public DetallePedidoEntity delete(UUID id) {
    DetallePedidoEntity existing = checkEntregaExists(id); // Validamos que exista

    detallePedidoRepository.deleteById(id); // Borramos por id

    return existing; // Retornamos la entidad que borramos
    }

    @Override
    public DetallePedidoEntity getById(UUID id) {
    return checkEntregaExists(id); // Si no existe, lanza excepción
    }

    @Override
    public List<DetallePedidoEntity> getAll() {
    List<DetallePedidoEntity> detalles = (List<DetallePedidoEntity>) detallePedidoRepository.findAll();
    if (detalles.isEmpty()) {
        throw new NoDataException();
    }
    return detalles;
}
//private void checkDescripcionNotNullOrEmpty(UUID id) {
   // if (id == null || id.equals(new UUID(0L, 0L))) {
     //   throw new NameNullException("descripcion");
//    }
//}

    private DetallePedidoEntity checkEntregaExists(UUID id) {
    return detallePedidoRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("El detalle con el ID: " + id + " no existe"));
    }

// private void checkDescripcionNotExists(BigDecimal precio) {
//     detallePedidoRepository.findByDescripcionIgnoreCase(precio)
//         .ifPresent(e -> {
//             throw new DuplicatedNameException("Ya existe un detalle con la descripción: " + precio);
//         });
//     }
}