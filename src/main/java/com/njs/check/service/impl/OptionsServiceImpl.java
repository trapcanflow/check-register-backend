package com.njs.check.service.impl;

import com.njs.check.common.ServerResponse;
import com.njs.check.dao.*;
import com.njs.check.pojo.User;
import com.njs.check.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OptionsServiceImpl  implements OptionsService {
    @Autowired
    OptionsBeyondMapper optionsBeyondMapper;

    @Autowired
    OptionsFundsMapper optionsFundsMapper;

    @Autowired
    OptionsReasonMapper optionsReasonMapper;

    @Autowired
    OptionsTransportMapper optionsTransportMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public Map getOptionsBeyondList() {
        return ServerResponse.success(optionsBeyondMapper.getAll());
    }

    @Override
    public Map getOptionsFundsList() {
        return ServerResponse.success(optionsFundsMapper.getAll());
    }

    @Override
    public Map getOptionsReasonList() {
        return ServerResponse.success(optionsReasonMapper.getAll());
    }

    @Override
    public Map getOptionsTransportList() {
        return ServerResponse.success(optionsTransportMapper.getAll());
    }

    @Override
    public Map getDepHeaderList(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        List<String> list = userMapper.getUpperLeaderNameList(user.getSecondDepId());
        return ServerResponse.success(list);
    }

    @Override
    public Map getLeaderList(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        List<String> list = userMapper.getLeader(user.getFirstDepId());
        return ServerResponse.success(list);
    }
}
