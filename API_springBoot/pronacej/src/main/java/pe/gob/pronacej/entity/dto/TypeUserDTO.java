package pe.gob.pronacej.entity.dto;

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
public class TypeUserDTO {

    @NotNull(message = "El name no puede ser nula")
    @NotBlank(message = "El name no puede estar vacía")
    @Size(max = 150, message = "El campo name debe tener entre 1 y 150 caracteres")
    private String name;

    @NotNull(message = "La description no puede ser nula")
    @NotBlank(message = "La description no puede estar vacía")
    @Size(max = 255, message = "La description debe tener entre 1 y 255 caracteres")
    private String description;

    @NotNull(message = "El state no puede ser nulo")
    @NotBlank(message = "El state no puede estar vacía")
    private Integer state;
}
