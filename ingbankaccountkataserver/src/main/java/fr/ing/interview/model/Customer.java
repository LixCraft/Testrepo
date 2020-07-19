package fr.ing.interview.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(generator = "customer_id_generator")
    @SequenceGenerator(
            name = "customer_id_generator",
            sequenceName = "seq_customer_id",
            initialValue = 1000,
            allocationSize = 1

    )
    @Column(name="customer_id", nullable = false)
    private Long customerId;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="birth_date", nullable = false)
    private LocalDateTime birthDate;
}
