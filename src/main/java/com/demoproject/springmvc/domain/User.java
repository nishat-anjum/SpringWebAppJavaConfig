package com.demoproject.springmvc.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by nishat on 10/20/15.
 */
@Entity
@Table(name = "users") //can be define unique constraint for email field
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    @NotEmpty(message = "error.required")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "error.required")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email")
    @Email(message = "error.invalid.email")
    @NotEmpty(message = "error.required")
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "error.required")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
