package pe.gob.pronacej.Security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDTO {

    @NotNull(message = "El id de typUser no puede ser nulo")
    @NotBlank(message = "El id de typUser no puede estar vacio")
    private Integer typeUserId;

    @NotNull(message = "La description no puede ser nula")
    @NotBlank(message = "La description no puede estar vacía")
    @Size(max = 50, message = "La description debe tener entre 1 y 50 caracteres")
    private String name;

    @NotNull(message = "La description no puede ser nula")
    @NotBlank(message = "La description no puede estar vacía")
    @Size(max = 50, message = "La description debe tener entre 1 y 50 caracteres")
    private String lastName;

    @NotNull(message = "El email no puede ser nula")
    @NotBlank(message = "El email no puede estar vacía")
    @Size(max = 50, message = "El email debe tener entre 1 y 50 caracteres")
    private String email;

    @NotNull(message = "El password no puede ser nula")
    @NotBlank(message = "El password no puede estar vacía")
    @Size(max = 255, message = "El password debe tener entre 1 y 255 caracteres")
    private String password;

    @NotNull(message = "El state no puede ser nulo")
    @NotBlank(message = "El state no puede estar vacía")
    private Short state;
}
