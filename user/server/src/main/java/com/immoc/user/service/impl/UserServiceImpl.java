package com.immoc.user.service.impl;

import com.immoc.user.entity.UserInfo;
import com.immoc.user.repository.UserInfoRepository;
import com.immoc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoRepository.findByOpenid(openid);
    }
}
