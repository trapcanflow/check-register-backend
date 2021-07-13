package com.njs.check.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.njs.check.common.ServerResponse;
import com.njs.check.dao.ApplicationMapper;
import com.njs.check.dao.SummerMapper;
import com.njs.check.dao.SummerUrlMapper;
import com.njs.check.pojo.Application;
import com.njs.check.pojo.Summer;
import com.njs.check.pojo.SummerUrl;
import com.njs.check.service.FileUploadService;
import com.njs.check.service.SummerService;

import com.njs.check.utils.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SummerServiceImpl implements SummerService {

    @Autowired
    SummerMapper summerMapper;

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    SummerUrlMapper summerUrlMapper;

    @Autowired
    ApplicationMapper applicationMapper;

    @Override
    public Map addOrUpdateSummer(Integer applicationId,String title,String text) {
        Summer summer = new Summer();
        summer.setApplicationId(applicationId);
        summer.setText(text);
        summer.setTitle(title);
        summer.setUserId(applicationMapper.selectUserIdByApplicationId(applicationId));
        if(summerMapper.selectByApplicationId(applicationId)==1) {
            summerMapper.updateByPrimaryKeySelective(summer);
        }else{
            summer.setCreateTime(new Date());
            summerMapper.insertSelective(summer);
        }
        return ServerResponse.success();
    }

    @Override
    @Transactional
    public Map uploadFile(MultipartFile file,String title,Integer applicationId) {
        String path = PropertiesUtil.getProperty("fileUploadDir") + "summer";
        String fileName = fileUploadService.upload(file, path);
        if (fileName == null) {
            log.error("上传{}失败，事务回滚", file.getOriginalFilename());
            throw new RuntimeException("上传" + file.getOriginalFilename() + "失败,事务回滚！");
        }
        SummerUrl summerUrl = new SummerUrl();
        summerUrl.setApplicationId(applicationId);
        summerUrl.setFileUrl("file/summer/"+fileName);
        summerUrl.setTitle(title);
        summerUrl.setFileType(getFileType(fileName));
        summerUrlMapper.insertSelective(summerUrl);
        return ServerResponse.success(fileName+"已成功上传");
    }

    @Override
    public Map selectSummers(Integer userId) {
        List<Summer> summerList = summerMapper.selectListByUserId(userId);
        return ServerResponse.success(summerList);
    }

    @Override
    public Map checkOneSummer(Integer applicationId) {
        Map<String, Object> map = new HashMap<>();
        JSONObject jsonObject = summerMapper.selectInfoByApplicationId(applicationId);
        List<JSONObject> jsonObjects = summerUrlMapper.selectListByApplicationId(applicationId);
        map.put("summer", jsonObject);
        map.put("fileList", jsonObjects);
        return ServerResponse.success(map);
    }

    @Override
    public Map deleteFile(Integer fileId) {
        int deleteRow = summerUrlMapper.deleteByPrimaryKey(fileId);
        if(deleteRow==0) return ServerResponse.error();
        return ServerResponse.success();
    }

    public static String getFileType(String fileName){
        if(fileName.isEmpty()) return "";
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
        return suffix;
    }
}
