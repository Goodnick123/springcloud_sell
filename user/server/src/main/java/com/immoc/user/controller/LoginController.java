package com.immoc.user.controller;

import com.immoc.user.VO.ResultVO;
import com.immoc.user.constant.CookieConstant;
import com.immoc.user.constant.RedisConstant;
import com.immoc.user.entity.UserInfo;
import com.immoc.user.enums.ResultEnum;
import com.immoc.user.enums.RoleEnum;
import com.immoc.user.service.UserService;
import com.immoc.user.service.impl.UserServiceImpl;
import com.immoc.user.utils.CookieUtil;
import com.immoc.user.utils.ResultVoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/buyer")
    public ResultVO<UserInfo> buyer(@RequestParam("openid") String openid, HttpServletResponse response){
        UserInfo userInfo  = userService.findByOpenid(openid);
        if(userInfo ==null){
            return ResultVoUtil.error(ResultEnum.LOGIN_FAIL);
        }
        //2. 判断角色
        if (RoleEnum.BUYER.getCode() != userInfo.getRole()) {
            return ResultVoUtil.error(ResultEnum.ROLE_ERROR);
        }
        //3. 设置cookie openid = abc
        CookieUtil.set(response, CookieConstant.OPENID,openid,CookieConstant.expire);

        return ResultVoUtil.success();
    }
    @GetMapping("/seller")
    public ResultVO<UserInfo> seller(@RequestParam("openid") String openid, HttpServletResponse response, HttpServletRequest request){
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie!=null && !StringUtils.isEmpty(String.format(RedisConstant.TOKEN_TEMPLATE,cookie.getValue()))){
            ResultVoUtil.success();
        }
        UserInfo userInfo  = userService.findByOpenid(openid);
        if(userInfo == null){
            return ResultVoUtil.error(ResultEnum.LOGIN_FAIL);
        }
        //2. 判断角色
        if (RoleEnum.BUYER.getCode() != userInfo.getRole()) {
            return ResultVoUtil.error(ResultEnum.ROLE_ERROR);
        }
        String token = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE,token),openid,CookieConstant.expire, TimeUnit.SECONDS);
        //3. 设置cookie openid = abc
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.expire);
        return ResultVoUtil.success();
    }
}
