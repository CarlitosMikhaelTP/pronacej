package pe.gob.pronacej.service.procedureCJDR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.pronacej.repository.CjdrRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class TdCjdrService {

    @Autowired
    private CjdrRepository repository;

    public List<Map<String, Object>> obtenerTratamientoDiferenciado(LocalDate fechaInicio, LocalDate fechaFin) {
        return repository.executeProcedureCJ(fechaInicio, fechaFin);
    }

}
