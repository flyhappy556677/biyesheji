package com.example.dronemanagerhou.controller.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String nickname;
    private String avatarUrl;
    private String token;
}
