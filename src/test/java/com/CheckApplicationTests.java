package com;

import com.alibaba.fastjson.JSONObject;
import com.njs.check.dao.ApplicationMapper;
import com.njs.check.dao.QRCodeUrlMapper;
import com.njs.check.dao.SecondDepMapper;
import com.njs.check.dao.UserMapper;
import com.njs.check.pojo.Application;
import com.njs.check.pojo.QRCodeUrl;
import com.njs.check.service.ExportService;
import com.njs.check.utils.DateUtil;
import com.njs.check.utils.FreeMarkerUtils;
import com.njs.check.utils.QRCodeUtil;
import com.njs.check.utils.WeChatUtil;
import netscape.javascript.JSObject;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.njs.check.utils.PdfUtil.createPdfStream;

@SpringBootTest
class CheckApplicationTests {

    private static final String QRCODEPATH = "D:\\static\\qrcode";

    private static final String CONTEXTURL = "http://www.sannongai.cn/checkCode/#/applicationId=";

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

    @Autowired
    QRCodeUrlMapper qrCodeUrlMapper;

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
        Application application = applicationMapper.selectByPrimaryKey(215);
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
        resultMap.put("img","D:/static/qrcode/qrcode.jpg");
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

    //二维码生成测试
    @Test
    public void generateQRCode() throws Exception {

        //不含Logo
        Integer applicationId = 240;
        Integer userId = 2;
        String qrCodeName = applicationId+"-"+userId+System.currentTimeMillis();
        QRCodeUtil.encode(CONTEXTURL+applicationId, null, "D:\\static\\qrcode", qrCodeName, true);
        QRCodeUrl qrCodeUrl = new QRCodeUrl();
        qrCodeUrl.setApplicationId(applicationId);
        qrCodeUrl.setQrcodeUrl(QRCODEPATH+"\\"+qrCodeName+".jpg");
        qrCodeUrlMapper.insertSelective(qrCodeUrl);
    }

    @Test
    public void testTemplate(){
        String[] fillData = {"报名疗休养审批","2019.1.1","海南三亚","陈政"};
        String str = WeChatUtil.sendTemplate1("oioOG5qxZ9OJs5FnXVaqdhZ0oumc","dn9jlzOa0vzyKXwq22P9ubWeqqU-xuNQZqCNuA2q1WA","http://120.79.185.173:8080/businessStaff/index.html#/",fillData);
        System.out.println(str);
    }

    public String convert(String s, int numRows){
        if(numRows == 1)
            return s;

        int len = Math.min(s.length(), numRows);
        String []rows = new String[len];
        for(int i = 0; i< len; i++) rows[i] = "";
        int loc = 0;
        boolean down = false;

        for(int i=0;i<s.length();i++) {
            rows[loc] += s.substring(i,i+1);
            if(loc == 0 || loc == numRows - 1)
                down = !down;
            loc += down ? 1 : -1;
        }

        String ans = "";
        for(String row : rows) {
            ans += row;
        }
        return ans;
    }

    @Test
    public void convertTest(){
        Map<Integer,Integer> hashMap = new HashMap<Integer, Integer>();
//        convert("LEETCODE",3);
      //  letterCombinations("23");
//        int[] nums = {1,1,2};
//        removeDuplicates(nums);
        int i  = (1+7+1)/2;
        System.out.println(i);
    }

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        int len = digits.length();
        if(len==0) return combinations;
        Map<Character,String> phoneMap = new HashMap<Character,String>();
        phoneMap.put('2',"abc");
        phoneMap.put('3',"def");
        phoneMap.put('4',"ghi");
        phoneMap.put('5',"jkl");
        phoneMap.put('6',"mno");
        phoneMap.put('7',"pqrs");
        phoneMap.put('8',"tuv");
        phoneMap.put('9',"wxyz");
        StringBuffer sb = new StringBuffer();
        backtrack(combinations,phoneMap,digits,0,sb);
        return combinations;
    }

    public void backtrack(List<String> combinations,Map<Character,String> phoneMap,String digits,int                             index,StringBuffer sb){
        if(index==digits.length()){
            combinations.add(sb.toString());
        }else{
            char digit = digits.charAt(index);
            String letter = phoneMap.get(digit);
            int letterCount = letter.length();
            for(int i = 0;i<letterCount;i++){
                sb.append(letter.charAt(i));
                backtrack(combinations,phoneMap,digits,index+1,sb);
                sb.deleteCharAt(index);
            }
        }
    }

    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if(len==0) return 0;
        int i=0;
        for(int j=1;j<len;j++){
            if(nums[j]!=nums[i])
                i++;
            nums[i]=nums[j];
        }
        return i+1;
    }

    @Test
    public void getWeek() {
//        SimpleDateFormat format  = new SimpleDateFormat("YYYY-MM-dd ");
//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
//        String weekStart = format.format(c.getTime())+" 00:00:00";
//        System.out.println(weekStart);
//
//        Calendar ca = Calendar.getInstance();
//        ca.setFirstDayOfWeek(Calendar.MONDAY);
//        ca.set(Calendar.DAY_OF_WEEK, ca.getFirstDayOfWeek() + 6); // Sunday
//        String weekEnd = format.format(ca.getTime())+" 23:59:59";
//        System.out.println(weekEnd);

        System.out.println(DateUtil.getDateStartOfTheWeek());
        System.out.println(DateUtil.getDateEndOfTheWeek());
//        List<JSONObject> a = applicationMapper.getOpenApplication(null,null);
//        a.toString();
    }
}
