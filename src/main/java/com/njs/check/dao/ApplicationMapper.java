package com.njs.check.dao;

import com.njs.check.pojo.Application;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ApplicationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Application record);

    int insertSelective(Application record);

    Application selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Application record);

    int updateByPrimaryKey(Application record);

//  List<Application> getUserCheck(Integer userId);

    List<Application> getUserCheck(Integer userId, Integer status);

    Map getApplicationMap(Integer applicationId);

    List<Application> selectListByAdviseId(Integer userId,Integer status);

    List<Application> selectListByApprovalId(Integer userId, int status);

    List<Application> getUserUnChecked(Integer userId);

    int updateStatusTo1ByUserId(Integer userId,Integer applicationId);

    List<Application> selectHisListByAdviseId(Integer userId);

    List<Application> selectHisListByApprovalId(Integer userId);

    int updateStatusByAdvise(Integer applicationId, Integer userId, int status);

    int updateStatusByApproval(Integer applicationId, Integer userId, int status);

    int updateStatusByApprovalAndTime(Integer applicationId, Integer userId, int status, Date dateTime);

    int updateStatusByAdviseAndTime(Integer applicationId, Integer userId, int status, Date dateTime);

//  List<Application> getUserCheck2();
}