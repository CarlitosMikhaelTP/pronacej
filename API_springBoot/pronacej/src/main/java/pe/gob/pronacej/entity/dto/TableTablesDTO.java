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
public class TableTablesDTO {


    @NotNull(message = "El id del idIndicators no puede ser nulo")
    @NotBlank(message = "El id del idIndicators no puede estar vacía")
    private Integer indicatorId;

    @NotNull(message = "El nameTable no puede ser nula")
    @NotBlank(message = "EL namTable no puede estar vacía")
    @Size(max = 100, message = "El nameTable debe tener entre 1 y 30 caracteres")
    private String nameTable;

    @NotNull(message = "El id Field no puede ser nulo")
    @NotBlank(message = "El id Field no puede estar vacía")
    private Integer idField;

    @NotNull(message = "El value no puede ser nula")
    @NotBlank(message = "El value no puede estar vacía")
    @Size(max = 100, message = "El campo value debe tener entre 1 y 30 caracteres")
    private String value;

    @NotNull(message = "El state no puede ser nulo")
    @NotBlank(message = "El state no puede estar vacía")
    private Short state;
}
