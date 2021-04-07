package com.njs.check.dao;

import com.njs.check.pojo.QRCodeUrl;

public interface QRCodeUrlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QRCodeUrl record);

    int insertSelective(QRCodeUrl record);

    QRCodeUrl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QRCodeUrl record);

    int updateByPrimaryKeyWithBLOBs(QRCodeUrl record);

    int updateByPrimaryKey(QRCodeUrl record);

    String selectUrlByApplicationId(Integer applicationId);
}