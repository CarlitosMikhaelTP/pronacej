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
public class SabanaDTO {

    @NotNull(message = "El id de tableTables no puede ser nulo")
    @NotBlank(message = "El id de tableTables no puede estar vacio")
    private Integer indicatorId;

    @NotNull(message = "El id de processHeader no puede ser nulo")
    @NotBlank(message = "El id de processHeader no puede estar vacio")
    private Integer processHeaderId;

    @NotNull(message = "El value no puede ser nulo")
    @NotBlank(message = "El value no puede estar vacío")
    @Size(max = 100, message = "El value debe tener entre 1 y 100 caracteres")
    private String value;

    @NotNull(message = "El state no puede ser nulo")
    @NotBlank(message = "El state no puede estar vacía")
    private Integer state;

}
