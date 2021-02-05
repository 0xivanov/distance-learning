package com.distance_learning.service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserServiceRegisterModel {
    private String username;
    private String email;
    private String password;
    private String role;
}
