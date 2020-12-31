package com.adriandevv.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Representa al usuario (Solo los campos relevantes)
 * @author Iordache Mihai Laurentiu
 * @version 1.0
 */
public class UserDTO {
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Enter a valid email address")
    private String email;
    @NotBlank(message = "Enter your username")
    private String username;
    @NotBlank(message = "Enter your password")
    @Length(min = 8, message = "Password must be at least 8 characters")
    private String password;
    @NotBlank(message = "Re-enter your password")
    private String rpassword;

    public UserDTO() {
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

    public String getRpassword() {
        return rpassword;
    }

    public void setRpassword(String rpassword) {
        this.rpassword = rpassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rpassword='" + rpassword + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
