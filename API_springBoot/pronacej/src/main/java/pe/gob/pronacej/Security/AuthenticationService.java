package pe.gob.pronacej.Security;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.gob.pronacej.Security.Configuration.ProjectService;
import pe.gob.pronacej.entity.dto.IndicatorDTO;
import pe.gob.pronacej.entity.graphic.Indicators;
import pe.gob.pronacej.entity.user.Person;
import pe.gob.pronacej.entity.user.TypeUser;
import pe.gob.pronacej.exceptions.NotFoundException;
import pe.gob.pronacej.repository.base.BaseRepository;
import pe.gob.pronacej.repository.impl.PersonRepository;
import pe.gob.pronacej.repository.impl.TypeUserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PersonRepository crudPerson;
    private final PasswordEncoder passwordEncoder;
    private final ProjectService projectService;
    private final AuthenticationManager authenticationManager;
    private final TypeUserRepository crudTypeUser;

    @Autowired
    private final ModelMapper modelMapper;

        public AuthenticationResponse register(PersonDTO request, Integer id) {
        TypeUser typeUser = crudTypeUser.findById(id)
                .orElseThrow(() -> new NotFoundException("ID del tipo de usuario no encontrado"));
        var person = Person.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .typeUserId(typeUser)
                .build();
        System.out.println("Tipo de usuario antes de guardar: " + typeUser);

        crudPerson.save(person);
        var jwtToken = projectService.generateToken(person);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .id(person.getId())
                .build();
    }

    // Servicio para autenticar al usuario
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Person person = crudPerson.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        person = crudPerson.loadTypeUser(person.getId());
        var jwtToken = projectService.generateToken(person);

        TypeUser typeUser = person.getTypeUserId();
        Integer typeUserId = typeUser.getId();
        Integer personId = person.getId();
        String name = person.getName();
        String lastNames = person.getLastName();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        //Crear la respuesta con el token y el tipo de usuario
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .typeUserId(typeUserId)
                .Id(personId)
                .name(name)
                .lastName(lastNames)
                .build();
    }

    // Servicio para mostrar los usuarios creados
    public List<PersonDTO> getAllUsers(){
        return StreamSupport.stream(crudPerson
                .findAll()
                .spliterator(),false)
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    // Servicio para mostrar un usuario por su ID
    public Optional<PersonDTO> getUserById(Integer id){
        Optional<Person> personOptional = crudPerson.findById(id);
        return personOptional.map(person -> {
            PersonDTO personDTO =  new PersonDTO();
            personDTO.setTypeUserId(person.getTypeUserId().getId());
            personDTO.setName(person.getName());
            personDTO.setLastName(person.getLastName());
            personDTO.setEmail(person.getEmail());
            personDTO.setPassword(person.getPassword());
            return personDTO;
        });
    }

    // Servicio para editar Usuario
    public String editUserDetails(Integer id, PersonDTO request) {
        Person person = crudPerson.findById(id)
                .orElseThrow(() -> new NotFoundException("Id del Usuario no encontrado "));
        // Actualizar los campos según la solicitud de edición
        if (request.getName() != null) {
            person.setName(request.getName());
        }
        if (request.getLastName() != null) {
            person.setLastName(request.getLastName());
        }
        if (request.getEmail() != null) {
            person.setEmail(request.getEmail());
        }
        // Si la contraseña se actualiza, codifícala y genera un nuevo token
        if (request.getPassword() != null) {
            person.setPassword(passwordEncoder.encode(request.getPassword()));
            // Generando un nuevo token con la informacion actualizada del usuario
            var jwtToken = projectService.generateToken(person);
            // Guardando cambios en la base de datos
            crudPerson.save(person);
            // Devolver nuevo token
            return jwtToken;

        }
        // Guardando cambios en la base de datos
        crudPerson.save(person);

        //Si no se actualiza el password devolver null o algun indicador
        return null;
    }

    public List<PersonDTO> showAll() {
        return StreamSupport.stream(crudPerson
                        .findAll()
                        .spliterator(), false)
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    public Optional<PersonDTO> showById(Integer id) {
        Optional<Person> personOptional = crudPerson.findById(id);

        return personOptional.map(person -> {
            PersonDTO personDTO = new PersonDTO();
            personDTO.setTypeUserId(person.getTypeUserId().getId());
            personDTO.setName(person.getName());
            personDTO.setLastName(person.getLastName());
            personDTO.setEmail(person.getEmail());
            personDTO.setPassword(person.getPassword());

            return personDTO;
        });
    }

    // Creación de servicio para eliminar usuarios por ID
    public void deleteUserById(Integer id){
        // Verificar si el usuario existe
        Person user = crudPerson.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        // Eliminando el usuario de la base de datos
        crudPerson.delete(user);
    }


    public PersonDTO convertEntityToDto(Person person) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        PersonDTO personDTO = new PersonDTO();
        personDTO = modelMapper.map(person, PersonDTO.class);
        return personDTO;
    }


    public Person convertDtoToEntity(PersonDTO personDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Person person = new Person();
        person = modelMapper.map(personDTO, Person.class);
        return person;
    }

}
