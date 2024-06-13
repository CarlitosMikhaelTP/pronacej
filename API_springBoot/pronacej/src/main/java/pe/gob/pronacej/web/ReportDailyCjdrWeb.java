package pe.gob.pronacej.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.pronacej.service.functionReportDaily.ReportDailyCjdrService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pronacej/v1/dailyCjdr")
public class ReportDailyCjdrWeb {

    @Autowired
    private ReportDailyCjdrService service;

    @GetMapping("/showReportCjdr")
    public List<Map<String, Object>> obtenerReporteCjdr(
            @RequestParam("fecha_seleccionada") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha_seleccionada){
        return service.getDailyReportCjdr(fecha_seleccionada);
    }

}
