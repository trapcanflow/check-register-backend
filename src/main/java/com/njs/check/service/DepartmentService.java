package com.njs.check.service;

import com.njs.check.common.ServerResponse;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface DepartmentService {
    Map getFirstDepList();

    Map getSecondDepList(Integer firstDepId);

    Map getFirstPositionList();

    Map getSecondPositionList(Integer firstPositionId);

    Map addNewDepartment(String departmentName);

    Map deleteDepartment(Integer secondDepId);
}
