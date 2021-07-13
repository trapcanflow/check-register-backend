package com.njs.check.controller;

import com.alibaba.fastjson.JSONObject;
import com.njs.check.service.ApplyAndCheckService;
import com.njs.check.vo.ApplicationVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@Api(tags = "填报申请接口")
@RequestMapping("/applyCheck")
public class ApplyController {
    @Autowired
    ApplyAndCheckService applyAndCheckService;

    @RequestMapping(value = "/addApply",method = RequestMethod.POST)
    @ApiOperation(value = "填报申请，isOpen参数代表0是公开的，1不公开",httpMethod = "POST")
    public Map addApply(@RequestBody ApplicationVo applicationVo){ return applyAndCheckService.addApply(applicationVo);}

    @GetMapping("/getHisList")
    @ApiOperation(value = "获取员工的申请列表（包括已通过的和未通过的）",notes = "type为0获取所有包括（未通过的、已通过的和自己作废的），为0时获取全部，为1时获取未通过的，为2时获取已通过的，为3时获取用户自己作废的")
    public Map getHisList(Integer userId,Integer type){return applyAndCheckService.getHisList(userId,type);}

    @GetMapping("/abolishApplication")
    @ApiOperation(value = "用户作废自己的申请")
    public Map abolishApplication(Integer userId,Integer applicationId){return applyAndCheckService.abolishApplication(userId,applicationId);}

    @GetMapping("/getAllUserName")
    @ApiOperation(value ="获取所有的用户姓名" )
    public Map getAllUserName(){return applyAndCheckService.getAllUserName();}

    @GetMapping("/getOpenApplication")
    @ApiOperation(value = "员工获取公开的行程,两个时间不传的时候默认查询本周的行程")
    public Map getOpenApplication(String startTime,String endTime){
        return applyAndCheckService.getOpenApplication(startTime,endTime);
    }
}

