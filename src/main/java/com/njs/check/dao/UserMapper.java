package com.njs.check.dao;

import com.njs.check.pojo.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<String> getUpperLeaderNameList(Integer secondDepId);

    List<String> getLeader(Integer firstDepId);

    User getUserByIdentityId(String identityId);

    Integer getIdByName(String name);

    int getFirstPositionById(Integer userId);

    List<String> getAllName();

    Integer getSecondDepId(Integer userId);
}