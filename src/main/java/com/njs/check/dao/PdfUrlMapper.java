package com.njs.check.dao;

import com.njs.check.pojo.PdfUrl;

public interface PdfUrlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PdfUrl record);

    int insertSelective(PdfUrl record);

    PdfUrl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PdfUrl record);

    int updateByPrimaryKeyWithBLOBs(PdfUrl record);

    int updateByPrimaryKey(PdfUrl record);

    String selectByApplicantId(Integer applicationId);
}