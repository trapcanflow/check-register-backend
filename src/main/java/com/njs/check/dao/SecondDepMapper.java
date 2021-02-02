package com.njs.check.dao;

import com.njs.check.pojo.SecondDep;

import java.util.List;

public interface SecondDepMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SecondDep record);

    int insertSelective(SecondDep record);

    SecondDep selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecondDep record);

    int updateByPrimaryKey(SecondDep record);

    String getNameById(Integer secondDepId);

    List<SecondDep> selectListByFirstId(Integer firstDepId);
}