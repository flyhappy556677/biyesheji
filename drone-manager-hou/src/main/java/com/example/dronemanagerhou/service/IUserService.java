package com.example.dronemanagerhou.service;

import com.example.dronemanagerhou.controller.dto.UserDTO;
import com.example.dronemanagerhou.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author drone
 * @since 2024-03-14
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);
}
