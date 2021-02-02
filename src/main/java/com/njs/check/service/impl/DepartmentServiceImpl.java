package com.njs.check.service.impl;

import com.njs.check.common.ServerResponse;
import com.njs.check.dao.FirstDepMapper;
import com.njs.check.dao.SecondDepMapper;
import com.njs.check.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    FirstDepMapper firstDepMapper;

    @Autowired
    SecondDepMapper secondDepMapper;

    @Override
    public Map getFirstDepList() {
        return ServerResponse.success(firstDepMapper.selectAll());
    }

    @Override
    public Map getSecondDepList(Integer firstDepId) {
        return ServerResponse.success(secondDepMapper.selectListByFirstId(firstDepId));
    }
}
