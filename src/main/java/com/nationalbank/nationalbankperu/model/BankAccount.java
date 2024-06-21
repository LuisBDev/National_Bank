package com.nationalbank.nationalbankperu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tblBankAccount")
public class BankAccount {

    @Embedded
    private Audit audit = new Audit();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private BigDecimal balance;


    @Column(nullable = false)
    private String status;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "fromAccount", orphanRemoval = true)
    @JsonIgnore
    private List<Transaction> transactionsFrom;

    @OneToMany(mappedBy = "toAccount", orphanRemoval = true)
    @JsonIgnore
    private List<Transaction> transactionsTo;


    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", status='" + status + '\'' +
//                ", user=" + user +
                ", created_at=" + audit.getCreatedAt() +
                ", updated_at=" + audit.getUpdatedAt() +
                '}';
    }

}
