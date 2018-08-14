package com.immoc.user.service;

import com.immoc.user.entity.UserInfo;

public interface UserService {

        UserInfo findByOpenid(String openid);
}
