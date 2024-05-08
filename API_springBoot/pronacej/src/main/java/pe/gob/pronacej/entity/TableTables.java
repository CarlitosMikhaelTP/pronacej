package pe.gob.pronacej.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tabletables")
public class TableTables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idindicator", nullable = false)
    private Indicator idIndicator;

    @Column(name = "nametable", length = 30, nullable = false)
    private String nameTable;

    @Column(name = "idfield", nullable = false)
    private Integer idField;

    @Column(name = "value", length = 30, nullable = false)
    private String value;

    @Column(name = "state", columnDefinition = "TINYINT DEFAULT 1", nullable = false)
    private Integer state;
}
