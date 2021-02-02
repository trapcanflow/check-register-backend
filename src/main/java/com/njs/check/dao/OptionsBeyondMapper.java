package com.njs.check.dao;

import com.njs.check.pojo.OptionsBeyond;

import java.util.List;
import java.util.Map;

public interface OptionsBeyondMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OptionsBeyond record);

    int insertSelective(OptionsBeyond record);

    OptionsBeyond selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OptionsBeyond record);

    int updateByPrimaryKeyWithBLOBs(OptionsBeyond record);

    int updateByPrimaryKey(OptionsBeyond record);

    List<OptionsBeyond> getAll();
}