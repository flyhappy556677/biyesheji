package com.example.dronemanagerhou.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dronemanagerhou.entity.Buyorder;
import com.example.dronemanagerhou.mapper.BuyorderMapper;
import com.example.dronemanagerhou.service.IBuyorderService;
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
 * @since 2024-04-03
 */
@Service
public class BuyorderServiceImpl extends ServiceImpl<BuyorderMapper, Buyorder> implements IBuyorderService {

    @Resource
    private  BuyorderMapper buyorderMapper;

    @Override
    public Page<Buyorder> findPageWithDrone(Page<Buyorder> page, String droneName) {
        Page<Buyorder> buyorderPage = buyorderMapper.findPageWithDrone(page, droneName);
        List<Buyorder> buyorders = buyorderPage.getRecords();
        for (Buyorder buyorder : buyorders) {
            // 获取 score 与 userscore
            int score = buyorder.getScore();
            int useScore = buyorder.getUsescore();
            boolean status=buyorder.getStatus();
            // 计算百分比
            double percentage = ((double) useScore / score) * 100;
            if (percentage > 20) {
                buyorder.setNote("正常状态");
            } else {
                buyorder.setNote("需要保养");
            }
            if (status == false){
                buyorder.setNote("正在保养中");
            }
        }
        return buyorderPage;
    }

    @Override
    public Buyorder getNameById(Integer id) {
        return buyorderMapper.getNameById(id);
    }

    @Override
    public boolean updateBuyorder(Buyorder buyorder) {
        // 调用 Mapper 方法执行更新操作
        int rows = buyorderMapper.updateBuyorder(buyorder);
        // 返回更新操作是否成功
        return rows > 0;
    }

    @Override
    public boolean newupdateBuyorder(Integer id) {
        Buyorder buyorder=buyorderMapper.getNameById(id);
        int usescore=buyorder.getScore();
        Boolean status=true;
        int rows = buyorderMapper.newupdateBuyorder(usescore,status,id);
        // 返回更新操作是否成功
        return rows > 0;
    }

    @Override
    public Buyorder saveorder(Buyorder buyorder) {
        buyorderMapper.saveorder(buyorder);
        return buyorder;
    }

    @Override
    public boolean updateStatus(Integer id) {
        int rowsAffected = buyorderMapper.updateStatus(id);
        return rowsAffected > 0;
    }
}
