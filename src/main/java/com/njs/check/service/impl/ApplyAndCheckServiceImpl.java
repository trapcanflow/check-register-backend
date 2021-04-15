package com.njs.check.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.njs.check.common.ServerResponse;
import com.njs.check.dao.ApplicationMapper;
import com.njs.check.dao.QRCodeUrlMapper;
import com.njs.check.dao.UserMapper;
import com.njs.check.pojo.Application;
import com.njs.check.pojo.QRCodeUrl;
import com.njs.check.service.ApplyAndCheckService;
import com.njs.check.utils.DateUtil;
import com.njs.check.utils.QRCodeUtil;
import com.njs.check.utils.WeChatUtil;
import com.njs.check.vo.ApplicationVo;
import org.apache.catalina.Server;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ApplyAndCheckServiceImpl implements ApplyAndCheckService {

    private static final String QRCODEPATH = "D:\\static\\qrcode";

    private static final String CONTEXTURL = "http://www.sannongai.cn/checkCode/#/applicationId=";

    private static final String url1 = "http://www.sannongai.cn/businessAdmin/index.html#/";

    private static final String url2 = "http://www.sannongai.cn/businessStaff/index.html#/";

    private static final String TEMPLATEID1 = "dn9jlzOa0vzyKXwq22P9ubWeqqU-xuNQZqCNuA2q1WA";//模板消息审批模板1

    private static final String TEMPLATEID2 = "mitL_0E_8MdGFsPDKDakGH5hFUdY_2BhtcPKt4p_E88";//模板消息审批模板2

    @Autowired
    ApplicationMapper applicationMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    QRCodeUrlMapper qrCodeUrlMapper;

    @Override
    public Map addApply(ApplicationVo applicationVo) {
//      if(!DateUtil.compareDate(applicationVo.getStartTime(),applicationVo.getEndTime())){
//           return ServerResponse.error("申请失败,出差开始的时间不能小于出差结束时间");
//      }
        Application application = new Application();
        BeanUtils.copyProperties(applicationVo,application);//将视图层转化成pojo
        Integer applicantId = userMapper.getIdByName(application.getApplicant());
        Integer secondPositionId = userMapper.getSecondDepId(applicantId);
        application.setApplicantId(applicantId);
        application.setStatus(0);
        if(secondPositionId==3){ //副所长跳过部门负责人审核这一不揍
            application.setStatus(2);
        }
        if(application.getPosition()==null||application.getPosition().equals("")){
            application.setPosition("无");
        }
        application.setAdviseId(userMapper.getIdByName(application.getAdvise()));
        application.setApprovalId(userMapper.getIdByName(application.getApproval()));
        if(applicationMapper.insertSelective(application)==0){
            return ServerResponse.error("插入失败");
        }
        String[] fillData = {"出差审批",DateUtil.dateToString(application.getStartTime())+DateUtil.dateToString(application.getEndTime()),application.getDeparture()+"至"+application.getDestination(),application.getApplicant()};
        WeChatUtil.sendTemplate1(userMapper.getWeChatId(application.getAdviseId()),TEMPLATEID1,url1,fillData);
        return ServerResponse.success();
    }

    @Override
    //填写端获取list
    public Map getHisList(Integer userId, Integer type) {
        List<Application> applicationList = new ArrayList<>();
        switch (type){
            case 0:
                applicationList = applicationMapper.getUserCheck(userId,null);//获取全部申请历史
                break;
            case 1:
                applicationList = applicationMapper.getUserUnChecked(userId);
                break;
            case 2:
                applicationList = applicationMapper.getUserCheck(userId,4);//获取已经通过全部审核的申请
                break;
            case 3:
                applicationList = applicationMapper.getUserCheck(userId,1);//获取自己作废的申请
        }
        return ServerResponse.success(application2ApplicationVo(applicationList));
    }

    //用户作废自己的申请
    @Override
    public Map abolishApplication(Integer userId,Integer applicationId) {
        int resultRow = applicationMapper.updateStatusTo1ByUserId(userId,applicationId);
        if(resultRow==1){
            return ServerResponse.success();
        }
        return ServerResponse.error("作废失败");
    }

    @Override
    public Map getNeedCheckList(Integer userId,Integer type) {
        int firstPosition = userMapper.getFirstPositionById(userId);
        List<Application> applicationList = new ArrayList<>();
        if(firstPosition==2){ //部门负责人的查询列表
            switch (type){
                case 0:
                    applicationList = applicationMapper.selectHisListByAdviseId(userId);
                    //获取历史的审批报表，包括需要审核的、已经通过的和拒绝的
                    break;
                case 1:
                    applicationList = applicationMapper.selectListByAdviseId(userId,0);
                    //获取需要审批的报表
                    break;
                case 2:
                    applicationList = applicationMapper.selectListByAdviseId(userId,2);
                    //获取已经通过审批的报表
                    break;
                case 3:
                    applicationList = applicationMapper.selectListByAdviseId(userId,3);
                    //获取拒绝通过的报表
                    break;
            }
            return ServerResponse.success(application2ApplicationVo(applicationList));
        }if(firstPosition==1){ //所领导的查询列表
            switch (type){
                case 0:
                    applicationList = applicationMapper.selectHisListByApprovalId(userId);
                    //获取历史的审批报表，包括需要审核的、已经通过的和拒绝的
                    break;
                case 1:
                    applicationList = applicationMapper.selectListByApprovalId(userId,2);
                    break;
                //获取需要审批的报表
                case 2:
                    applicationList = applicationMapper.selectListByApprovalId(userId,4);
                    break;
                //获取已经通过审批的报表
                case 3:
                    applicationList = applicationMapper.selectListByApprovalId(userId,5);
                 //获取拒绝通过的报表
            }
            return ServerResponse.success(application2ApplicationVo(applicationList));
        }
        return ServerResponse.error("抱歉，你没有需要审批的报表");
    }


    //处理申请
    @Override
    public Map processTheApplication(Integer applicationId,Integer userId, Integer type) throws Exception {
        int firstPosition = userMapper.getFirstPositionById(userId);
        if(firstPosition==2){
            Application application = applicationMapper.selectByPrimaryKey(applicationId);
            if(application.getStatus()!=0){
                return ServerResponse.error("该申请表不是可以给部门负责人的申请的表");
            }
            switch (type){
                case 0://通过
                    applicationMapper.updateStatusByAdviseAndTime(applicationId,userId,2,new Date());
                    String[] fillData = {"出差审批",DateUtil.dateToString(application.getStartTime())+DateUtil.dateToString(application.getEndTime()),application.getDeparture()+"至"+application.getDestination(),application.getApplicant()};
                    WeChatUtil.sendTemplate1(userMapper.getWeChatId(application.getApprovalId()),TEMPLATEID1,url1,fillData);
                    break;
                case 1://拒绝
                    applicationMapper.updateStatusByAdvise(applicationId,userId,3);
                    break;
            }
            return ServerResponse.success();
        }if(firstPosition==1){
            Application application = applicationMapper.selectByPrimaryKey(applicationId);
            if(application.getStatus()!=2){
                return ServerResponse.error("该申请表不是可以给所领导的申请的表");
            }
            switch (type){
                case 0://通过
                    applicationMapper.updateStatusByApprovalAndTime(applicationId,userId,4,new Date());
                     //通过之后生成二维码
                    String qrCodeName = applicationId+"-"+userId+System.currentTimeMillis();
                    QRCodeUtil.encode(CONTEXTURL+applicationId,null,QRCODEPATH,qrCodeName,true);
                    QRCodeUrl qrCodeUrl = new QRCodeUrl();
                    qrCodeUrl.setApplicationId(applicationId);
                    qrCodeUrl.setQrcodeUrl(QRCODEPATH+"\\"+qrCodeName+".jpg");
                    qrCodeUrlMapper.insertSelective(qrCodeUrl);
                    String[] fillDate = {"关于"+application.getDestination()+"的出差申请",DateUtil.dateToString(application.getStartTime())+"至"+DateUtil.dateToString(application.getEndTime())};
                    WeChatUtil.sendTemplate2(userMapper.getWeChatId(application.getApplicantId()),TEMPLATEID2,url2,fillDate);
                    break;
                case 1://拒绝
                     applicationMapper.updateStatusByApproval(applicationId,userId,5);
                    break;
            }
            return ServerResponse.success();
        }
        return ServerResponse.error("无效操作");
    }

    @Override
    public Map getAllUserName() {
        List<String> nameList = userMapper.getAllName();
        return ServerResponse.success(nameList);
    }

    @Override
    public Map getApplication(Integer applicationId) {
        Application application = applicationMapper.selectByPrimaryKey(applicationId);
        return ServerResponse.success(application);
    }

    @Override
    public Map getOpenApplication(Date startTime, Date endTime) {
        if(startTime == null && endTime == null) {
            Date weekStart = DateUtil.getDateStartOfTheWeek();
            Date weekEnd = DateUtil.getDateEndOfTheWeek();
            List<JSONObject> application = applicationMapper.getOpenApplication(weekStart,weekEnd,0);
            return ServerResponse.success(application);
        }
        List<JSONObject> applications = applicationMapper.getOpenApplication(startTime,endTime,0);
        return ServerResponse.success(applications);
    }

    @Override
    public Map getOpenAndOrApplication(Date startTime, Date endTime) {
        if(startTime == null && endTime == null) {
            Date weekStart = DateUtil.getDateStartOfTheWeek();
            Date weekEnd = DateUtil.getDateEndOfTheWeek();
            List<JSONObject> application = applicationMapper.getOpenOrNotApplication(weekStart,weekEnd);
            return ServerResponse.success(application);
        }
        List<JSONObject> applications = applicationMapper.getOpenOrNotApplication(startTime,endTime);
        return ServerResponse.success(applications);
    }

    public List<ApplicationVo> application2ApplicationVo(List<Application> applicationList){ //将不需要展示到前端的属性去掉
        List<ApplicationVo> applicationVos = new ArrayList<>();
        for(Application application:applicationList){
            ApplicationVo applicationVo = new ApplicationVo();
            BeanUtils.copyProperties(application,applicationVo);
            applicationVos.add(applicationVo);
        }
        return applicationVos;
    }
}
