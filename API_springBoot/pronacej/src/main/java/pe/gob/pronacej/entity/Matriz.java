package pe.gob.pronacej.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "matriz")
public class Matriz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cj_id", nullable = false)
    private CentroJuvenil centroJuvenil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estadoCivil_id", nullable = false)
    private EstadoCivil estadoCivil;

    @Column(name = "descripcion", length = 30, nullable = false)
    private String descripcion;

    @Column(name = "estado", columnDefinition = "TINYINT DEFAULT 1", nullable = false)
    private short estado;

}
