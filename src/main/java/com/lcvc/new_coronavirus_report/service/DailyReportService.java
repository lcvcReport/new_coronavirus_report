package com.lcvc.new_coronavirus_report.service;

import com.lcvc.new_coronavirus_report.dao.DailyReportDao;
import com.lcvc.new_coronavirus_report.dao.QuestionnaireDao;
import com.lcvc.new_coronavirus_report.dao.TeacherDao;
import com.lcvc.new_coronavirus_report.model.DailyReport;
import com.lcvc.new_coronavirus_report.model.Questionnaire;
import com.lcvc.new_coronavirus_report.model.Teacher;
import com.lcvc.new_coronavirus_report.model.exception.MyServiceException;
import com.lcvc.new_coronavirus_report.model.exception.MyWebException;
import com.lcvc.new_coronavirus_report.model.form.DailyReportTable;
import com.lcvc.new_coronavirus_report.model.query.DailyReportQuery;
import com.lcvc.new_coronavirus_report.model.query.QuestionnaireQuery;
import com.lcvc.new_coronavirus_report.util.date.MyDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
@Validated//表示开启sprint的校检框架，会自动扫描方法里的@Valid（@Valid注解一般写在接口即可）
@Service
public class DailyReportService {
    @Autowired
    private QuestionnaireDao questionnaireDao;
    @Autowired
    private DailyReportDao dailyReportDao;



    /**
     * 获取当天的重点人群排查管理日报表
     * 说明：本次的数据为实时计算
     * @param currentDay 存储当天的数据进入数据库
     * @return
     */
    public void saveDailyReportInOneDay(Date currentDay){
        DailyReport dailyReport=this.countDailyReportInOneDay(currentDay);
        dailyReport.setReportDate(new java.sql.Date(currentDay.getTime()));//必须记录当天的日期
        //查询是否已经有当天的记录表
        DailyReportQuery dailyReportQuery=new DailyReportQuery();
        dailyReportQuery.setReportDate(new java.sql.Date(currentDay.getTime()));//设置为当前日志
        List<DailyReport> dailyReports=dailyReportDao.readAll(dailyReportQuery);//获取当天已经在数据库存在的表格记录
        if(dailyReports.size()>0){//如果有已经有当天记录，则删除原有记录
            dailyReportDao.deleteObjects(dailyReports);
        }
        dailyReportDao.save(dailyReport);//保存进入数据库
    }


    /**
     * 统计当天的重点人群排查管理日报表
     * 说明：本次的数据为实时计算
     * @param currentDay 获取当天的数据
     * @return
     */
    public DailyReport countDailyReportInOneDay(Date currentDay) {
        DailyReport dailyReportCurrentDay=new DailyReport();
        QuestionnaireQuery questionnairequery=null;
        //来自武汉市的市外人员人数
        questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(currentDay);//查找今天的表内容
        questionnairequery.setComefromWuHan(true);//查询来自武汉的记录
        dailyReportCurrentDay.setComefromWuHanNumber(questionnaireDao.querySize(questionnairequery));
        //来自湖北省（除武汉市外）的市外人员人数
        questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(currentDay);//查找今天的表内容
        questionnairequery.setComefromHuBei(true);//查询来自湖北的记录
        dailyReportCurrentDay.setComefromHuBeiNumber(questionnaireDao.querySize(questionnairequery));
        //我市到过武汉市的人员人数
        questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(currentDay);//查找今天的表内容
        questionnairequery.setArriveWuHan(true);//查询去过武汉的记录
        dailyReportCurrentDay.setArriveWuHanNumber(questionnaireDao.querySize(questionnairequery));
        //我市到过湖北省（除武汉市外）的人员人数
        questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(currentDay);//查找今天的表内容
        questionnairequery.setArriveHuBei(true);//查询去过湖北的记录
        dailyReportCurrentDay.setArriveHuBeiNumber(questionnaireDao.querySize(questionnairequery));
        //密切接触者过疫区人员的人数（不是指疫区人员人数）
        questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(currentDay);//查找今天的表内容
        questionnairequery.setTouchHuBeiPerson(true);//查询去过密切接触疫区人员的记录
        dailyReportCurrentDay.setTouchHuBeiPersonNumber(questionnaireDao.querySize(questionnairequery));
        //我市现在仍在湖北出差、休假、旅游、探亲等短时停留人员的人数
        questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(currentDay);//查找今天的表内容
        questionnairequery.setStayInHubei(true);//查询当前依旧停留在湖北的
        dailyReportCurrentDay.setStayInHubeiNumber(questionnaireDao.querySize(questionnairequery));
        //发热人群统计
        questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(currentDay);//查找今天的表内容
        questionnairequery.setFeverQuery(true);//查询发热病人的记录
        dailyReportCurrentDay.setFeverNumber(questionnaireDao.querySize(questionnairequery));
        return dailyReportCurrentDay;
    }


    /**
     * 获取昨天的日志表（从数据库读取）
     * 说明：如果数据库不存在，说明是第一次运行，未统计，则实时获取
     * @return
     */
    public DailyReport getDailyReportYesterday(){
        DailyReport dailyReportInYesterday=null;
        Date yesterday= MyDateUtil.getDateBefore(new Date(),1);//获取昨天的日期
        //查询昨天的日志
        DailyReportQuery dailyReportQuery=new DailyReportQuery();
        dailyReportQuery.setReportDate(new java.sql.Date(yesterday.getTime()));//设置为昨天的日期
        List<DailyReport> dailyReports=dailyReportDao.readAll(dailyReportQuery);//获取当天已经在数据库存在的表格记录
        if(dailyReports.size()>0){
            dailyReportInYesterday=dailyReports.get(0);;//获取昨天的日志。这里除非系统异常，否则肯定可以获取
        }else{//如果获取不到，一般是第一天，则计算昨天的情况
            dailyReportInYesterday=this.countDailyReportInOneDay(yesterday);
        }
        return dailyReportInYesterday;
    }

    /**
     * 获取今天的重点人群排查管理日报表
     */
    public DailyReportTable getDailyReportInTodayAndYesterDay(){
        DailyReport dailyReportToday=this.countDailyReportInOneDay(new Date());//获取今天的统计数据
        DailyReport dailyReportInYesterday=this.getDailyReportYesterday();//获取昨天的统计数据
        DailyReportTable reportTable=new DailyReportTable();
        reportTable.setDailyReportToday(dailyReportToday);
        reportTable.setDailyReportInYesterday(dailyReportInYesterday);
        return reportTable;
    }

}
