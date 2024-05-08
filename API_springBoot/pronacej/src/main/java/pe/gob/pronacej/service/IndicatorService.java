package pe.gob.pronacej.service;

import pe.gob.pronacej.dto.IndicatorDTO;
import pe.gob.pronacej.entity.Indicator;

import java.util.List;
import java.util.Optional;

// Explicar duda sobre generación de clases o interfaces genéricas e incompatibilidad con las anotaciones
public interface IndicatorService{

    IndicatorDTO register(IndicatorDTO indicatorDTO);

    IndicatorDTO edit(Integer id, IndicatorDTO indicatorDTO);

    List<IndicatorDTO> showAll();

    Optional<IndicatorDTO> showById(Integer id);

    boolean deleteById(Integer id);
}
