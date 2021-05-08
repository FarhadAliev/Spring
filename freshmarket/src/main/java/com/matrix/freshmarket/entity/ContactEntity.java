package com.matrix.freshmarket.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "contact")
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id ;
    @NotNull(message = "First Name may not be null")
    @Size(min = 2, max = 32, message = "First Name must be between 2 and 32 characters long")
    @NotEmpty(message = "Please enter First Name")
    @Column(name = "first_name")
    String firstName;
    @Size(min = 2, max = 32, message = "Last Name must be between 2 and 32 characters long")
    @NotNull(message = "Last Name may not be null")
    @NotEmpty(message = "Please enter Last Name")
    @Column(name = "last_name")
    String lastName;
    @NotNull(message = "Email may not be null")
    @NotEmpty(message = "Please enter e-mail address")
    @Email(message = "Please enter a valid e-mail address")
    @Column(name = "email")
    String email;


    public ContactEntity(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
