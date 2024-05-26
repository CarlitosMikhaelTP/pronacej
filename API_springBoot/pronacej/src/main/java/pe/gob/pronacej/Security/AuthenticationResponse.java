package pe.gob.pronacej.Security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private Integer id;
    private String token;
    private Integer typeUserId;// Agregando el campo tipo de Usuario
    private Integer Id;
    private String name;
    private String lastName;

}
