package pe.gob.pronacej.service;


import org.apache.catalina.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceGeneric<T>{

    T register(T t);

    T edit(Integer id, T t);

    List<T> showAll();

    Optional<T> showById(Integer id);

    boolean deleteById(Integer id);
}
