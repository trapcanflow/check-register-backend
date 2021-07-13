package com.njs.check.service;

import com.alibaba.fastjson.JSONObject;
import com.njs.check.pojo.User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AdminService {
    Map getUsers(JSONObject jsonObject);

    Map deleteUser(Integer userId);

    Map addUser(User user);

    Map modifyUserInfo(User user);

    Map modifyPermission(Integer userId, Integer roleId);
}
