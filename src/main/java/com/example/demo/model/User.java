package com.example.demo.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "users")
@NoArgsConstructor
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String password;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Bucket bucket = new Bucket();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Order> order = new ArrayList<>();
}
