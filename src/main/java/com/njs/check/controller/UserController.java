package com.njs.check.controller;

import com.alibaba.fastjson.JSONObject;
import com.njs.check.pojo.User;
import com.njs.check.service.AdminService;
import com.njs.check.service.DepartmentService;
import com.njs.check.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/admin")
@RestController
@Api(tags = "用户管理和权限管理")
public class UserController {

    @Autowired
    AdminService adminService;

    @Autowired
    DepartmentService departmentService;

    @PostMapping("/getUsers")
    @ApiOperation(value = "获取人员列表",notes ="如果有传值secondDepId，就获取该部门下的员工，如果没有就获取全部",httpMethod = "POST")
    public Map getUsers(@RequestBody JSONObject jsonObject){
        return adminService.getUsers(jsonObject);
    }

    @GetMapping("/deleteUser")
    @ApiOperation(value = "删除用户",notes ="传入用户的id",httpMethod ="GET")
    public Map deleteUser(Integer userId){
        return adminService.deleteUser(userId);
    }

    @PostMapping("/addUser")
    @ApiOperation(value = "新增用户", notes ="不需要传id，status，wechatId和regTime" ,httpMethod = "POST")
    public Map addUser(@RequestBody User user){
        return adminService.addUser(user);
    }

    @PostMapping("/modifyUser")
    @ApiOperation(value = "修改用户信息",notes = "修改时需要传入用户的id作为标识",httpMethod = "POST")
    public Map modifyUserInfo(@RequestBody User user){
        return adminService.modifyUserInfo(user);
    }

    @GetMapping("/modifyPermission")
    @ApiOperation(value = "修改用户的职位等级",notes = "roleId为1，2，3分别是领导，负责人和普通员工",httpMethod = "GET")
    public Map modifyPermission(Integer userId,Integer roleId){return adminService.modifyPermission(userId,roleId);}

    @GetMapping("/addNewDepartment")
    @ApiOperation(value = "新增部门", httpMethod = "GET")
    public Map addNewDepartment(String departmentName) {return departmentService.addNewDepartment(departmentName);}

    @GetMapping("/deleteDepartment")
    @ApiOperation(value = "删除某一个部门",notes = "传入secondDepId",httpMethod = "GET")
    public Map deleteDepartment(Integer secondDepId){return departmentService.deleteDepartment(secondDepId);}

}
