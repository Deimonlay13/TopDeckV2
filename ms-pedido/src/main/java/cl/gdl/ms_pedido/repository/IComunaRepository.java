package cl.gdl.ms_pedido.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cl.gdl.ms_pedido.entity.ComunaEntity;

public interface IComunaRepository extends CrudRepository<ComunaEntity, Long> {
    List<ComunaEntity> findByRegionId(Long regionId);
}
