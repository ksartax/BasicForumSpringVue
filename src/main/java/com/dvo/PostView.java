package com.dvo;

import lombok.*;

@EqualsAndHashCode
@ToString
@Setter
@Getter
@Data
public class PostView {
    private int id;
    private String title;
    private String description;
    private UserView userView;
    private CategoryView categoryView;
    private int commentCount;
}
