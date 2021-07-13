package com.njs.check.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public interface SummerService {

    Map addOrUpdateSummer(Integer applicationId,String title,String text);

    Map uploadFile(MultipartFile file,String title,Integer applicationId);

    Map selectSummers(Integer userId);

    Map checkOneSummer(Integer applicationId);

    Map deleteFile(Integer fileId);
}
