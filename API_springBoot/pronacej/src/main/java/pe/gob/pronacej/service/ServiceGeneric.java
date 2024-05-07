package pe.gob.pronacej.service;


import org.apache.catalina.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceGeneric<T>{

    T registrar(T t);

    T editar(Integer id, T t);

    List<T> mostrarTodos();

    Optional<T> mostrarPorId(Integer id);

    boolean eliminarPorId(Integer id);
}
