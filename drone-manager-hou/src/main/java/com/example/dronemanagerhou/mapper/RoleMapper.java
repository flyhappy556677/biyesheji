package com.example.dronemanagerhou.mapper;

import com.example.dronemanagerhou.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author drone
 * @since 2024-03-23
 */
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select id from sys_role where flag=#{flag}")
    Integer selectByFlag(@Param("flag") String flag);
}
