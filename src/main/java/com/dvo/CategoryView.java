package com.dvo;

import lombok.*;

@EqualsAndHashCode
@ToString
@Setter
@Getter
@Data
public class CategoryView {
    private int id;
    private String title;
    private String description;
    private UserView userView;
    private int postsCount;
}
