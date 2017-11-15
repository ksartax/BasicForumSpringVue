package com.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User implements Serializable {
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonBackReference
    public Set<Comment> comments;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int id;
    @Column(name = "active", nullable = false)
    private int active;

    @Column(name = "username", nullable = false, unique = true)
    @NotBlank
    private String username;

    @Column(name = "password", nullable = false, unique = true)
    @NotBlank
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    @NotBlank
    private String email;

    @Column(name = "role")
    private String role;

    @OneToOne
    @JoinColumn(name = "statistics_id", nullable = true)
    private Statistic statistics;

    @Column(name = "path_img", nullable = true)
    private String pathImg;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    public Statistic getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistic statistics) {
        this.statistics = statistics;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
