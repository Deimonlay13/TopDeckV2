package cl.gdl.ms_pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.gdl.ms_pedido.entity.RegionEntity;

public interface IRegionRepository extends JpaRepository<RegionEntity, Long>{
}
