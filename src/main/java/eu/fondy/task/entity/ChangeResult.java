package eu.fondy.task.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "change_result")
@NoArgsConstructor
@Getter
@Setter
public class ChangeResult {

    @Id
    @Column(name = "external_id")
    private UUID externalID;

    @Column(name = "pence_submitted")
    private int penceSubmitted;

    @Column(name = "pounds")
//    @Convert(converter = BreakdownAttributeConverter.class)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<Integer, Integer> poundsBreakdown;

    @Column(name = "pence")
//    @Convert(converter = BreakdownAttributeConverter.class)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<Integer, Integer> penceBreakdown;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
