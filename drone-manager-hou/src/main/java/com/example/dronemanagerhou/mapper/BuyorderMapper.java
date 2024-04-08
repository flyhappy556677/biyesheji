package com.example.dronemanagerhou.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dronemanagerhou.entity.Buyorder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author drone
 * @since 2024-04-03
 */
public interface BuyorderMapper extends BaseMapper<Buyorder> {

    Page<Buyorder> findPageWithDrone(@Param("page") Page<Buyorder> page,@Param("droneName") String droneName);

    @Select("SELECT drone_name,score,usescore,user_name FROM sys_buyorder WHERE id = #{id}")
    Buyorder getNameById(Integer id);

    @Insert("INSERT INTO sys_buyorder (drone_name, drone_no, user_id,user_name,user_phone,buytime,score,usescore,status) " +
            "VALUES (#{droneName}, #{droneNo}, #{userId}, #{userName}, #{userPhone}, #{buytime}, #{score}, #{usescore}, #{status})")
    void saveorder(Buyorder buyorder);

    int updateBuyorder(Buyorder buyorder);

    @Update("UPDATE sys_buyorder SET status = false WHERE id = #{id}")
    int updateStatus(@Param("id") Integer id);

    @Update("UPDATE sys_buyorder SET usescore = #{usescore}, status = #{status} WHERE id = #{id}")
    int newupdateBuyorder(@Param("usescore") int usescore, @Param("status") Boolean status,@Param("id") Integer id);


}
