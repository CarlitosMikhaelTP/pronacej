package pe.gob.pronacej.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessHeaderDTO {

    @NotNull(message = "El id de typeProcessHeader no puede ser nula")
    @NotBlank(message = "El id del typeProcessHeader no puede estar vacía")
    private Integer typeProcessHeaderId;

    @Future(message = "La fecha de start_time debe estar en el futuro")
    @NotNull(message = "La fecha de start_time no puede ser nula")
    private Timestamp start_time;

    @Future(message = "La fecha de end_time debe estar en el futuro")
    @NotNull(message = "La fecha de end_time no puede ser nula")
    private Timestamp end_time;

    @NotNull(message = "El state no puede ser nulo")
    @NotBlank(message = "El state no puede estar vacía")
    private Integer state;
}
