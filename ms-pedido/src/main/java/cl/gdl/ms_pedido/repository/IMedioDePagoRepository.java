package cl.gdl.ms_pedido.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.gdl.ms_pedido.dto.MedioDePagoDTO;
import cl.gdl.ms_pedido.entity.MedioDePagoEntity;

@Repository
public interface IMedioDePagoRepository extends JpaRepository<MedioDePagoEntity, UUID> {

    MedioDePagoDTO findByNameMedioDePago(String nameMedioDePago);

    Optional<MedioDePagoDTO> findByNameMedioDePagoIgnoreCase(String nameMedioDePago);
}
