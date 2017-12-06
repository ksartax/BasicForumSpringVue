package com.models;

import com.component.Search.SearchInfection;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@EqualsAndHashCode
@ToString
@Setter
@Getter
@Data
@Table(name = "Posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Post implements Serializable, SearchInfection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    @OrderBy("id ASC")
    private Set<Comment> comments;

    @Column(name = "title")
    @NotBlank
    private String title;

    @Column(name = "description")
    @NotBlank
    private String description;

    @Column(name = "comment_count")
    private int commentCount;

    @Column(name = "created_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;

    public void incrementCommentCount(int count) {
        this.commentCount += count;
    }

    public void decrementCommentCount(int count) {
        this.commentCount -= count;
    }

    public String getUrl() {
        return "/forum/post/" + this.getId();
    }

    public String getDescrypton() {
        return this.getTitle() + " : " + this.getDescription();
    }
}
