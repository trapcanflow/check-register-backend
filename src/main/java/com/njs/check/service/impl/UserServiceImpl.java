package com.njs.check.service.impl;

import com.njs.check.common.ServerResponse;
import com.njs.check.dao.*;
import com.njs.check.pojo.User;
import com.njs.check.service.UserService;
import com.njs.check.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    FirstPositionMapper firstPositionMapper;

    @Autowired
    SecondPositionMapper secondPositionMapper;

    @Autowired
    FirstDepMapper firstDepMapper;

    @Autowired
    SecondDepMapper secondDepMapper;

    @Override
    public Map login(String identityId) {
        User user = userMapper.getUserByIdentityId(identityId);
        if(user!=null){
            return ServerResponse.success(user2UserVo(user));
        }else
        return ServerResponse.error("身份证不存在或者输入错误");
    }

    public UserVo user2UserVo(User user){
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        userVo.setFirstPosition(firstPositionMapper.getNameById(user.getFirstPositionId()));
        userVo.setSecondPosition(secondPositionMapper.getNameById(user.getSecondPositionId()));
        userVo.setFirstDepName(firstDepMapper.getNameById(user.getFirstDepId()));
        userVo.setSecondDepName(secondDepMapper.getNameById(user.getSecondDepId()));
        return userVo;
    }
}
