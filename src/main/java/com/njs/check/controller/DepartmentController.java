package com.njs.check.controller;

import com.njs.check.common.ServerResponse;
import com.njs.check.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Api(tags = "机构列表获取")
@RequestMapping(value = "/department", produces = {"application/json;charset=utf-8"})
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/getFistDepList")
    @ApiOperation( value = "获取一级机构列表", notes = "获取一级机构列表", httpMethod = "GET")
    public Map getFirstDepList(){
        return departmentService.getFirstDepList();
    }

    @GetMapping("/getSecondDep")
    @ApiOperation(value = "获取二级机构列表",notes = "获取二级机构列表",httpMethod = "GET" )
    public Map getSecondDepList(@RequestParam Integer firstDepId){return departmentService.getSecondDepList(firstDepId);}
}
