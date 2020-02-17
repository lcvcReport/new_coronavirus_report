package com.lcvc.new_coronavirus_report.service;

import com.lcvc.new_coronavirus_report.dao.*;
import com.lcvc.new_coronavirus_report.model.form.BaseDateArrayChart;
import com.lcvc.new_coronavirus_report.model.query.QuestionnaireQuery;
import com.lcvc.new_coronavirus_report.util.date.MyDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 负责处理调查表信息
 *
 */
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
@Validated//表示开启sprint的校检框架，会自动扫描方法里的@Valid（@Valid注解一般写在接口即可）
@Service
public class ChartService {
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
     * 查询当天的填报人数情况
     * @return
     */
    public BaseDateArrayChart getRePortPersonProportion(){
        //封装柱状图
        BaseDateArrayChart barData=new BaseDateArrayChart();
        //获取各级学生人数
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setSchoolClass("17");
        Integer student2017=questionnaireDao.querySize(questionnairequery);
        questionnairequery.setSchoolClass("18");
        Integer student2018=questionnaireDao.querySize(questionnairequery);
        questionnairequery.setSchoolClass("19");
        Integer student2019=questionnaireDao.querySize(questionnairequery);
        //获取教师的
        questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setTeacherQuery(true);//查询教师
        questionnairequery.setWorkType("专任教师");
        Integer teacherTeach=questionnaireDao.querySize(questionnairequery);
        questionnairequery.setWorkType("行政人员");
        Integer teacherXingZheng=questionnaireDao.querySize(questionnairequery);
        //封装标签
        barData.setLabels(new String[]{"17级学生","18级学生","19级学生","专任教师","行政人员"});
        barData.setValues(new Integer[]{student2017,student2018,student2019,teacherTeach,teacherXingZheng});
        return barData;
    }

    /**
     * 查询最近几天的生病的填报人数
     * @param dayNumber
     * @return
     */
    public BaseDateArrayChart getMyIllRecently(Integer dayNumber){
        BaseDateArrayChart barData=new BaseDateArrayChart();
        List<String> dates=new ArrayList<String>();//记录日期
        List<Integer> numbers=new ArrayList<Integer>();//记录相应人数
        Date now=new Date();//获取当前时间
        while(dayNumber>=1){//最开始的日期算起，按时间升序
            Date currentDate= MyDateUtil.getDateBefore(now,dayNumber-1);//获取dayNumber天前日期
            SimpleDateFormat format= new SimpleDateFormat("MM-dd");
            dates.add(format.format(currentDate));//将日期记录到X轴
            QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
            questionnairequery.setQueryDate(currentDate);//查找当天的表内容
            questionnairequery.setMyIllQuery(true);//生病
            numbers.add(questionnaireDao.querySize(questionnairequery));
            dayNumber--;
        }
        barData.setLabels(dates.toArray(new String[dates.size()]));
        barData.setValues(numbers.toArray(new Integer[numbers.size()]));
        return barData;
    }


    /**
     * 查询最近几天的来自武汉的市外填报人数
     * @param dayNumber
     * @return
     */
    public BaseDateArrayChart getFromWuHanRecently(Integer dayNumber){
        BaseDateArrayChart barData=new BaseDateArrayChart();
        List<String> dates=new ArrayList<String>();//记录日期
        List<Integer> numbers=new ArrayList<Integer>();//记录相应人数
        Date now=new Date();//获取当前时间
        while(dayNumber>=1){//最开始的日期算起，按时间升序
            Date currentDate= MyDateUtil.getDateBefore(now,dayNumber-1);//获取dayNumber天前日期
            SimpleDateFormat format= new SimpleDateFormat("MM-dd");
            dates.add(format.format(currentDate));//将日期记录到X轴
            QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
            questionnairequery.setQueryDate(currentDate);//查找当天的表内容
            questionnairequery.setComefromWuHan(true);//来自武汉
            numbers.add(questionnaireDao.querySize(questionnairequery));
            dayNumber--;
        }
        barData.setLabels(dates.toArray(new String[dates.size()]));
        barData.setValues(numbers.toArray(new Integer[numbers.size()]));
        return barData;
    }

