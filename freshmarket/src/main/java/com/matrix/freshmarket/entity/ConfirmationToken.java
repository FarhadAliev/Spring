package com.matrix.freshmarket.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "confirmation_token")
public class ConfirmationToken {


    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="token_id")
    private long tokenId;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;


    @OneToOne(targetEntity = User.class , fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "user_id")
    private User<Role> user;


    public ConfirmationToken(User<Role> user) {
        this.user = user;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

    public ConfirmationToken(long tokenId, String confirmationToken, Date createdDate, User<Role> user) {
        this.tokenId = tokenId;
        this.confirmationToken = confirmationToken;
        this.createdDate = createdDate;
        this.user = user;
    }


    public ConfirmationToken() {
    }



    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User<Role> getUser() {
        return user;
    }

    public void setUser(User<Role> user) {
        this.user = user;
    }

    public long getId() {
        return tokenId;
    }

    public void setId(long tokenId) {
        this.tokenId = tokenId;
    }



}
