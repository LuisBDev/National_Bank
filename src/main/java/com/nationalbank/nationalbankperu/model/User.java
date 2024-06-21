package com.nationalbank.nationalbankperu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tblUser")
public class User {

    @Embedded
    private Audit audit = new Audit();

    //@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(orphanRemoval = true, mappedBy = "user")
    private List<BankAccount> bankAccounts;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numIdentification;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String birthDate;

    @Column(nullable = false, length = 6)
    private String password;

    //to string
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", numIdentification='" + numIdentification + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", password='" + password + '\'' +
                ", created_at" + audit.getCreatedAt() +
                ", updated_at" + audit.getUpdatedAt() +
                ", bankAccounts=" + bankAccounts +
                '}';
    }
}
