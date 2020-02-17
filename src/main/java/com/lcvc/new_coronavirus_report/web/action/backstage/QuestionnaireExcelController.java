package com.lcvc.new_coronavirus_report.web.action.backstage;

import com.lcvc.new_coronavirus_report.model.Questionnaire;
import com.lcvc.new_coronavirus_report.model.base.Constant;
import com.lcvc.new_coronavirus_report.model.base.JsonCode;
import com.lcvc.new_coronavirus_report.model.form.DailyReportTable;
import com.lcvc.new_coronavirus_report.model.query.QuestionnaireQuery;
import com.lcvc.new_coronavirus_report.service.DailyReportService;
import com.lcvc.new_coronavirus_report.service.QuestionnaireService;
import com.lcvc.new_coronavirus_report.util.poi.ExcelWirteForTable;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * 专门处理9个表的内容，并将其用excel导出
 * https://blog.csdn.net/shrek11/article/details/88988638
 */
@RestController
@RequestMapping(value = "/api/backstage/excel")
public class QuestionnaireExcelController {

    @Autowired
    private QuestionnaireService questionnaireService;
    @Autowired
    private DailyReportService dailyReportService;



    /**
     * 通过流的方式输出excle到页面，每个文件都要下载
     * @param response 响应
     * @param workbook 表对象
     * @param fileName 文件名，下载时显示的文件名
     */
    private void outExcelStream(HttpServletResponse response, Workbook workbook, String fileName){
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            response.setContentType("application/x-download");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xlsx");
            workbook.write(os);
            os.flush();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //获取今天的表1-1的内容
    @GetMapping("/dailyReport")
    public String getTable1(HttpServletResponse response){
        DailyReportTable  dailyReportTable=  dailyReportService.getDailyReportInTodayAndYesterDay();
        //导出表格
        XSSFWorkbook book= ExcelWirteForTable.getDailyReportSheet(dailyReportTable);//根据记录，生成excel表格
        this.outExcelStream(response,book,"柳州市重点人群排查工作相关表格");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }

    //模板，张峰-看好案例，获取今天的表1-2的内容
    @GetMapping("/comeFromWuHan")
    public String getTable2(HttpServletResponse response,String department){
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setComefromWuHan(true);//查询来自武汉的记录
        questionnairequery.setDepartment(department);//加入部门条件
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        //导出表格
        XSSFWorkbook book= ExcelWirteForTable.getComeFromWuHanSheet(list);//根据记录，生成excel表格
        //创建文件对象，导出
        this.outExcelStream(response,book,"来自武汉市的市外人员排查日报表（一）");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }


    //获取今天的表1-3的内容
    @GetMapping("/comeFromHuBei")
    public String getTable3(HttpServletResponse response){
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setComefromHuBei(true);//查询来自湖北的记录
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        //导出表格
        XSSFWorkbook book= ExcelWirteForTable.getComeFromHuBeiSheet(list);//根据记录，生成excel表格
        //创建文件对象，导出
        this.outExcelStream(response,book,"来自湖北省（除武汉市）的市外人员排查日报表（二）");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }

    //获取今天的表1-4的内容
    @GetMapping("/arriveWuHan")
    public String getTable4(HttpServletResponse response){
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setArriveWuHan(true);//查询去过武汉的记录
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        //导出表格
        XSSFWorkbook book= ExcelWirteForTable.getArriveWuHanSheet(list);//根据记录，生成excel表格
        //创建文件对象，导出
        this.outExcelStream(response,book,"我市到过武汉市的人员排查日报表（三）");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }

    //获取今天的表1-5的内容
    @GetMapping("/arriveHuBei")
    public String getTable5(HttpServletResponse response){
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setArriveHuBei(true);//查询去过湖北的记录
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        //导出表格
        XSSFWorkbook book= ExcelWirteForTable.getArriveHuBeiSheet(list);//根据记录，生成excel表格
        //创建文件对象，导出
        this.outExcelStream(response,book,"我市到过湖北省（除武汉市）的人员排查日报表（四）");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }

    //获取今天的表1-6的内容
    @GetMapping("/stayHuBei")
    public String getTable6(HttpServletResponse response){
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setStayInHubei(true);//查询当前依旧停留在湖北的
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        //导出表格
        XSSFWorkbook book= ExcelWirteForTable.getStayHuBeiSheet(list);//根据记录，生成excel表格
        //创建文件对象，导出
        this.outExcelStream(response,book," 我市现在仍在湖北（含武汉市）、广东、浙江、河南、湖南出差、休假、旅游、探亲等短时停留的师生员工(六)");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }

    //获取今天的表1-7的内容
    @GetMapping("/touchHuBei")
    public String getTable7(HttpServletResponse response){
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setTouchHuBeiPerson(true);//查询去过密切接触疫区人员的记录
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        //导出表格
        XSSFWorkbook book= ExcelWirteForTable.getTouchHuBeiSheet(list);//根据记录，生成excel表格
        //创建文件对象，导出
        this.outExcelStream(response,book," 密切接触过来自或到达过湖北等疫区人员情况表");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }

    //发热记录
    @GetMapping("/fever")
    public String fever(HttpServletResponse response){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put(Constant.JSON_CODE, JsonCode.SUCCESS.getValue());
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setFeverQuery(true);//查询发热病人的记录
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        //导出表格
        XSSFWorkbook book= ExcelWirteForTable.getForFeverPeople(list);//根据记录，生成excel表格
        //创建文件对象，导出
        this.outExcelStream(response,book," 其他发热人员情况登记表");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }

    //来自广东、浙江、河南、湖南省的市外人员排查日报表
    @GetMapping("/comeFromGZHH")
    public String comeFromGZHH(HttpServletResponse response){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put(Constant.JSON_CODE, JsonCode.SUCCESS.getValue());
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setComeFromGZHH(true);//查询来自广东、浙江、河南、湖南省的市外人员
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        //创建文件对象，导出
        XSSFWorkbook book= ExcelWirteForTable.getComeFromGZHHSheet(list);//根据记录，生成excel表格
        //创建文件对象，导出
        this.outExcelStream(response,book," 来自广东、浙江、河南、湖南省的市外人员排查日报表");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }

    //去过广东、浙江、河南、湖南省的市外人员排查日报表
    @GetMapping("/arriveGZHH")
    public String arriveGZHH(HttpServletResponse response){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put(Constant.JSON_CODE, JsonCode.SUCCESS.getValue());
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setArriveGZHH(true);//查询去过广东、浙江、河南、湖南省的市外人员
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        //创建文件对象，导出
        XSSFWorkbook book= ExcelWirteForTable.getArriveGZHHSheet(list);//根据记录，生成excel表格
        //创建文件对象，导出
        this.outExcelStream(response,book,"去过广东、浙江、河南、湖南省的市外人员排查日报表");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }


    //获取今天的学生表日常记录的内容
    @GetMapping("/student")
    public String getStudentTable(HttpServletResponse response){
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setStudentQuery(true);//查询学生信息
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        //导出表格
        XSSFWorkbook book= ExcelWirteForTable.getStudentSheet(list);//根据记录，生成excel表格
        //创建文件对象，导出
        this.outExcelStream(response,book," 学生自觉居家观察健康状况表");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }

    //获取今天的教师表日常记录的内容
    @GetMapping("/teacher")
    public String getTeacherTable(HttpServletResponse response){
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setTeacherQuery(true);//查询教师信息
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        //导出表格
        XSSFWorkbook book= ExcelWirteForTable.getTeacherSheet(list);//根据记录，生成excel表格
        //创建文件对象，导出
        this.outExcelStream(response,book," 教职工自觉居家观察健康状况表");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }

    //可疑、疑似或确诊个体病例明细情况表
    @GetMapping("/confirmIll")
    public String getConfirmIllTable(HttpServletResponse response){
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setTeacherQuery(true);//查询教师信息
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        //导出表格
        XSSFWorkbook book= ExcelWirteForTable.getConfirmIllSheet(list);//根据记录，生成excel表格
        //创建文件对象，导出
        this.outExcelStream(response,book," 可疑、疑似或确诊个体病例明细情况表11");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }

    //获取今天的学生在公司上班的记录的内容：表名：实习学生当前在单位上岗情况表
    @GetMapping("/practiceWork")
    public String getFromGZHHShengTable(HttpServletResponse response){
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setPractice(true);//正在实习
        questionnairequery.setPracticeWorkStatus("在单位上班");//统计在上班的
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        //导出表格
        XSSFWorkbook book= ExcelWirteForTable.getPracticeWork(list);//根据记录，生成excel表格
        //创建文件对象，导出
        this.outExcelStream(response,book," 实习学生当前在单位上岗情况表");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }
    @GetMapping("/getAll")
    public String getAllTable(HttpServletResponse response){
        //获取今天的表1-1的内容
        DailyReportTable  dailyReportTable=  dailyReportService.getDailyReportInTodayAndYesterDay();
        //获取今天的表1-2的内容
        QuestionnaireQuery questionnairequery2=new QuestionnaireQuery();
        questionnairequery2.setQueryDate(new Date());//查找今天的表内容
        questionnairequery2.setComefromWuHan(true);//查询来自武汉的记录
        List<Questionnaire> list2=questionnaireService.query(questionnairequery2);//获取数据记录
        //获取今天表1-3的内容
        QuestionnaireQuery questionnairequery3=new QuestionnaireQuery();
        questionnairequery3.setQueryDate(new Date());//查找今天的表内容
        questionnairequery3.setComefromHuBei(true);//查询来自湖北的记录
        List<Questionnaire> list3=questionnaireService.query(questionnairequery3);//获取数据记录
        //获取表4
        QuestionnaireQuery questionnairequery4=new QuestionnaireQuery();
        questionnairequery4.setQueryDate(new Date());//查找今天的表内容
        questionnairequery4.setArriveWuHan(true);//查询去过武汉的记录
        List<Questionnaire> list4=questionnaireService.query(questionnairequery4);//获取数据记录
        //获取表5
        QuestionnaireQuery questionnairequery5=new QuestionnaireQuery();
        questionnairequery5.setQueryDate(new Date());//查找今天的表内容
        questionnairequery5.setArriveHuBei(true);//查询去过湖北的记录
        List<Questionnaire> list5=questionnaireService.query(questionnairequery5);//获取数据记录
        //获取表六
        QuestionnaireQuery questionnairequery6=new QuestionnaireQuery();
        questionnairequery6.setQueryDate(new Date());//查找今天的表内容
        questionnairequery6.setStayInHubei(true);//查询当前依旧停留在湖北的
        List<Questionnaire> list6=questionnaireService.query(questionnairequery6);//获取数据记录
        //获取表7
        QuestionnaireQuery questionnairequery7=new QuestionnaireQuery();
        questionnairequery7.setQueryDate(new Date());//查找今天的表内容
        questionnairequery7.setComeFromGZHH(true);//查询来自广东、浙江、河南、湖南省的市外人员
        List<Questionnaire> list7=questionnaireService.query(questionnairequery7);//获取数据记录
        //获取表8
        QuestionnaireQuery questionnairequery8=new QuestionnaireQuery();
        questionnairequery8.setQueryDate(new Date());//查找今天的表内容
        questionnairequery8.setArriveGZHH(true);//查询去过广东、浙江、河南、湖南省的市外人员
        List<Questionnaire> list8=questionnaireService.query(questionnairequery8);//获取数据记录
        //获取密切接触者表
        QuestionnaireQuery questionnairequerytouch=new QuestionnaireQuery();
        questionnairequerytouch.setQueryDate(new Date());//查找今天的表内容
        questionnairequerytouch.setTouchHuBeiPerson(true);//查询去过密切接触疫区人员的记录
        List<Questionnaire> touchlist=questionnaireService.query(questionnairequerytouch);//获取数据记录
        //发热人群数据
        QuestionnaireQuery questionnairequeryfever=new QuestionnaireQuery();
        questionnairequeryfever.setQueryDate(new Date());//查找今天的表内容
        questionnairequeryfever.setFeverQuery(true);//查询发热病人的记录
        List<Questionnaire> feverlist=questionnaireService.query(questionnairequeryfever);//获取数据记录
        Workbook book= ExcelWirteForTable.getAllSheet(dailyReportTable, list2, list3, list4,list5,list6,list7,list8,touchlist,feverlist);//根据记录，生成excel表格
       //创建文件对象，导出
        this.outExcelStream(response,book,"柳州市重点人群排查工作相关表格");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }
    //居家隔离观察健康状况表
    @GetMapping("/studentTeacher")
    public String getStudentTeacherTable(HttpServletResponse response){
        //teacher
        QuestionnaireQuery questionnairequeryteacher=new QuestionnaireQuery();
        questionnairequeryteacher.setQueryDate(new Date());//查找今天的表内容
        questionnairequeryteacher.setTeacherQuery(true);//查询教师信息
        List<Questionnaire> teacherlist=questionnaireService.query(questionnairequeryteacher);//获取数据记录
        //student
        QuestionnaireQuery questionnairequerystudent=new QuestionnaireQuery();
        questionnairequerystudent.setQueryDate(new Date());//查找今天的表内容
        questionnairequerystudent.setStudentQuery(true);//查询学生信息
        List<Questionnaire> studentlist=questionnaireService.query(questionnairequerystudent);//获取数据记录

        XSSFWorkbook book= ExcelWirteForTable.getStudentTeacherSheet(studentlist,teacherlist);//根据记录，生成excel表格
        //创建文件对象，导出
        this.outExcelStream(response,book," 居家隔离观察健康状况表");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }
}
