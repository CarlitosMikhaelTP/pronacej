package pe.gob.pronacej.service.functionReportDaily;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.pronacej.repository.ReportDailyRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ReportDailyCjdrService {

    @Autowired
    private ReportDailyRepository repository;

    public List<Map<String, Object>> getDailyReportCjdr(LocalDate fecha_seleccionada){
        return repository.executeFunctionReportDailyCjdr(fecha_seleccionada);
    }
}
