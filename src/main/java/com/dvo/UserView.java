package com.dvo;

import lombok.*;

@EqualsAndHashCode
@ToString
@Setter
@Getter
@Data
public class UserView {
    private String username;
    private int id;
    private int active;
    private String email;
    private String pathImg;
}
