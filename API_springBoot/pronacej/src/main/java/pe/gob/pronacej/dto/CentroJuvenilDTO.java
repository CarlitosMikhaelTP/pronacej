package pe.gob.pronacej.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CentroJuvenilDTO {

    @NotNull(message = "El nombre del lugar no puede ser nulo")
    @NotBlank(message = "La nombre del lugar no puede estar vacío")
    @Size(max = 30, message = "El nombre del lugar debe tener entre 1 y 30 caracteres")
    private String lugar;

    @NotNull(message = "La descripción no puede ser nula")
    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 30, message = "La descripción debe tener entre 1 y 30 caracteres")
    private String descripcion;

    // Campos que se mostrará como respuesta al para que lo consuma el móvil

}
