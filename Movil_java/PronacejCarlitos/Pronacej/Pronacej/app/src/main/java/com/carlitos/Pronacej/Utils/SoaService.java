package com.carlitos.Pronacej.Utils;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SoaService {

    @GET("/pronacej/v1/soa/showTD")
    Call<List<Map<String, Object>>> obtenerTD(
            @Query("fechaInicio") String fechaInicio,
            @Query("fechaFin") String fechafin
    );

    @GET("/pronacej/v1/soa/showIE")
    Call<List<Map<String, Object>>> obtenerIE(
            @Query("fechaInicio") String fechaInicio,
            @Query("fechaFin") String fechafin
    );

    @GET("/pronacej/v1/soa/showIL")
    Call<List<Map<String, Object>>> obtenerIL(
            @Query("fechaInicio") String fechaInicio,
            @Query("fechaFin") String fechafin
    );

    @GET("/pronacej/v1/soa/showIC")
    Call<List<Map<String, Object>>> obtenerIC(
            @Query("fechaInicio") String fechaInicio,
            @Query("fechaFin") String fechafin
    );

    @GET("/pronacej/v1/soa/showPopulation")
    Call<List<Map<String, Object>>> obtenerePopulation(
            @Query("fechaInicio") String fechaInicio,
            @Query("fechaFin") String fechafin
    );

    // Reportes Diario CJDR
    @GET("pronacej/v1/dailySoa/showReportSoa")
    Call<List<Map<String, Object>>> obtenerReporteDiarioSoa(
            @Query("fecha_seleccionada")String fecha_seleccionada);

}
