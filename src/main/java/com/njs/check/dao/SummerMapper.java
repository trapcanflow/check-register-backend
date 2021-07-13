package com.njs.check.dao;

import com.alibaba.fastjson.JSONObject;
import com.njs.check.pojo.Summer;

import java.util.List;

public interface SummerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Summer record);

    int insertSelective(Summer record);

    Summer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Summer record);

    int updateByPrimaryKey(Summer record);

    int selectByApplicationId(Integer applicationId);

    List<Summer> selectListByUserId(Integer userId);

    JSONObject selectInfoByApplicationId(Integer applicationId);
}