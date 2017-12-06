package com.dvo;

import lombok.*;

@EqualsAndHashCode
@ToString
@Setter
@Getter
@Data
public class CommentView {
    private int id;
    private PostView postView;
    private UserView userView;
    private String description;
    private String createdAt;
}
