package com.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Statistics")
public class Statistic implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int id;

    @Column(name = "posts_count", nullable = false, columnDefinition = "0")
    private int posts_count;

    @Column(name = "comments_count", nullable = false, columnDefinition = "0")
    private int comments_count;

    @Column(name = "friends_count", nullable = false, columnDefinition = "0")
    private int friendsCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosts_count() {
        return posts_count;
    }

    public void setPosts_count(int posts_count) {
        this.posts_count = posts_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }
}
