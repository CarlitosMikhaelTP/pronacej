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
public class IndicatorDTO {

    @NotNull(message = "El id de person no puede ser nulo")
    @NotBlank(message = "El id de person no puede estar vacio")
    private Integer sectionRecordId;


    @NotNull(message = "El nameIndicator no puede ser nula")
    @NotBlank(message = "El nameIndicator no puede estar vacía")
    @Size(max = 30, message = "El campo nameIndicator debe tener entre 1 y 30 caracteres")
    private String name;

    @NotNull(message = "El state no puede ser nulo")
    @NotBlank(message = "El state no puede estar vacía")
    private Short state;
}
