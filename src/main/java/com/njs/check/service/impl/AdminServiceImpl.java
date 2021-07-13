package com.njs.check.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.njs.check.common.ServerResponse;
import com.njs.check.dao.UserMapper;
import com.njs.check.pojo.User;
import com.njs.check.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Map getUsers(JSONObject jsonObject) {
        List<User> userList;
        if(jsonObject.containsKey("secondDepId")) {
            int secondDepId = jsonObject.getIntValue("secondDepId");
            userList = userMapper.getUsersBySecondDepId(secondDepId);
        }else{
            userList = userMapper.getAllUser();
        }
        return ServerResponse.success(userList);
    }

    @Override
    public Map deleteUser(Integer userId) {
        int resultRow = userMapper.deleteByPrimaryKey(userId);
        if(resultRow==0) return ServerResponse.error("删除失败");
        return ServerResponse.success("删除成功");
    }

    @Override
    public Map addUser(User user) {
        int resultRow = userMapper.insertSelective(user);
        if(resultRow==0) return ServerResponse.error("插入失败");
        return ServerResponse.success("插入成功");
    }

    @Override
    public Map modifyUserInfo(User user) {
        int resultRow = userMapper.updateByPrimaryKeySelective(user);
        if(resultRow==0) return ServerResponse.error("更新失败");
        return ServerResponse.success("更新成功");
    }

    @Override
    public Map modifyPermission(Integer userId, Integer roleId) {
        int updateRow = userMapper.updateFirstPositionByUserId(userId,roleId);
        if(updateRow==0) return ServerResponse.error("更新失败");
        return ServerResponse.success("更新成功");
    }
}
