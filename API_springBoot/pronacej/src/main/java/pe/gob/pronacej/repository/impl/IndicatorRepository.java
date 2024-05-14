package pe.gob.pronacej.repository.impl;

import org.springframework.stereotype.Repository;
import pe.gob.pronacej.entity.graphic.Indicators;
import pe.gob.pronacej.repository.base.BaseRepository;

@Repository
public interface IndicatorRepository extends BaseRepository<Indicators, Integer> {

    // Hay métodos que Spring Data JPA puede generar automáticamente
    // Tengo la opción de no crear la implementacion de metodos como
    // 'BuscarPorNombre' y ese tipo de cosas, pero como se harán consultas
    // complejas optaré por el uso de interface e implementaciones 100%
    // propias.
    //List<Indicators> findIndicatorByNameIndicator(String name);

}
