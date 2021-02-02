package com.njs.check.controller;

import com.alibaba.fastjson.JSONObject;
import com.njs.check.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Api(tags = "登录接口")
@RequestMapping(value = "/common")
public class CommonController {
    @Autowired
    UserService userService;


    @GetMapping("/login")
    @ApiOperation(value = "登陆接口",notes = "传入身份证号码",httpMethod = "GET")
    public Map login(@RequestParam String identityId){
        return userService.login(identityId);
    }
}
