package DataBaseModel;



import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "results_web_lab4")
public class ResultDB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "coordinate_x", precision=100, scale=90)
    private BigDecimal x;

    @Column(name = "coordinate_y", precision=100, scale=90)
    private BigDecimal y;

    @Column(name = "coordinate_r", precision=100, scale=90)
    private BigDecimal r;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "success")
    private Boolean success;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserDB owner;
}