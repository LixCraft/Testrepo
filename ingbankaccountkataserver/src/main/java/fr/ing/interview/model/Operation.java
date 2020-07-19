package fr.ing.interview.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="operation")
public class Operation {

    @Id
    @GeneratedValue(generator = "operation_id_generator")
    @SequenceGenerator(
            name = "operation_id_generator",
            sequenceName = "seq_operation_id",
            initialValue = 1000,
            allocationSize = 1

    )
    private Long operationId;

    @Column(name="account_id", nullable = false)
    private Long accountId;

    @Column(name="category", nullable = false)
    private String category;

    @Column(name="description")
    private String description;

    @Column(name="amount", nullable = false)
    private Double amount;

    @Column(name="date", nullable = false)
    private LocalDateTime date;
}
