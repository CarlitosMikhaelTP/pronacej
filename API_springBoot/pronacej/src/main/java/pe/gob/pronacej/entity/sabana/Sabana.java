package pe.gob.pronacej.entity.sabana;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.gob.pronacej.entity.graphic.Indicators;
import pe.gob.pronacej.entity.graphic.SectionRecord;
import pe.gob.pronacej.entity.graphic.TableTables;
import pe.gob.pronacej.entity.user.Admin;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "sabana")
public class Sabana {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_admin", nullable = false)
    private Admin adminId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_table_tables", nullable = false)
    private TableTables tableTablesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_header_id", nullable = false)
    private ProcessHeader processHeaderId;

    @Column(name = "valor", length = 50, nullable = false)
    private String value;

    @Column(name = "state", columnDefinition = "TINYINT DEFAULT 1", nullable = false)
    private Integer state;

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


}