package pe.gob.pronacej.service.procedureSOA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.pronacej.repository.CjdrRepository;
import pe.gob.pronacej.repository.SoaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class PopulationSoaService {

    @Autowired
    private SoaRepository poblacionRepository;

    public List<Map<String, Object>> obtenerDatosPoblacion(LocalDate fechaInicio, LocalDate fechaFin) {
        return poblacionRepository.executeProcedurePopulation(fechaInicio, fechaFin);
    }

}
