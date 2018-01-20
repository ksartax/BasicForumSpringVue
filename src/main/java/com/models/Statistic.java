package com.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Statistics")
public class Statistic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int id;

    @Column(name = "posts_count", nullable = false, columnDefinition = "0")
    private int postsCount;

    @Column(name = "comments_count", nullable = false, columnDefinition = "0")
    private int commentsCount;

    @Column(name = "friends_count", nullable = false, columnDefinition = "0")
    private int friendsCount;

    @Column(name = "groups_count")
    private int groupsCount;

    public void incrementPostCount(int count) {
        this.postsCount += count;
    }

    public void decrementPostCount(int count) {
        this.postsCount -= count;
    }

    public void incrementCommentCount(int count) {
        this.commentsCount += count;
    }

    public void decrementCommentCount(int count) {
        this.commentsCount = this.commentsCount - count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(int postsCount) {
        this.postsCount = postsCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }

    public int getGroupsCount() {
        return groupsCount;
    }

    public void setGroupsCount(int groupsCount) {
        this.groupsCount = groupsCount;
    }
}
