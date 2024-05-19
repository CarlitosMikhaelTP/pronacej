package pe.gob.pronacej.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pe.gob.pronacej.service.procedureCJDR.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pronacej/v1/cj")
public class CjdrWeb {

    @Autowired
    private PopulationCjdrService populationCjdrService;
    @Autowired
    private TdCjdrService tratamientoDiferenciadoService;
    @Autowired
    private InsertionEduCjdrService insercioEducativaService;
    @Autowired
    private InsertionLaboralCjdrService insertionLaboralCjdrServicer;
    @Autowired
    private InfraccionesCometidasServiceCjdr infraccionesCometidasService;



    @GetMapping("/showTD")
    public List<Map<String, Object>> obtenerDatosTD(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam(value = "fechaFin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin) {
        if (fechaFin == null) {
            return tratamientoDiferenciadoService.obtenerTratamientoDiferenciado(fechaInicio, null);
        } else {
            return tratamientoDiferenciadoService.obtenerTratamientoDiferenciado(fechaInicio, fechaFin);
        }
    }

    @GetMapping("/showIE")
    public List<Map<String, Object>> obtenerDatosIE(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam(value = "fechaFin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin) {
        if (fechaFin == null) {
            return insercioEducativaService.obtenerDatosInsercionEducativa(fechaInicio, null);
        } else {
            return insercioEducativaService.obtenerDatosInsercionEducativa(fechaInicio, fechaFin);
        }
    }

    @GetMapping("/showIL")
    public List<Map<String, Object>> obtenerDatosIL(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam(value = "fechaFin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin) {
        if (fechaFin == null) {
            return insertionLaboralCjdrServicer.obtenerDatosInsercionLaboral(fechaInicio, null);
        } else {
            return insertionLaboralCjdrServicer.obtenerDatosInsercionLaboral(fechaInicio, fechaFin);
        }
    }

    @GetMapping("/showIC")
    public List<Map<String, Object>> obtenerDatosIC(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam(value = "fechaFin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin) {
        if (fechaFin == null) {
            return infraccionesCometidasService.obtenerDatosInfracciones(fechaInicio, null);
        } else {
            return infraccionesCometidasService.obtenerDatosInfracciones(fechaInicio, fechaFin);
        }
    }

    @GetMapping("/showPopulation")
    public List<Map<String, Object>> obtenerDatosPoblacion(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam(value = "fechaFin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin) {
        if (fechaFin == null) {
            return populationCjdrService.obtenerDatosPoblacion(fechaInicio, null);
        } else {
            return populationCjdrService.obtenerDatosPoblacion(fechaInicio, fechaFin);
        }
    }
}
