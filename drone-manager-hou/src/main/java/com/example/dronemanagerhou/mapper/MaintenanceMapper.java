package com.example.dronemanagerhou.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dronemanagerhou.entity.Buyorder;
import com.example.dronemanagerhou.entity.Maintenance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author drone
 * @since 2024-04-07
 */
public interface MaintenanceMapper extends BaseMapper<Maintenance> {
    Page<Maintenance> findPageWith(@Param("page") Page<Maintenance> page);

    @Select("SELECT user_id,user_name,address,order_id,createtime,status,drone_name FROM sys_maintenance WHERE id = #{id}")
    Maintenance getNameById(Integer id);

    int updateBuyorder(Maintenance maintenance);
}
