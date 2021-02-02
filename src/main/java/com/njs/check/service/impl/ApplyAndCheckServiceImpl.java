package com.njs.check.service.impl;

import com.njs.check.common.ServerResponse;
import com.njs.check.dao.ApplicationMapper;
import com.njs.check.dao.UserMapper;
import com.njs.check.pojo.Application;
import com.njs.check.service.ApplyAndCheckService;
import com.njs.check.utils.DateUtil;
import com.njs.check.vo.ApplicationVo;
import com.njs.check.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ApplyAndCheckServiceImpl implements ApplyAndCheckService {
    @Autowired
    ApplicationMapper applicationMapper;

    @Autowired
    UserMapper userMapper;




    @Override
    public Map addApply(ApplicationVo applicationVo) {
        if(!DateUtil.compareDate(applicationVo.getStartTime(),applicationVo.getEndTime())){
            return ServerResponse.error("申请失败,出差开始的时间不能小于出差结束时间");
        }
        Application application = new Application();
        BeanUtils.copyProperties(applicationVo,application);//将视图层转化成pojo
        Integer applicantId = userMapper.getIdByName(application.getApplicant());
        Integer secondPositionId = userMapper.getSecondDepId(applicantId);
        if(secondPositionId==3){ //副所长跳过部门负责人审核这一不揍
            application.setStatus(2);
        }
        application.setStatus(0);
        application.setAdviseId(userMapper.getIdByName(application.getAdvise()));
        application.setApprovalId(userMapper.getIdByName(application.getApproval()));
        if(applicationMapper.insertSelective(application)==0){
            return ServerResponse.error("插入失败");
        }
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
    public Map processTheApplication(Integer applicationId,Integer userId, Integer type) {
        int firstPosition = userMapper.getFirstPositionById(userId);
        if(firstPosition==2){
            Application application = applicationMapper.selectByPrimaryKey(applicationId);
            if(application.getStatus()!=0){
                return ServerResponse.error("该申请表不是可以给部门负责人的申请的表");
            }
            switch (type){
                case 0://通过
                    applicationMapper.updateStatusByAdviseAndTime(applicationId,userId,2,new Date());
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
