package com.openclassrooms.yourcaryourway.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "User")
@Data
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, name = "date_of_birth")
    private String dateOfBirth;

    @Column(nullable = false)
    private String address;
}
