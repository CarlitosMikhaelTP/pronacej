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
@Builder
@Entity
@Table(name = "indicators")
public class Indicator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nameindicator", length = 30, nullable = false)
    private String nameIndicator;

    @Column(name = "state", columnDefinition = "TINYINT DEFAULT 1", nullable = false)
    private Integer state;

    // Definiendo la cardinalidad
    @OneToMany(mappedBy = "idIndicator", cascade = CascadeType.ALL)
    private List<TableTables> tableTablesList;
}
