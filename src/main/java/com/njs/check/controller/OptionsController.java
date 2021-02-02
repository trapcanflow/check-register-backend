package com.njs.check.controller;

import com.njs.check.service.OptionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Api(tags = "审核表选项接口")
@RequestMapping("/options")
public class OptionsController {

    @Autowired
    OptionsService optionsService;

    @GetMapping("/getTransportList")
    @ApiOperation(value = "获取乘坐交通工具的选项列表",notes = "获取选择乘坐交通工具的选项列表",httpMethod = "GET")
    public Map getTransportList(){return optionsService.getOptionsTransportList();}

    @GetMapping("/getTransportBeyondList")
    @ApiOperation(value = "获取超标原因的选项列表",notes = "获取超标原因的选项列表",httpMethod = "GET")
    public Map getTransportBeyondList(){return optionsService.getOptionsBeyondList();}

    @GetMapping("/getFundsList")
    @ApiOperation(value = "获取经费来源的选项列表",notes = "获取经费来源的选项列表",httpMethod = "GET")
    public Map getFundsList(){return optionsService.getOptionsFundsList();}

    @GetMapping("/getReasonList")
    @ApiOperation(value = "获取出差事由的选项列表",notes = "获取出差事由的选项列表",httpMethod = "GET")
    public Map getReasonList(){return optionsService.getOptionsReasonList();}

    @GetMapping("/getDepHeaderList")
    @ApiOperation(value = "获取部门负责人的选项列表",notes = "获取部门负责人的选项列表",httpMethod = "GET")
    public Map getDepHeaderList(@RequestParam Integer userId){return optionsService.getDepHeaderList(userId);}

    @GetMapping("/getLeaderList")
    @ApiOperation(value = "获取部门领导列表",notes = "获取部门领导列表",httpMethod = "GET")
    public Map getLeaderList(@RequestParam Integer userId){return optionsService.getLeaderList(userId);}
}
