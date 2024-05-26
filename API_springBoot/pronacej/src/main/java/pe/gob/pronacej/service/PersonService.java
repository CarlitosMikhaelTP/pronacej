package pe.gob.pronacej.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.gob.pronacej.Security.PersonDTO;
import pe.gob.pronacej.entity.dto.SabanaDTO;
import pe.gob.pronacej.entity.sabana.Sabana;
import pe.gob.pronacej.entity.user.Person;
import pe.gob.pronacej.entity.user.TypeUser;
import pe.gob.pronacej.repository.base.BaseRepository;
import pe.gob.pronacej.repository.impl.PersonRepository;
import pe.gob.pronacej.repository.impl.TypeUserRepository;

import java.util.List;
import java.util.Optional;


@Service
public class PersonService {

    private final TypeUserRepository crudTypeUser;

    private final PersonRepository crudPerson;


    @Autowired // Para inyectar nuestro repositorio
    //Servicio que será llamado en el controlador para listar los registros
    public PersonService (PersonRepository crudPerson, TypeUserRepository crudTypeUser){
        this.crudPerson = crudPerson;
        this.crudTypeUser = crudTypeUser;
    }

    public List<Person> listarUsers(){
        return crudPerson.findAll();
    }
    //Servicio que será llamado por el controlador para guardar los nuevos registros
    public void guardarUsers(Person user){
        crudPerson.save(user);
    }

    //Servicio para obtener un registro por medio del número de su id
    public Person obtenerUsers(Integer id){
        return crudPerson.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    //Servicio para eliminar un registro
    public void eliminarUsers(Integer id){
        crudPerson.deleteById(id);
    }

    public void saveUserTypeForAuthenticatedUser(Integer id, TypeUser typeUser){
        Person user= obtenerUsers(id);
        user.setTypeUserId(typeUser);
        guardarUsers(user);
    }

    public void registrarUsuarioConTipo(PersonDTO nuevoUsuarioDTO){
        Optional<TypeUser> tipoUsuarioOptional = crudTypeUser.findById(nuevoUsuarioDTO.getTypeUserId());
        tipoUsuarioOptional.ifPresent(tipoUsuario -> {
            Person user = new Person();
            user.setName(nuevoUsuarioDTO.getName());
            user.setLastName(nuevoUsuarioDTO.getLastName());
            user.setEmail(nuevoUsuarioDTO.getEmail());
            user.setPassword(nuevoUsuarioDTO.getPassword());

            user.setTypeUserId(tipoUsuario);
            crudPerson.save(user);
        });
    }



}
