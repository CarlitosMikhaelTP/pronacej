package pe.gob.pronacej.service;

import pe.gob.pronacej.entity.dto.IndicatorDTO;
import pe.gob.pronacej.entity.dto.SabanaDTO;
import pe.gob.pronacej.entity.graphic.Indicators;
import pe.gob.pronacej.entity.sabana.Sabana;

import java.util.List;
import java.util.Optional;

public interface SabanaService {

    SabanaDTO register(SabanaDTO sabanaDTO);

    SabanaDTO edit(Integer id, SabanaDTO sabanaDTO);

    List<SabanaDTO> showAll();

    Optional<SabanaDTO> showById(Integer id);

    boolean deleteById(Integer id);

    // Declarando método Model Mapper
    SabanaDTO convertEntityToDto(Sabana sabana);

    Sabana convertDtoToEntity(SabanaDTO sabanaDTO);
}
