package com.example.dronemanagerhou.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dronemanagerhou.entity.Buyorder;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author drone
 * @since 2024-04-03
 */
public interface IBuyorderService extends IService<Buyorder> {

     Page<Buyorder> findPageWithDrone(Page<Buyorder> page, String droneName);

     Buyorder getNameById(Integer id) ;
     boolean updateBuyorder(Buyorder buyorder);

     boolean newupdateBuyorder(Integer id);

     Buyorder saveorder(Buyorder buyorder);

        boolean updateStatus(Integer id);
}
