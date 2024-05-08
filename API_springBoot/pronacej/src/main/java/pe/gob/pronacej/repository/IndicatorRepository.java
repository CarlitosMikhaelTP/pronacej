package pe.gob.pronacej.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.gob.pronacej.entity.Indicator;

@Repository
public interface IndicatorRepository extends JpaRepository<Indicator, Integer> {
}
