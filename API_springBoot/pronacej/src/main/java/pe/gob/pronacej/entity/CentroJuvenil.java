package pe.gob.pronacej.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "CentroJuvenil")
public class CentroJuvenil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "lugar", length = 30, nullable = false)
    private String lugar;

    @Column(name = "descripcion", length = 30, nullable = false)
    private String descripcion;

    @Column(name = "estado", columnDefinition = "TINYINT DEFAULT 1", nullable = false)
    private short estado;

    // Cardinalidad
    @OneToMany(mappedBy = "cj_id", cascade = CascadeType.ALL)
    private List<Matriz> matrizList;


}
