package pe.gob.pronacej.service;

import pe.gob.pronacej.entity.user.TypeUser;
import pe.gob.pronacej.repository.base.BaseRepository;
import pe.gob.pronacej.repository.impl.TypeUserRepository;

import java.util.List;

public class TypeUserService {

    private final TypeUserRepository crudTypeUser;

    public TypeUserService(TypeUserRepository crudTypeUser){
        this.crudTypeUser = crudTypeUser;
    }

    public List<TypeUser> obtenerTodosLosTiposUsuario(){
        return crudTypeUser.findAll();
    }

}
