package com.example.dronemanagerhou.service;

import com.example.dronemanagerhou.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author drone
 * @since 2024-03-23
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
