package com.salma.Ecommerce.Entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name="verification_token")
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private long id;

    @Lob
    @Column(name = "token",nullable = false,unique = true)
    private String token;

    @Column(name = "created_timestamp",nullable = false)
    private Timestamp createdTimeStamp;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;


}
