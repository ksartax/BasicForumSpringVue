package com.models;

import lombok.*;

@EqualsAndHashCode
@ToString
@Setter
@Getter
@Data
public class Contact {
    private String firstName;
    private String title;
    private String email;
    private String description;
}
