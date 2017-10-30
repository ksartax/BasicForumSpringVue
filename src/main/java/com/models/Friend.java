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

    @Column(name = "my_user_id")
    private int myUserId;

    @Column(name = "other_user_id")
    private int otherUserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMyUserId() {
        return myUserId;
    }

    public void setMyUserId(int myUserId) {
        this.myUserId = myUserId;
    }

    public int getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(int otherUserId) {
        this.otherUserId = otherUserId;
    }
}
