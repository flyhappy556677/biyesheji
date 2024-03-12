package com.example.dronemanagerhou.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dronemanagerhou.entity.User;
import com.example.dronemanagerhou.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper,User> {
    public boolean saveUser(User user) {

/*        if(user.getId() == null){
           return save(user);//mybatis-plus提供的方法，表示插入的数据
        }else {
            return updateById(user);
        }*/

        return saveOrUpdate(user);

    }





/*    @Autowired
    private UserMapper userMapper;
    public  int save(User user){
        if(user.getId() == null){ //user没有id，则表示是新增
            return userMapper.insert(user);
        }else {
             return  userMapper.update(user);
        }
    }
    public List<User> findAll(){
        List<User> all=userMapper.findAll();
        return all;
    }*/
}
