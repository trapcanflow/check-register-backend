package com.njs.check.dao;

import com.njs.check.pojo.OptionsTransport;

import java.util.List;

public interface OptionsTransportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OptionsTransport record);

    int insertSelective(OptionsTransport record);

    OptionsTransport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OptionsTransport record);

    int updateByPrimaryKeyWithBLOBs(OptionsTransport record);

    int updateByPrimaryKey(OptionsTransport record);

    List<OptionsTransport> getAll();
}