package pe.gob.pronacej.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.pronacej.service.procedureSOA.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pronacej/v1/soa")
public class SoaWeb {

    @Autowired
    private PopulationSoaService populationSoaService;
    @Autowired
    private TdSoaService tratamientoDiferenciadoSoaService;
    @Autowired
    private InsertionEduSoaService insercioEducativaSoaService;
    @Autowired
    private InsertionLaboralSoaService insertionLaboralSoaServicer;
    @Autowired
    private InfraccionesCometidasSoaService infraccionesCometidasSoaService;



    @GetMapping("/showTD")
    public List<Map<String, Object>> obtenerDatosTD(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam(value = "fechaFin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin) {
        if (fechaFin == null) {
            return tratamientoDiferenciadoSoaService.obtenerTratamientoDiferenciado(fechaInicio, null);
        } else {
            return tratamientoDiferenciadoSoaService.obtenerTratamientoDiferenciado(fechaInicio, fechaFin);
        }
    }

    @GetMapping("/showIE")
    public List<Map<String, Object>> obtenerDatosIE(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam(value = "fechaFin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin) {
        if (fechaFin == null) {
            return insercioEducativaSoaService.obtenerDatosInsercionEducativa(fechaInicio, null);
        } else {
            return insercioEducativaSoaService.obtenerDatosInsercionEducativa(fechaInicio, fechaFin);
        }
    }

    @GetMapping("/showIL")
    public List<Map<String, Object>> obtenerDatosIL(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam(value = "fechaFin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin) {
        if (fechaFin == null) {
            return insertionLaboralSoaServicer.obtenerDatosInsercionLaboral(fechaInicio, null);
        } else {
            return insertionLaboralSoaServicer.obtenerDatosInsercionLaboral(fechaInicio, fechaFin);
        }
    }

    @GetMapping("/showIC")
    public List<Map<String, Object>> obtenerDatosIC(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam(value = "fechaFin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin) {
        if (fechaFin == null) {
            return infraccionesCometidasSoaService.obtenerDatosInfracciones(fechaInicio, null);
        } else {
            return infraccionesCometidasSoaService.obtenerDatosInfracciones(fechaInicio, fechaFin);
        }
    }

    @GetMapping("/showPopulation")
    public List<Map<String, Object>> obtenerDatosPoblacion(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam(value = "fechaFin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin) {
        if (fechaFin == null) {
            return populationSoaService.obtenerDatosPoblacion(fechaInicio, null);
        } else {
            return populationSoaService.obtenerDatosPoblacion(fechaInicio, fechaFin);
        }
    }
}
