package com.njs.check.dao;

import com.njs.check.pojo.OptionsFunds;

import java.util.List;

public interface OptionsFundsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OptionsFunds record);

    int insertSelective(OptionsFunds record);

    OptionsFunds selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OptionsFunds record);

    int updateByPrimaryKeyWithBLOBs(OptionsFunds record);

    int updateByPrimaryKey(OptionsFunds record);

    List<OptionsFunds> getAll();
}