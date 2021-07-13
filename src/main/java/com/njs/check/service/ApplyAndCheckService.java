package com.njs.check.service;

import com.alibaba.fastjson.JSONObject;
import com.njs.check.pojo.Application;
import com.njs.check.vo.ApplicationVo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public interface ApplyAndCheckService {

    Map addApply(ApplicationVo applicationVo);

    Map getHisList(Integer userId, Integer type);

    Map getNeedCheckList(Integer userId,Integer type);

    Map abolishApplication(Integer userId,Integer applicationId);

    Map processTheApplication(Integer applicationId,Integer userId, Integer type) throws Exception;

    Map getAllUserName();

    Map getApplication(Integer applicationId);

    Map getOpenApplication(String startTime, String endTime);

    Map getOpenAndOrApplication(String startTime, String endTime);
}
