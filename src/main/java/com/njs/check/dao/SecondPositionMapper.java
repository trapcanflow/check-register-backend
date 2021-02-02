package com.njs.check.dao;

import com.njs.check.pojo.SecondPosition;

public interface SecondPositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SecondPosition record);

    int insertSelective(SecondPosition record);

    SecondPosition selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecondPosition record);

    int updateByPrimaryKey(SecondPosition record);

    String getNameById(Integer secondDepId);
}