package com.example.dronemanagerhou.utils;

import cn.hutool.core.date.DateUtil;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.dronemanagerhou.entity.User;
import com.example.dronemanagerhou.service.IUserService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
@Component
public class TokenUtils {

    private static IUserService staticUserService;
    @Resource
    private IUserService userService;
    @PostConstruct
    public void setUserService(){
        staticUserService=userService;
    }
    //生成token
    public static String genToken(String adminId, String sign) {
        return JWT.create().withAudience(adminId) // 将 user id 保存到 token 里面,作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // 2小时后token过期
                .sign(Algorithm.HMAC256(sign)); // 以 password 作为 token 的密钥
    }

    //获取当前登入用户信息
    public static User getCurrentUser(){
        try{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if(StrUtil.isNotBlank(token)){
                String userid = JWT.decode(token).getAudience().get(0);
                return staticUserService.getById(Integer.valueOf(userid));
            }
        } catch (Exception e){
                return null;
        }
       return null;
    }
}
