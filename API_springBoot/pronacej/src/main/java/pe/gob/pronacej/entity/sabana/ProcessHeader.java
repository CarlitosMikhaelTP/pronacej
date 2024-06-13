package pe.gob.pronacej.entity.sabana;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.gob.pronacej.entity.user.Person;
import pe.gob.pronacej.entity.user.TypeUser;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "process_header")
public class ProcessHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_process_header_id", nullable = false)
    private TypeProcessHeader typeProcessHeaderId;

    @Column(name= "start_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private Timestamp startTime;

    @Column(name= "end_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private Timestamp endTime;

    @Column(name = "amount", precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "message", length = 200)
    private String message;

    @Column(name = "status",length = 300)
    private String status;

    @Column(name = "state", columnDefinition = "TINY DEFAULT 1", nullable = false)
    private Short state;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    // Mapenado cardinalidad
    @OneToMany(mappedBy = "processHeaderId", cascade = CascadeType.ALL)
    private List<Sabana> sabanaList;
}
