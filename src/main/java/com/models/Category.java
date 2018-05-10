package com.models;

import com.component.Search.SearchInfection;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Categories", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Category implements Serializable, SearchInfection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "title")
    @NotBlank
    private String title;

    @Column(name = "description")
    @NotBlank
    private String description;

    @Column(name = "posts_count")
    private int postsCount;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("commentCount DESC, id ASC")
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Set<Post> posts;

    @Column(name = "created_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;

    public void incrementPost(int count) {
        this.postsCount += count;
    }

    public void decrementPost(int count) {
        this.postsCount -= count;
    }

    public int getPostsCommentsCount() {
        int count = 0;

        for (Post post : this.getPosts()) {
            count += post.getCommentCount();
        }

        return count;
    }

    public String getUrl() {
        return "/forum/category/" + this.getTitle();
    }

    public String getDescrypton() {
        return this.getTitle() + " : " + this.getDescription();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(int postsCount) {
        this.postsCount = postsCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
