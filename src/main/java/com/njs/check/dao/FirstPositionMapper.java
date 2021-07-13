package com.njs.check.dao;

import com.njs.check.pojo.FirstPosition;

import java.util.List;

public interface FirstPositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FirstPosition record);

    int insertSelective(FirstPosition record);

    FirstPosition selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FirstPosition record);

    int updateByPrimaryKey(FirstPosition record);

    String getNameById(Integer firstPositionId);

    List<FirstPosition> selectAll();
}