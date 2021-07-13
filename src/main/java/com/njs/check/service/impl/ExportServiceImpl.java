package com.njs.check.service.impl;

import com.njs.check.common.ServerResponse;
import com.njs.check.dao.*;
import com.njs.check.pojo.Application;
import com.njs.check.pojo.PdfUrl;
import com.njs.check.service.ExportService;
import com.njs.check.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.njs.check.utils.PdfUtil.createPdfStream;

@Service
public class ExportServiceImpl implements ExportService {

    //freemarker模板文件名字
    private static final String TEMPLATENAME = "application.pdf";

    private static final String TEMPLATEFILEPATH = "D:\\static\\pdf";




    @Autowired
    ApplicationMapper applicationMapper;

    @Autowired
    SecondDepMapper secondDepMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PdfUrlMapper pdfUrlMapper;

    @Autowired
    QRCodeUrlMapper qrCodeUrlMapper;


    @Override
    public Map getWordUrl(Integer applicationId) throws IOException {
        Application application = applicationMapper.selectByPrimaryKey(applicationId);
        if(application.getStatus()!=4){
            return ServerResponse.error("该申请表没有完成审核，无法导出");
        }if(StringUtils.isEmpty(pdfUrlMapper.selectByApplicantId(applicationId))){
            return ServerResponse.success(generatePdf(application));
        }
            return ServerResponse.success(pdfUrlMapper.selectByApplicantId(applicationId));
//        Map<String,String> resultMap = applicationToMap(application);
//        ByteArrayOutputStream pdf = createPdfStream(TEMPLATEFILEPATH, TEMPLATENAME, resultMap);
//        String newPadPath="D:\\static\\pdf"+"\\"+DateUtil.dateToString(application.getApplyTime())+application.getApplicant()+"前往"+application.getDestination()+"出差申请.pdf";
//        FileOutputStream out = new FileOutputStream(newPadPath);
//        out.write(pdf.toByteArray());
//        out.flush();
//        out.close();
//        pdf.close();
    }

    public String generatePdf(Application application) throws IOException{
        Map<String,String> resultMap = applicationToMap(application);
        ByteArrayOutputStream pdf = createPdfStream(TEMPLATEFILEPATH, TEMPLATENAME, resultMap);
        int random= (int)((Math.random()*9+1)*1000);//生成四位数的随机数
        String fileName = System.currentTimeMillis()+"-"+System.currentTimeMillis()+random+".pdf";
        String newPdfPath=TEMPLATEFILEPATH+"\\"+fileName;
        FileOutputStream out = new FileOutputStream(newPdfPath);
        out.write(pdf.toByteArray());
        out.flush();
        out.close();
        pdf.close();
        PdfUrl pdfUrl = new PdfUrl();
        String url = "pdf/"+fileName;
        pdfUrl.setApplicationId(application.getId());
        pdfUrl.setWordUrl(url);
        pdfUrl.setCreateTime(new Date());
        pdfUrlMapper.insertSelective(pdfUrl);
        return url;
    }

    //将申请表转化为ResultMap
    public Map applicationToMap(Application application){
        String departmentName = secondDepMapper.getNameById(userMapper.getSecondDepId(application.getApplicantId()));
        String qrCodeUrl = qrCodeUrlMapper.selectUrlByApplicationId(application.getId());
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("apply_time", DateUtil.dateToString(application.getApplyTime()));
        resultMap.put("department_name",departmentName);
        resultMap.put("applicant",application.getApplicant());
        resultMap.put("position",application.getPosition());
        resultMap.put("accompany",application.getAccompany());
        resultMap.put("reason",application.getReason());
        resultMap.put("funds_from",application.getFundsFrom());
        resultMap.put("start_time",DateUtil.dateToString(application.getStartTime()));
        resultMap.put("end_time",DateUtil.dateToString(application.getEndTime()));
        resultMap.put("departure",application.getDeparture());
        resultMap.put("destination",application.getDestination());
        resultMap.put("transport",application.getTransport());
        resultMap.put("transport_beyond",application.getTransportBeyond());
        resultMap.put("advise",application.getAdvise());
        resultMap.put("advise_time",DateUtil.dateToStr(application.getAdviseTime()));
        resultMap.put("approval",application.getApproval());
        resultMap.put("approval_time",DateUtil.dateToStr(application.getApprovalTime()));
        resultMap.put("img",qrCodeUrl);
        return resultMap;
    }
}
