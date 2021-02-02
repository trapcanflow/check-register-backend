package com.njs.check.dao;

import com.njs.check.pojo.FirstPosition;

public interface FirstPositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FirstPosition record);

    int insertSelective(FirstPosition record);

    FirstPosition selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FirstPosition record);

    int updateByPrimaryKey(FirstPosition record);

    String getNameById(Integer firstPositionId);
}