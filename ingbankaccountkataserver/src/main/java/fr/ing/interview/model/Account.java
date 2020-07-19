package fr.ing.interview.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(generator = "account_id_generator")
    @SequenceGenerator(
            name = "account_id_generator",
            sequenceName = "seq_account_id",
            initialValue = 1000,
            allocationSize = 1
    )
    @Column(name="account_id", nullable = false)
    private Long accountId;

    @Column(name="customer_id", nullable = false)
    private Long customerId;

    @Column(name="account_number")
    private String accountNumber;

    @Column(name="type_Code")
    private String typeCode;

    @Column(name="type_label")
    private String typeLabel;
}
