package com.njs.check.dao;

import com.njs.check.pojo.FirstDep;

import java.util.List;

public interface FirstDepMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FirstDep record);

    int insertSelective(FirstDep record);

    FirstDep selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FirstDep record);

    int updateByPrimaryKey(FirstDep record);

    List<FirstDep> selectAll();

    String getNameById(Integer firstDepId);
}