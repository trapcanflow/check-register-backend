package com.njs.check.service;

import com.njs.check.pojo.Application;
import com.njs.check.vo.ApplicationVo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ApplyAndCheckService {

    Map addApply(ApplicationVo applicationVo);

    Map getHisList(Integer userId, Integer type);

    Map getNeedCheckList(Integer userId,Integer type);

    Map abolishApplication(Integer userId,Integer applicationId);

    Map processTheApplication(Integer applicationId,Integer userId, Integer type);

    Map getAllUserName();
}
