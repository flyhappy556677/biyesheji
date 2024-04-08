package com.example.dronemanagerhou.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dronemanagerhou.entity.Buyorder;
import com.example.dronemanagerhou.entity.Maintenance;
import com.example.dronemanagerhou.mapper.BuyorderMapper;
import com.example.dronemanagerhou.mapper.MaintenanceMapper;
import com.example.dronemanagerhou.service.IMaintenanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author drone
 * @since 2024-04-07
 */
@Service
public class MaintenanceServiceImpl extends ServiceImpl<MaintenanceMapper, Maintenance> implements IMaintenanceService {

    @Resource
    private MaintenanceMapper maintenanceMapper;
    @Override
    public Page<Maintenance> findPageWith(Page<Maintenance> page) {
        Page<Maintenance> maintenancePage = maintenanceMapper.findPageWith(page);

        List<Maintenance> maintenanceList = maintenancePage.getRecords();
        for (Maintenance maintenance : maintenanceList) {
            // 根据状态添加状态标签
            updateMaintenanceStatusLabel(maintenance);
        }

        return maintenancePage;
    }

    @Override
    public Maintenance getNameById(Integer id) {
        return maintenanceMapper.getNameById(id);
    }

    @Override
    public boolean updateBuyorder(Maintenance maintenance) {
        // 调用 Mapper 方法执行更新操作
        int rows = maintenanceMapper.updateBuyorder(maintenance);
        // 返回更新操作是否成功
        return rows > 0;
    }

    private void updateMaintenanceStatusLabel(Maintenance maintenance) {
        boolean status = maintenance.getStatus();
        if (status) {
            maintenance.setNote("需要进行保修");
        } else {
            maintenance.setNote("已经保修完成");
        }
    }
}
