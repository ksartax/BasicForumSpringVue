package com.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Friends")
public class Friend implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int id;

    @OneToOne
    @JoinColumn(name="my_user_id", nullable = true)
    private User myUser;

    @OneToOne
    @JoinColumn(name="other_user_id", nullable = true)
    private User otherUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getMyUser() {
        return myUser;
    }

    public void setMyUser(User myUser) {
        this.myUser = myUser;
    }

    public User getOtherUser() {
        return otherUser;
    }

    public void setOtherUser(User otherUser) {
        this.otherUser = otherUser;
    }
}
