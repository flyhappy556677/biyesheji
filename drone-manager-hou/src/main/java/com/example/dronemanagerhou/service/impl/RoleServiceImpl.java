package com.example.dronemanagerhou.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dronemanagerhou.entity.Menu;
import com.example.dronemanagerhou.entity.Role;
import com.example.dronemanagerhou.entity.RoleMenu;
import com.example.dronemanagerhou.mapper.RoleMapper;
import com.example.dronemanagerhou.mapper.RoleMenuMapper;
import com.example.dronemanagerhou.service.IMenuService;
import com.example.dronemanagerhou.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author drone
 * @since 2024-03-23
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private IMenuService menuService;
    @Transactional
    @Override
    public void setRoleMenu(Integer roleId, List<Integer> menuIds) {
/*        QueryWrapper<RoleMenu> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        roleMenuMapper.delete(queryWrapper);*/

        //先删除当前角色id所有的绑定关系
        roleMenuMapper.deleteByRoleId(roleId);

        //再吧前端传过来的菜单id数组绑定到当前这个角色id上
        List<Integer> menuIdsCopy= CollUtil.newArrayList(menuIds);
        for (Integer menuId :menuIds){
            Menu menu=menuService.getById(menuId);
            if(menu.getPid() != null && !menuIdsCopy.contains(menu.getPid())){//二级菜单，并且传过来的menuId的数组里面没有它的父级id
                //那么我们就得补上这个父级id
                RoleMenu roleMenu=new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menu.getPid());
                roleMenuMapper.insert(roleMenu);
                menuIdsCopy.add(menu.getPid());
            }
            RoleMenu roleMenu=new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuMapper.insert(roleMenu);
        }
    }

    @Override
    public List<Integer> getRoleMenu(Integer roleId) {
        return roleMenuMapper.selectByRoleId(roleId);
    }
}
