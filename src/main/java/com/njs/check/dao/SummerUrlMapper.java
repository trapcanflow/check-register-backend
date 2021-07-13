package com.njs.check.dao;

import com.alibaba.fastjson.JSONObject;
import com.njs.check.pojo.SummerUrl;

import java.util.List;

public interface SummerUrlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SummerUrl record);

    int insertSelective(SummerUrl record);

    SummerUrl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SummerUrl record);

    int updateByPrimaryKey(SummerUrl record);

    List<JSONObject> selectListByApplicationId(Integer applicationId);
}