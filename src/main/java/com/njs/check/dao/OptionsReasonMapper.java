package com.njs.check.dao;

import com.njs.check.pojo.OptionsReason;

import java.util.List;

public interface OptionsReasonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OptionsReason record);

    int insertSelective(OptionsReason record);

    OptionsReason selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OptionsReason record);

    int updateByPrimaryKeyWithBLOBs(OptionsReason record);

    int updateByPrimaryKey(OptionsReason record);

    List<OptionsReason> getAll();
}