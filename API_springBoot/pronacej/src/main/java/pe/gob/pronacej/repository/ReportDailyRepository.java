package pe.gob.pronacej.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public class ReportDailyRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Llamando a función de reportes diarios cjdr
    public List<Map<String, Object>> executeFunctionReportDailyCjdr(LocalDate fecha_seleccionada){
        String query = "SELECT * FROM funcion_reporte_diario_cjdr(?)";
        return jdbcTemplate.queryForList(query, fecha_seleccionada);
    }

    // Llamando a función de reportes diarios soa
    public List<Map<String, Object>> executeFunctionReportDailySoa(LocalDate fecha_seleccionada){
        String query = "SELECT * FROM funcion_reporte_diario_soa(?)";
        return jdbcTemplate.queryForList(query, fecha_seleccionada);
    }
}