    /**
     * 查询最近几天的来自湖北的市外填报人数
     * @param dayNumber
     * @return
     */
    public BaseDateArrayChart getFromHuBeiRecently(Integer dayNumber){
        BaseDateArrayChart barData=new BaseDateArrayChart();
        List<String> dates=new ArrayList<String>();//记录日期
        List<Integer> numbers=new ArrayList<Integer>();//记录相应人数
        Date now=new Date();//获取当前时间
        while(dayNumber>=1){//最开始的日期算起，按时间升序
            Date currentDate= MyDateUtil.getDateBefore(now,dayNumber-1);//获取dayNumber天前日期
            SimpleDateFormat format= new SimpleDateFormat("MM-dd");
            dates.add(format.format(currentDate));//将日期记录到X轴
            QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
            questionnairequery.setQueryDate(currentDate);//查找当天的表内容
            questionnairequery.setComefromHuBei(true);//来自湖北
            numbers.add(questionnaireDao.querySize(questionnairequery));
            dayNumber--;
        }
        barData.setLabels(dates.toArray(new String[dates.size()]));
        barData.setValues(numbers.toArray(new Integer[numbers.size()]));
        return barData;
    }

    /**
     * 查询最近几天的去过湖北的市外填报人数
     * @param dayNumber
     * @return
     */
    public BaseDateArrayChart getArriveHuBeiRecently(Integer dayNumber){
        BaseDateArrayChart barData=new BaseDateArrayChart();
        List<String> dates=new ArrayList<String>();//记录日期
        List<Integer> numbers=new ArrayList<Integer>();//记录相应人数
        Date now=new Date();//获取当前时间
        while(dayNumber>=1){//最开始的日期算起，按时间升序
            Date currentDate= MyDateUtil.getDateBefore(now,dayNumber-1);//获取dayNumber天前日期
            SimpleDateFormat format= new SimpleDateFormat("MM-dd");
            dates.add(format.format(currentDate));//将日期记录到X轴
            QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
            questionnairequery.setQueryDate(currentDate);//查找当天的表内容
            questionnairequery.setArriveHuBei(true);//来自湖北
            numbers.add(questionnaireDao.querySize(questionnairequery));
            dayNumber--;
        }
        barData.setLabels(dates.toArray(new String[dates.size()]));
        barData.setValues(numbers.toArray(new Integer[numbers.size()]));
        return barData;
    }

    /**
     * 查询最近几天的去过武汉的市外填报人数
     * @param dayNumber
     * @return
     */
    public BaseDateArrayChart getArriveWuHanRecently(Integer dayNumber){
        BaseDateArrayChart barData=new BaseDateArrayChart();
        List<String> dates=new ArrayList<String>();//记录日期
        List<Integer> numbers=new ArrayList<Integer>();//记录相应人数
        Date now=new Date();//获取当前时间
        while(dayNumber>=1){//最开始的日期算起，按时间升序
            Date currentDate= MyDateUtil.getDateBefore(now,dayNumber-1);//获取dayNumber天前日期
            SimpleDateFormat format= new SimpleDateFormat("MM-dd");
            dates.add(format.format(currentDate));//将日期记录到X轴
            QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
            questionnairequery.setQueryDate(currentDate);//查找当天的表内容
            questionnairequery.setArriveWuHan(true);//去过武汉
            numbers.add(questionnaireDao.querySize(questionnairequery));
            dayNumber--;
        }
        barData.setLabels(dates.toArray(new String[dates.size()]));
        barData.setValues(numbers.toArray(new Integer[numbers.size()]));
        return barData;
    }

    /**
     * 查询最近几天的去过广东、浙江、河南、湖南省的市内人员填报人数
     * @param dayNumber
     * @return
     */
    public BaseDateArrayChart getArriveGZHHRecently(Integer dayNumber){
        BaseDateArrayChart barData=new BaseDateArrayChart();
        List<String> dates=new ArrayList<String>();//记录日期
        List<Integer> numbers=new ArrayList<Integer>();//记录相应人数
        Date now=new Date();//获取当前时间
        while(dayNumber>=1){//最开始的日期算起，按时间升序
            Date currentDate= MyDateUtil.getDateBefore(now,dayNumber-1);//获取dayNumber天前日期
            SimpleDateFormat format= new SimpleDateFormat("MM-dd");
            dates.add(format.format(currentDate));//将日期记录到X轴
            QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
            questionnairequery.setQueryDate(currentDate);//查找当天的表内容
            questionnairequery.setArriveGZHH(true);//去过广东、浙江、河南、湖南省
            numbers.add(questionnaireDao.querySize(questionnairequery));
            dayNumber--;
        }
        barData.setLabels(dates.toArray(new String[dates.size()]));
        barData.setValues(numbers.toArray(new Integer[numbers.size()]));
        return barData;
    }

