package com.njs.check.controller;

import com.njs.check.service.ApplyAndCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@Api(tags = "审批端接口")
@RequestMapping("/check")
public class CheckController {
    @Autowired
    ApplyAndCheckService applyAndCheckService;

    @GetMapping("/getCheckList")
    @ApiOperation(value = "获取审批列表，需传入userId和type",notes = "type为0,获取所有列表包括需要审批的表单、已经拒绝的表单和已经通过的表单，type为1时获取需要审批的，type为2时获取已经通过的、type为3时获取拒绝的")
    public Map getApplicationList(Integer userId,Integer type){
        return applyAndCheckService.getNeedCheckList(userId,type);
    }

    @GetMapping("/checkApp")
    @ApiOperation(value = "审批申请操作",notes = "需要传入申请表单id，和用户id和type，type为0通过，type为1则拒绝",httpMethod = "GET")
    public Map checkApplication(Integer applicationId,Integer userId,Integer type) throws Exception {
        return applyAndCheckService.processTheApplication(applicationId,userId,type);
    }

    @GetMapping("/getApplication")
    @ApiOperation(value = "获得申请表" )
    public Map getApplication(Integer applicationId){ return applyAndCheckService.getApplication(applicationId);}

    @GetMapping("/getOpenAndOrApplication")
    @ApiOperation(value = "领导获取所以的行程")
    public Map getOpenApplication(Date startTime, Date endTime){
        return applyAndCheckService.getOpenAndOrApplication(startTime,endTime);
    }
}
