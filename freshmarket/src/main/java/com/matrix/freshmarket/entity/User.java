package com.matrix.freshmarket.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Transient;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@Table(name="user")
public class User<roles> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @NotEmpty
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "reg_time")
    private LocalDate regTime;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private boolean isEnabled;

    @ManyToMany(fetch = FetchType.EAGER,cascade={CascadeType.ALL})
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;




    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public User() {
    }

    public User( String email, LocalDate regTime, String password, List<Role> roles,Boolean isEnabled) {
        this.email = email;
        this.regTime = regTime;
        this.password = password;
        this.roles = roles;
        this.isEnabled=isEnabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