    /**
     * 查询最近几天的从广东、浙江、河南、湖南省回来的市外填报人数
     * @param dayNumber
     * @return
     */
    public BaseDateArrayChart getFromGZHHRecently(Integer dayNumber){
        BaseDateArrayChart barData=new BaseDateArrayChart();
        List<String> dates=new ArrayList<String>();//记录日期
        List<Integer> numbers=new ArrayList<Integer>();//记录相应人数
        Date now=new Date();//获取当前时间
        while(dayNumber>=1){//最开始的日期算起，按时间升序
            Date currentDate= MyDateUtil.getDateBefore(now,dayNumber-1);//获取dayNumber天前日期
            SimpleDateFormat format= new SimpleDateFormat("MM-dd");
            dates.add(format.format(currentDate));//将日期记录到X轴
            QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
            questionnairequery.setQueryDate(currentDate);//查找当天的表内容
            questionnairequery.setComeFromGZHH(true);//来自广东、浙江、河南、湖南省
            numbers.add(questionnaireDao.querySize(questionnairequery));
            dayNumber--;
        }
        barData.setLabels(dates.toArray(new String[dates.size()]));
        barData.setValues(numbers.toArray(new Integer[numbers.size()]));
        return barData;
    }

    /**
     * 查询近期仍停留在武汉人数
     * @param dayNumber
     * @return
     */
    public BaseDateArrayChart getStayHubeiRecently(Integer dayNumber){
        BaseDateArrayChart barData=new BaseDateArrayChart();
        List<String> dates=new ArrayList<String>();//记录日期
        List<Integer> numbers=new ArrayList<Integer>();//记录相应人数
        Date now=new Date();//获取当前时间
        while(dayNumber>=1){//最开始的日期算起，按时间升序
            Date currentDate= MyDateUtil.getDateBefore(now,dayNumber-1);//获取dayNumber天前日期
            SimpleDateFormat format= new SimpleDateFormat("MM-dd");
            dates.add(format.format(currentDate));//将日期记录到X轴
            QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
            questionnairequery.setQueryDate(currentDate);//查找当天的表内容
            questionnairequery.setStayInHubei(true);//去过武汉
            numbers.add(questionnaireDao.querySize(questionnairequery));
            dayNumber--;
        }
        barData.setLabels(dates.toArray(new String[dates.size()]));
        barData.setValues(numbers.toArray(new Integer[numbers.size()]));
        return barData;
    }


    /**
     * 查询近几天接触疫区人数情况
     * @param dayNumber
     * @return  返回数量
     */
    public BaseDateArrayChart getTouchHuBeiPersonRecently(Integer dayNumber){
        BaseDateArrayChart barData=new BaseDateArrayChart();
        List<String> dates=new ArrayList<String>();//记录日期
        List<Integer> numbers=new ArrayList<Integer>();//记录相应人数
        Date now=new Date();//获取当前时间
        while(dayNumber>=1){//最开始的日期算起，按时间升序
            Date currentDate= MyDateUtil.getDateBefore(now,dayNumber-1);//获取dayNumber天前日期
            SimpleDateFormat format= new SimpleDateFormat("MM-dd");
            dates.add(format.format(currentDate));//将日期记录到X轴
            QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
            questionnairequery.setQueryDate(currentDate);//查找当天的表内容
            questionnairequery.setTouchHuBeiPerson(true);//接触过疫区人员
            numbers.add(questionnaireDao.querySize(questionnairequery));
            dayNumber--;
        }
        barData.setLabels(dates.toArray(new String[dates.size()]));
        barData.setValues(numbers.toArray(new Integer[numbers.size()]));
        return barData;
    }

    /**
     * 查询近几天学生在单位上班的人数情况
     * @param dayNumber
     * @return  返回数量
     */
    public BaseDateArrayChart getPracticeWorkStudentRecently(Integer dayNumber){
        BaseDateArrayChart barData=new BaseDateArrayChart();
        List<String> dates=new ArrayList<String>();//记录日期
        List<Integer> numbers=new ArrayList<Integer>();//记录相应人数
        Date now=new Date();//获取当前时间
        while(dayNumber>=1){//最开始的日期算起，按时间升序
            Date currentDate= MyDateUtil.getDateBefore(now,dayNumber-1);//获取dayNumber天前日期
            SimpleDateFormat format= new SimpleDateFormat("MM-dd");
            dates.add(format.format(currentDate));//将日期记录到X轴
            QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
            questionnairequery.setQueryDate(currentDate);//查找当天的表内容
            questionnairequery.setPractice(true);//在实习
            questionnairequery.setPracticeWorkStatus("在单位上班");
            numbers.add(questionnaireDao.querySize(questionnairequery));
            dayNumber--;
        }
        barData.setLabels(dates.toArray(new String[dates.size()]));
        barData.setValues(numbers.toArray(new Integer[numbers.size()]));
        return barData;
    }




}
