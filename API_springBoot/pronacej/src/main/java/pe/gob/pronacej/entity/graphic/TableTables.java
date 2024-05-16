package pe.gob.pronacej.entity.graphic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.gob.pronacej.entity.sabana.Sabana;
import pe.gob.pronacej.entity.user.Admin;
import pe.gob.pronacej.entity.user.Person;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "table_tables")
public class TableTables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin adminId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_indicator", nullable = false)
    private Indicators indicatorId;

    @Column(name = "name_table", length = 100, nullable = false)
    private String nameTable;

    @Column(name = "id_field", nullable = false)
    private Integer idField;

    @Column(name = "value", length = 100, nullable = false)
    private String value;

    @Column(name = "state", columnDefinition = "TINYINT DEFAULT 1", nullable = false)
    private Short state;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updated_at",  columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private SectionRecord createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", referencedColumnName = "id")
    private SectionRecord updatedBy;

    // Mapeando Cardinalidad
    @OneToMany(mappedBy = "tableTablesId", cascade = CascadeType.ALL)
    private List<Sabana> sabanaList;
}
