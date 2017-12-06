package com.models;

import lombok.*;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
@ToString
@Setter
@Getter
@Data
@Table(name = "global_statistics")
public class GlobalStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int id;

    @Column(name = "count", nullable = false, columnDefinition = "0")
    private int count;

    @Column(name = "title")
    private String title;

    public void incrementCount(int count) {
        this.count = this.count + count;
    }

    public void decrementCount(int count) {
        this.count = this.count - count;
    }
}
