package pe.gob.pronacej.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public class SoaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> executeProcedureTF(LocalDate fechaInicio, LocalDate fechaFin){
        String query = "SELECT * FROM funcion_tratamiento_diferenciado_soa(?,?)";
        return jdbcTemplate.queryForList(query, fechaInicio, fechaFin);
    }

    public List<Map<String, Object>> executeProcedureIE(LocalDate fechaInicio, LocalDate fechaFin){
        String query = "SELECT * FROM funcion_insercion_educativa_soa(?,?)";
        return jdbcTemplate.queryForList(query, fechaInicio, fechaFin);
    }
    public List<Map<String, Object>> executeProcedureIL(LocalDate fechaInicio, LocalDate fechaFin){
        String query = "SELECT * FROM funcion_insercion_laboral_soa(?,?)";
        return jdbcTemplate.queryForList(query, fechaInicio, fechaFin);
    }

    public List<Map<String, Object>> executeProcedureIC(LocalDate fechaInicio, LocalDate fechaFin){
        String query = "SELECT * FROM funcion_infracciones_cometidas_soa(?,?)";
        return jdbcTemplate.queryForList(query, fechaInicio, fechaFin);
    }

    public List<Map<String, Object>> executeProcedurePopulation(LocalDate fechaInicio, LocalDate fechaFin){
        String query = "SELECT * FROM funcion_poblacion_soa(?,?)";
        return jdbcTemplate.queryForList(query, fechaInicio, fechaFin);
    }

}
