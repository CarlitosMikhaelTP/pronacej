package pe.gob.pronacej.Security;

import lombok.RequiredArgsConstructor;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.pronacej.entity.dto.IndicatorDTO;
import pe.gob.pronacej.entity.graphic.Indicators;
import pe.gob.pronacej.entity.user.Person;
import pe.gob.pronacej.entity.user.TypeUser;
import pe.gob.pronacej.exceptions.NotFoundException;
import pe.gob.pronacej.repository.base.BaseRepository;
import pe.gob.pronacej.repository.impl.TypeUserRepository;
import pe.gob.pronacej.service.PersonService;
import pe.gob.pronacej.service.TypeUserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    private final AuthenticationService service;
    private final TypeUserRepository crudTypeUser;
    private final PersonService personService;


    //ENDPOINT para acceder a los servicios de Registros
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody PersonDTO request
    ){
        Integer id = request.getTypeUserId();
        TypeUser typeUser = crudTypeUser.findById(id)
                .orElseThrow(() -> new NotFoundException("Tipo de usuario no encontrado"));
        //Llamando al servicio de registro y pasándole el tipo de usuario asociado
        AuthenticationResponse response = service.register(request, id);
        return ResponseEntity.ok(response);
    }

    // ENDPOINT para acceder a los servicios de autenticación
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse response = service.authenticate(request);

        return ResponseEntity.ok(service.authenticate(request));
    }

    // ENDPOINT PARA EDITAR A UN USUARIO POR SU ID
    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editUser(
            @PathVariable Integer id,
            @RequestBody PersonDTO request
    ){
        String newToken = service.editUserDetails(id, request);
        if (newToken != null){
            return ResponseEntity.ok("Contraseña actualizada. Nuevo Token generado: " + newToken);
        } else {
            return ResponseEntity.ok("Detalles de usuario actualizado, sin cambios en la contraseña.");
        }
    }

    // ENDPOINT PARA ELIMINAR A UN USUARIO POR SU ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id){
        service.deleteUserById(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }

}
