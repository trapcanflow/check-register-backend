package com.njs.check.service.impl;

import com.njs.check.common.ServerResponse;
import com.njs.check.dao.FirstDepMapper;
import com.njs.check.dao.FirstPositionMapper;
import com.njs.check.dao.SecondDepMapper;
import com.njs.check.dao.SecondPositionMapper;
import com.njs.check.pojo.FirstPosition;
import com.njs.check.pojo.SecondDep;
import com.njs.check.pojo.SecondPosition;
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

    @Autowired
    FirstPositionMapper firstPositionMapper;

    @Autowired
    SecondPositionMapper secondPositionMapper;

    @Override
    public Map getFirstDepList() {
        return ServerResponse.success(firstDepMapper.selectAll());
    }

    @Override
    public Map getSecondDepList(Integer firstDepId) {
        return ServerResponse.success(secondDepMapper.selectListByFirstId(firstDepId));
    }

    @Override
    public Map getFirstPositionList() {
        return ServerResponse.success(firstPositionMapper.selectAll());
    }

    @Override
    public Map getSecondPositionList(Integer firstPositionId) {
        return ServerResponse.success(secondPositionMapper.selectListByFirstId(firstPositionId));
    }

    @Override
    public Map addNewDepartment(String departmentName) {
        SecondDep secondDep = new SecondDep();
        secondDep.setFirstDepId(1);
        secondDep.setSecondDepName(departmentName);
        int insertRow = secondDepMapper.insertSelective(secondDep);
        if(insertRow==0) return ServerResponse.error("新增部门失败") ;
        return ServerResponse.success("新增成功");
    }

    @Override
    public Map deleteDepartment(Integer secondDepId) {
        int deleteRow = secondDepMapper.deleteByPrimaryKey(secondDepId);
        if(deleteRow==0) return ServerResponse.error("删除失败");
        return ServerResponse.success("删除成功");
    }
}
