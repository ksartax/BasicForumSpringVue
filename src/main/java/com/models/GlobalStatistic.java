package com.models;

import javax.persistence.*;

@Entity
@Table(name = "global_statistics")
public class GlobalStatistic
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int id;

    @Column(name = "count", nullable = false, columnDefinition = "0")
    private int count;

    @Column(name = "title")
    private String title;

    @Column(name = "path_img", nullable = true)
    private String pathImg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    public void incrementCount(int count)
    {
        this.count = this.count + count;
    }
}
