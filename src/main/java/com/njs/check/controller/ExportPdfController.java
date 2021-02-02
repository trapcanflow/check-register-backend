package com.njs.check.controller;

import com.njs.check.dao.ApplicationMapper;
import com.njs.check.pojo.Application;
import com.njs.check.service.ExportService;
import com.njs.check.utils.PDFTemplateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "导出接口")
@RequestMapping("/export")
public class ExportPdfController {

    @Autowired
    ApplicationMapper applicationMapper;

    @Autowired
    ExportService exportService;

    @GetMapping("/getApplicationPdf")
    @ApiOperation(value = "获取出差申请pdf",notes = "获取出差申请pdf" )
    public Map downLoadWord(Integer applicantId) throws IOException {
        return exportService.getWordUrl(applicantId);
    }
}
