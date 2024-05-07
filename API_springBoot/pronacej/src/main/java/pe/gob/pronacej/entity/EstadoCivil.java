package pe.gob.pronacej.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "EstadoCivil")
public class EstadoCivil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tipoEstado", length = 30, nullable = false)
    private String tipoEstado;

    @Column(name = "descripcion", length = 30, nullable = false)
    private String descripcion;

    @Column(name = "estado", columnDefinition = "TINYINT DEFAULT 1", nullable = false)
    private short estado;

    // Cardinalidad
    @OneToMany(mappedBy = "estadoCivil_id", cascade = CascadeType.ALL)
    private List<Matriz> matrizList;

}
