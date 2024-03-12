package com.example.dronemanagerhou.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dronemanagerhou.entity.User;
import com.example.dronemanagerhou.mapper.UserMapper;
import com.example.dronemanagerhou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //新增和修改
    @PostMapping
    public boolean save(@RequestBody User user){
       return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        return userService.removeById(id);
    }

    //查询所有数据
    @GetMapping("/")
    public List<User> findAll(){
        return userService.list();
    }
    //mybatis-plus方法
    @GetMapping("/page")
    public  IPage<User> findPage(@RequestParam Integer pageNum ,
                                 @RequestParam Integer pageSize,
                                 @RequestParam(defaultValue = "") String username,
                                 @RequestParam (defaultValue = "")String nickname,
                                 @RequestParam (defaultValue = "")String address){


        IPage<User> page=new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(!"".equals(username)){
            queryWrapper.like("username",username);
        }
        if(!"".equals(nickname)){
            queryWrapper.like("nickname",nickname);
        }
        if(!"".equals(address)){
            queryWrapper.like("address",address);
        }
        return userService.page(page,queryWrapper);
    }

/*    //分页查询
    //@RequestParam接受 ？pageNum=1&pageSize=10
    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum ,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String username){
        pageNum=( pageNum - 1 ) * pageSize;

        List<User> data=userMapper.selectPage(pageNum,pageSize,username);
        Integer total=userMapper.selectTotal();
        Map<String, Object> res =new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }*/
}