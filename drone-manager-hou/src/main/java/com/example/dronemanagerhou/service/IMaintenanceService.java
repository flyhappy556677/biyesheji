package com.example.dronemanagerhou.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dronemanagerhou.entity.Buyorder;
import com.example.dronemanagerhou.entity.Maintenance;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author drone
 * @since 2024-04-07
 */
public interface IMaintenanceService extends IService<Maintenance> {
    Page<Maintenance> findPageWith(Page<Maintenance> page);

    Maintenance getNameById(Integer id) ;

    boolean updateBuyorder(Maintenance maintenance);
}
