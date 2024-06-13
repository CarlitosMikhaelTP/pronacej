package pe.gob.pronacej.service;

import org.springframework.stereotype.Service;
import pe.gob.pronacej.entity.dto.IndicatorDTO;
import pe.gob.pronacej.entity.dto.ProcessHeaderDTO;
import pe.gob.pronacej.entity.graphic.Indicators;
import pe.gob.pronacej.entity.sabana.ProcessHeader;

import java.util.List;
import java.util.Optional;

public interface ProcessHeaderService {

    ProcessHeaderDTO register(ProcessHeaderDTO processHeaderDTO);

    ProcessHeaderDTO edit(Integer id, ProcessHeaderDTO processHeaderDTO);

    List<ProcessHeaderDTO> showAll();

    Optional<ProcessHeaderDTO> showById(Integer id);

    boolean deleteById(Integer id);

    // Declarando método Model Mapper
    ProcessHeaderDTO convertEntityToDto(ProcessHeader processHeader);

    ProcessHeaderDTO convertDtoToEntity(ProcessHeaderDTO processHeaderDTO);

}
