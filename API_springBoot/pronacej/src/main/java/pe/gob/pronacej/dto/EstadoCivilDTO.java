package pe.gob.pronacej.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EstadoCivilDTO {

    @NotNull(message = "El tipo de estado civil no puede ser nulo")
    @NotBlank(message = "El tipo de estado civil no puede estar vacío")
    @Size(max = 30, message = "El tipo de estado debe tener entre 1 y 30 caracteres")
    private String tipoEstado;

    @NotNull(message = "La descripción no puede ser nula")
    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 30, message = "La descripción debe tener entre 1 y 30 caracteres")
    private String descripcion;

    // Campos que se mostrará como respuesta al para que lo consuma el móvil

}
