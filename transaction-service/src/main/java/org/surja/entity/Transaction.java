package org.surja.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//system id

    @Column(nullable = false, unique = true)
    private String txnId;// client side id

    @Column(nullable = false)
    private Long fromUserId;

    @Column(nullable = false)
    private Long toUserId;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private String reason;

    private String comment;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
