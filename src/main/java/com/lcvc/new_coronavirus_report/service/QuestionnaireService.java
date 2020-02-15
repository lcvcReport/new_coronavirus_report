package com.lcvc.new_coronavirus_report.service;

import com.lcvc.new_coronavirus_report.dao.*;
import com.lcvc.new_coronavirus_report.model.*;
import com.lcvc.new_coronavirus_report.model.base.PageObject;
import com.lcvc.new_coronavirus_report.model.exception.MyServiceException;
import com.lcvc.new_coronavirus_report.model.exception.MyWebException;
import com.lcvc.new_coronavirus_report.model.query.QuestionnaireQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 负责处理调查表信息
 *
 */
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
@Validated//表示开启sprint的校检框架，会自动扫描方法里的@Valid（@Valid注解一般写在接口即可）
@Service
public class QuestionnaireService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private QuestionnaireDao questionnaireDao;
    @Autowired
    private TeacherQuestionnaireDao teacherQuestionnaireDao;
    @Autowired
    private StudentQuestionnaireDao studentQuestionnaireDao;
    /**
     * 保存填表人的表单
     *
     */
    public void save(@NotNull Questionnaire questionnaire,String ip){
        //设置IP地址
        questionnaire.setIp(ip);
        //必备字段验证，不加spring验证框架
        if(questionnaire.getIdentity()==null){
            throw new MyWebException("提交失败：必须填写身份信息");
        }
        //验证教工号或学号，并赋予相应信息
        if(questionnaire.getIdentity().equals("teacher")){
            questionnaire.setStudentNumber(null);//清空学生的学号字段，防止前端同时提交工号和学号
            if(StringUtils.isEmpty(questionnaire.getTeacherNumber())){
                throw new MyWebException("提交失败：必须填写教工号");
            }
            if(StringUtils.isEmpty(questionnaire.getWorkType())){
                throw new MyWebException("提交失败：必须填写教师工作岗位");
            }
            questionnaire.setTeacherNumber(questionnaire.getTeacherNumber().trim());//清除空格
            Teacher teacher=teacherDao.get(questionnaire.getTeacherNumber());//从教工号读取教师信息
            if(teacher!=null){
                if(questionnaireDao.getQuestionnaireNumberByTeacherToday(teacher.getTeacherNumber())>0){
                    throw new MyServiceException("提交失败：今天已经提交过调查表，请明天再来");
                }
                if(StringUtils.isEmpty(questionnaire.getIdentity())){//如果身份证没有填，则从数据库里面读取。
                    questionnaire.setIdentityCard(teacher.getIdentityCard());//获取身份证
                }
                questionnaire.setName(teacher.getName());//获取姓名
                questionnaire.setSex(teacher.getSex());//获取性别
                //questionnaire.setTel(teacher.getTel());// 电话号码为了保证准确性，暂时都自己填
            }else{
                throw new MyWebException("提交失败：该教工号不存在");
            }
        }else if(questionnaire.getIdentity().equals("student")){
            questionnaire.setTeacherNumber(null);//清空教师的工号字段，防止前端同时提交工号和学号
            if(StringUtils.isEmpty(questionnaire.getStudentNumber())){
                throw new MyWebException("提交失败：必须填写学号");
            }
            if(questionnaire.getPractice()==null){
                throw new MyWebException("提交失败：必须填写您是否正在参加顶岗实习（实习生）");
            }
            if(questionnaire.getPractice()==true){//如果选择了正在实习
                if(StringUtils.isEmpty(questionnaire.getPracticeWorkStatus())){//如果没有选择实习状态
                    throw new MyWebException("提交失败：实习学生必须填写今天的实习情况");
                }
            }
            questionnaire.setStudentNumber(questionnaire.getStudentNumber().trim());//清除空格
            Student student=studentDao.get(questionnaire.getStudentNumber());//从学号读取学生信息
            if(student!=null){
                if(questionnaireDao.getQuestionnaireNumberByStudentToday(student.getStudentNumber())>0){
                    throw new MyServiceException("提交失败：今天已经提交过调查表，请明天再来");
                }
                if(StringUtils.isEmpty(questionnaire.getIdentity())){//如果身份证没有填，则从数据库里面读取。
                    questionnaire.setIdentityCard(student.getIdentityCard());//获取身份证
                }
                questionnaire.setName(student.getName());
                questionnaire.setSex(student.getSex());
                questionnaire.setIdentityCard(student.getIdentityCard());
                questionnaire.setSchoolClass(student.getSchoolClass());//设置班级专业信息
                //questionnaire.setTel(student.getTel());// 电话号码为了保证准确性，暂时都自己填
            }else{
                throw new MyWebException("提交失败：该学生号不存在");
            }
        }else{
            throw new MyWebException("提交失败：数据异常");
        }
        //基础字段验证，不加spring验证框架
        //对其他字段进行验证
        if(StringUtils.isEmpty(questionnaire.getIdentity())){//这里如果为空，说明表单没有身份证信息，数据库也没有身份证信息
            throw new MyWebException("提交失败：请输入身份证号");
        }
        //对其他字段进行验证
        if(StringUtils.isEmpty(questionnaire.getTel())){//这里如果为空，说明表单没有身份证信息，数据库也没有身份证信息
            throw new MyWebException("提交失败：请输入电话号码");
        }

        //常规字段
        if(StringUtils.isEmpty(questionnaire.getMyHealth())){
            throw new MyWebException("提交失败：必须填写本人健康状况");
        }else{
            if(questionnaire.getMyHealth().contains("发热")){//如果有发热关键词
                if(StringUtils.isEmpty(questionnaire.getMyHealth())){//
                    throw new MyWebException("提交失败：发热人员必须填写体温");
                }
            }else{//如果没有发热
                questionnaire.setTemperature(null);//清空该字段
            }
        }
        if(StringUtils.isEmpty(questionnaire.getMyfamilyHealth())){
            throw new MyWebException("提交失败：必须填写本人家庭成员的健康状况");
        }
        if(questionnaire.getTouchHuBeiPerson()==null){
            throw new MyWebException("提交失败：必须填写是否密切接触来自或到达过武汉及湖北其他地区人员");
        }
        if(questionnaire.getConfirmIll()==null){
            throw new MyWebException("提交失败：必须填写是否为疑似病例或确诊病例");
        }

        //必备字段验证，不加spring验证框架
        if(questionnaire.getComefromWuHan()==null){
            throw new MyWebException("提交失败：必须填写是否来自武汉");
        }
        if(questionnaire.getComefromHuBei()==null){
            throw new MyWebException("提交失败：必须填写是否来自湖北");
        }
        if(questionnaire.getArriveWuHan()==null){
            throw new MyWebException("提交失败：必须填写是否去过武汉");
        }
        if(questionnaire.getArriveHuBei()==null){
            throw new MyWebException("提交失败：必须填写是否去过湖北");
        }
        if(questionnaire.getComeFromGZHH()==null){
            throw new MyWebException("提交失败：必须填写是否来自广东、浙江、河南、湖南省");
        }
        if(questionnaire.getArriveGZHH()==null){
            throw new MyWebException("提交失败：必须填写是否去过广东、浙江、河南、湖南省");
        }

        if(questionnaire.getComeFromGZHH()||questionnaire.getArriveGZHH()){
            if(StringUtils.isEmpty(questionnaire.getProvince())){
                throw new MyWebException("提交失败：必须填写是广东、浙江、河南、湖南省哪个省份");
            }
        }


        //如果来自武汉湖北,广东、浙江、河南、湖南省的市外人员
        if(questionnaire.getComefromHuBei()||questionnaire.getComefromWuHan()||questionnaire.getComeFromGZHH()){
            if(questionnaire.getLeaveHubei()==null){
                throw new MyWebException("提交失败：必须填写离开该省时间");
            }
            if(StringUtils.isEmpty(questionnaire.getLeaveHubeiWay())){
                throw new MyWebException("提交失败：必须填写回柳州的方式：车次/航班/汽车/自驾等");
            }
        }
        //检查是去过湖北或武汉,或当前仍旧停留的
        if(questionnaire.getArriveHuBei()||questionnaire.getArriveWuHan()){//如果去过武汉湖北，但可能还没回来，所以一些字段不做限制
            if(questionnaire.getStayInHubei()==null){
                throw new MyWebException("提交失败：必须填写是否当前还停留在该省");
            }
            if(questionnaire.getStayInHubei()){//如果选择了停留
                if(StringUtils.isEmpty(questionnaire.getEpidemicArea())){//选择停留的必须写
                    throw new MyWebException("提交失败：必须填写疫区居住地");
                }
                if(questionnaire.getLeaveLiuZhou()==null){//只有短时停留的采血
                    throw new MyWebException("提交失败：必须填写离柳时间");
                }
            }
        }

        //检查是去过湖北或武汉,广东、浙江、河南、湖南省的
        if(questionnaire.getArriveHuBei()||questionnaire.getArriveWuHan()||questionnaire.getArriveGZHH()){
            if(StringUtils.isEmpty(questionnaire.getRegisteredPlace())){//户口地址
                throw new MyWebException("提交失败：必须填写户口地址");
            }
        }


        //如果密切接触过来自或到达过湖北疫区人员情况表，2月12日表取消，后续如果没有则删除本次验证
        if(questionnaire.getTouchHuBeiPerson()){
            if(StringUtils.isEmpty(questionnaire.getTouchHuBeiPersonName())){
                throw new MyWebException("提交失败：必须填写接触过疫区人员的姓名");
            }
            if(questionnaire.getTouchHuBeiTime()==null){
                throw new MyWebException("提交失败：必须填写密切接触的时间");
            }
            if(StringUtils.isEmpty(questionnaire.getTouchHuBeiDescription())){
                throw new MyWebException("提交失败：必须描述和疫区人员密切接触过程");
            }
        }


        //满足需要上报条件的（湖北来的人，去过湖北，去过广东、浙江、河南、湖南省）,接触疫区不用上报
        if(questionnaire.getComefromHuBei()||questionnaire.getComefromWuHan()||questionnaire.getArriveHuBei()||questionnaire.getArriveWuHan()||questionnaire.getComeFromGZHH()||questionnaire.getArriveGZHH()){//如果去过武汉湖北
            if(questionnaire.getArriveLiuZhou()==null){
                throw new MyWebException("提交失败：必须填写回柳时间");
            }
        }


        //满足任何一个需要上报条件的（湖北来的人，去过湖北，去过广东、浙江、河南、湖南省，接触过疫区）
        if(questionnaire.getComefromHuBei()||questionnaire.getComefromWuHan()||questionnaire.getArriveHuBei()||questionnaire.getArriveWuHan()||questionnaire.getTouchHuBeiPerson()||questionnaire.getComeFromGZHH()||questionnaire.getArriveGZHH()){//如果去过武汉湖北
            if(StringUtils.isEmpty(questionnaire.getAddressInLiuZhou())){//柳州居住地
                throw new MyWebException("提交失败：必须填写在柳州的居住地");
            }
        }
        //都验证通过了，就保存表单信息
        questionnaireDao.save(questionnaire);//保存调查表所有信息。（重要：后续设计，如果对性能有影响，安全人群将不记录入总表。）
        //保存到日常居家观察健康状况表
        if(questionnaire.getIdentity().equals("teacher")){
            //保存到教师记录表
            TeacherQuestionnaire teacherQuestionnaire=new TeacherQuestionnaire();
            teacherQuestionnaire.setName(questionnaire.getName());
            teacherQuestionnaire.setWorkType(questionnaire.getWorkType());
            teacherQuestionnaire.setMyHealth(questionnaire.getMyHealth());
            teacherQuestionnaire.setMyfamilyHealth(questionnaire.getMyfamilyHealth());
            teacherQuestionnaire.setTouchHuBeiPerson(questionnaire.getTouchHuBeiPerson());
            teacherQuestionnaire.setConfirmIll(questionnaire.getConfirmIll());
            teacherQuestionnaire.setIntro(questionnaire.getIntro());
            teacherQuestionnaire.setIp(questionnaire.getIp());
            teacherQuestionnaireDao.save(teacherQuestionnaire);
        }else{
            //保存到学生记录表
            StudentQuestionnaire studentQuestionnaire=new StudentQuestionnaire();
            studentQuestionnaire.setName(questionnaire.getName());
            studentQuestionnaire.setMyHealth(questionnaire.getMyHealth());
            studentQuestionnaire.setMyfamilyHealth(questionnaire.getMyfamilyHealth());
            studentQuestionnaire.setTouchHuBeiPerson(questionnaire.getTouchHuBeiPerson());
            studentQuestionnaire.setConfirmIll(questionnaire.getConfirmIll());
            studentQuestionnaire.setIntro(questionnaire.getIntro());
            studentQuestionnaire.setIp(questionnaire.getIp());
            studentQuestionnaireDao.save(studentQuestionnaire);
        }
        //throw new MyServiceException("测试回滚");
    }

    /**
     * 分页查询所有调查表
     * @param page 当前页面
     * @param limit  每页最多显示的记录数
     * @param questionnairequery 查询条件类
     */
    public PageObject query(Integer page, Integer limit, QuestionnaireQuery questionnairequery){
        PageObject pageObject = new PageObject(limit,page,questionnaireDao.querySize(questionnairequery));
        pageObject.setList(questionnaireDao.query(pageObject.getOffset(),pageObject.getLimit(),questionnairequery));
        return pageObject;
    }

    /**
     * 查询所有调查表
     * @param questionnairequery 查询条件类
     */
    public List<Questionnaire> query(QuestionnaireQuery questionnairequery){
        return questionnaireDao.readAll(questionnairequery);
    }

    /**
     * 查询所有调查表
     * @param questionnairequery 查询条件类
     * @return  返回数量
     */
    public Integer querySize(QuestionnaireQuery questionnairequery){
        return questionnaireDao.querySize(questionnairequery);
    }


}
