package com.matrix.freshmarket.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Transient;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


@Entity
@Data
@Table(name="user")
public class User<roles> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotEmpty
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "reg_time")
    private LocalDate regTime;
    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER,cascade={CascadeType.ALL})
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;




    public User() {
    }

    public User( String email, LocalDate regTime, String password, List<Role> roles) {
        this.email = email;
        this.regTime = regTime;
        this.password = password;
        this.roles = roles;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
