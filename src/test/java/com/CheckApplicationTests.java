package com;

import com.njs.check.dao.ApplicationMapper;
import com.njs.check.dao.SecondDepMapper;
import com.njs.check.dao.UserMapper;
import com.njs.check.pojo.Application;
import com.njs.check.service.ExportService;
import com.njs.check.service.impl.ExportServiceImpl;
import com.njs.check.utils.DateUtil;
import com.njs.check.utils.FreeMarkerUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.njs.check.utils.PdfUtil.createPdfStream;

@SpringBootTest
class CheckApplicationTests {

    private String filePath; //文件路径
    private String fileName; //文件名称
    private String fileOnlyName; //文件唯一名称

    @Autowired
    DataSource dataSource;

    @Autowired
    ApplicationMapper applicationMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ExportService exportService;

    @Autowired
    SecondDepMapper secondDepMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void getDate(){
        long date = System.currentTimeMillis();
        System.out.println(date);
    }

    @Test
    public void wordTest(){
        Map<String,Object> dataMap = applicationMapper.getApplicationMap(4);
        dataMap.put("departmentName","部门名称");
        filePath = "D:/doc_f/";

        //文件唯一名称
        fileOnlyName = "用freemarker生成Word文档" + ".doc";

        //文件名称
        fileName = "用freemarker生成Word文档.doc";
        FreeMarkerUtils.createWord(dataMap, "application.ftl", filePath, fileOnlyName);
    }

    @Test
    public void getNameById(){
        String name = "部门负责人（测试用）";
        System.out.println(name);
        int applicantId = userMapper.getIdByName(name);
        System.out.println(applicantId);
    }

    @Test
    public void getAllName(){
        List<String> nameList = userMapper.getAllName();
        System.out.println(nameList);
    }

    @Test
    public void exportPdf() throws IOException {
        Application application = applicationMapper.selectByPrimaryKey(11);
        Map<String,String> resultMap = applicationToMap(application);
        if(resultMap.size()==0){
            System.out.println("resultMap为空");
        }
        //2.根据模板填充数据源
        ByteArrayOutputStream pdf = createPdfStream("D:\\static\\pdf", "application.pdf", resultMap);
        //3.输出填充后的文件
        String newPadPath="D:\\static\\pdf"+"\\测试.pdf";
        FileOutputStream out = new FileOutputStream(newPadPath);

        out.write(pdf.toByteArray());
        out.flush();
        out.close();
        pdf.close();
    }

    public Map applicationToMap(Application application){
        String departmentName = secondDepMapper.getNameById(userMapper.getSecondDepId(application.getApplicantId()));
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("apply_time", DateUtil.dateToString(application.getApplyTime()));
        resultMap.put("department_name",departmentName);
        resultMap.put("applicant",application.getApplicant());
        resultMap.put("position",application.getPosition());
        resultMap.put("accompany",application.getAccompany());
        resultMap.put("reason",application.getReason());
        resultMap.put("funds_from",application.getFundsFrom());
        resultMap.put("start_time",DateUtil.dateToStr(application.getStartTime()));
        resultMap.put("end_time",DateUtil.dateToStr(application.getEndTime()));
        resultMap.put("departure",application.getDeparture());
        resultMap.put("destination",application.getDestination());
        resultMap.put("transport",application.getTransport());
        resultMap.put("transport_beyond",application.getTransportBeyond());
        resultMap.put("advise",application.getAdvise());
        resultMap.put("advise_time",DateUtil.dateToStr(application.getAdviseTime()));
        resultMap.put("approval",application.getApproval());
        resultMap.put("approval_time",DateUtil.dateToStr(application.getApprovalTime()));
        return resultMap;
    }

    @Test
    public void parseApplication(){
//        Application application = applicationMapper.selectByPrimaryKey(11);
//        System.out.println(applicationToMap(application));
       int random= (int)((Math.random()*9+1)*1000);
        System.out.println(random);
    }
}
