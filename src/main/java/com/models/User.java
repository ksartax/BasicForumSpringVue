package com.models;

import com.component.Search.SearchInfection;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@EqualsAndHashCode
@ToString
@Setter
@Getter
@Data
@Table(name = "Users")
public class User implements Serializable, SearchInfection {

    @OneToMany(mappedBy = "user")
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
    @Size(max = 100, min = 5, message = "Pole musi zawierać min 5 znaków")
    private String username;

    @Column(name = "password", nullable = false)
    @Size(max = 100, min = 5, message = "Pole musi zawierać min 5 znaków")
    @NotBlank
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    @NotBlank
    private String email;

    @Column(name = "role")
    private String role;

    @Transient
    private String confirmPassword;

    @OneToOne
    @JoinColumn(name = "statistics_id", nullable = true)
    private Statistic statistics;

    @Column(name = "path_img", nullable = true)
    private String pathImg;

    public void setPathImg(String pathImg) {
        if (pathImg.equals("a.jpg")) {
            this.pathImg = pathImg;
        } else {
            this.pathImg = getId() + "/" + pathImg;
        }
    }

    public String getUrl() {
        return "/members/member/" + this.getId();
    }

    public String getDescrypton() {
        return this.getUsername() + " : " + this.getEmail();
    }
}
