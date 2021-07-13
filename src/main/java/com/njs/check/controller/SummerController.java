package com.njs.check.controller;

import com.alibaba.fastjson.JSONObject;
import com.njs.check.service.SummerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/summer")
@Api(tags = "出差总结接口")
public class SummerController {

    @Autowired
    SummerService summerService;

    @PostMapping("/addOrUpdateSummer")
    @ApiOperation(value = "创建或者修改新的出差总结",notes = "需要提交参数applicationId,text,title",httpMethod = "POST")
    public Map addOrUpdateSummer (@RequestBody JSONObject jsonObject){
        Integer applicationId = jsonObject.getIntValue("applicationId");
        String title = jsonObject.getString("title");
        String text = jsonObject.getString("text");
        return summerService.addOrUpdateSummer(applicationId,title,text);
    }

    @PostMapping("/uploadFile")
    @ApiOperation(value = "上传某个出差总结文件",notes = "需要填写文件标题",httpMethod = "POST")
    public Map uploadFile(MultipartFile file,String title,Integer applicationId){
        return summerService.uploadFile(file,title,applicationId);
    }

    @GetMapping("/selectSummers")
    @ApiOperation(value = "获取个人的所有的出差总结",notes = "查看不了上传的文件",httpMethod = "GET")
    public Map selectSummers(Integer userId){
        return summerService.selectSummers(userId);
    }

    @GetMapping("/checkOneSummer")
    @ApiOperation(value = "查看某个特定的出差总结",notes = "返回的是出差总结的文件",httpMethod = "GET")
    public Map checkOneSummer(Integer applicationId){
        return summerService.checkOneSummer(applicationId);
    }

    @GetMapping("/deleteFile")
    @ApiOperation(value = "删除某个出差文件",notes = "",httpMethod = "GET")
    public Map deleteFile(Integer fileId){
        return summerService.deleteFile(fileId);
    }
}