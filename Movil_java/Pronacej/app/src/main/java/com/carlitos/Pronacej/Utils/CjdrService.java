package com.carlitos.Pronacej.Utils;

import com.carlitos.Pronacej.Model.Sabana;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CjdrService {

    @GET("/pronacej/v1/cj/showTD")
    Call<List<Map<String, Object>>> obtenerTD(
            @Query("fechaInicio") String fechaInicio,
            @Query("fechaFin") String fechafin
    );

    @GET("/pronacej/v1/cj/showIE")
    Call<List<Map<String, Object>>> obtenerIE(
            @Query("fechaInicio") String fechaInicio,
            @Query("fechaFin") String fechafin
    );

    @GET("/pronacej/v1/cj/showIL")
    Call<List<Map<String, Object>>> obtenerIL(
            @Query("fechaInicio") String fechaInicio,
            @Query("fechaFin") String fechafin
    );

    @GET("/pronacej/v1/cj/showIC")
    Call<List<Map<String, Object>>> obtenerIC(
            @Query("fechaInicio") String fechaInicio,
            @Query("fechaFin") String fechafin
    );

    @GET("/pronacej/v1/cj/showPopulation")
    Call<List<Map<String,Object>>> obtenerePopulation(
            @Query("fechaInicio") String fechaInicio,
            @Query("fechaFin") String fechafin
    );


}
