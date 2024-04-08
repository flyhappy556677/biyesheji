package com.example.dronemanagerhou.controller.dto;

import com.example.dronemanagerhou.entity.Menu;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String avatarUrl;
    private String token;
    private List<Menu> menus;
    private String role;
    private String phone;
}
