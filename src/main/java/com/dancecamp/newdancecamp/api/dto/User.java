package com.dancecamp.newdancecamp.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class User {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String role;
}
