package com.lcvc.new_coronavirus_report.web.action.backstage;

import com.lcvc.new_coronavirus_report.model.Questionnaire;
import com.lcvc.new_coronavirus_report.model.form.DailyReportTable;
import com.lcvc.new_coronavirus_report.model.query.QuestionnaireQuery;
import com.lcvc.new_coronavirus_report.service.DailyReportService;
import com.lcvc.new_coronavirus_report.service.QuestionnaireService;
import com.lcvc.new_coronavirus_report.util.poi.ExcelWirteForSixthSheet;
import com.lcvc.new_coronavirus_report.util.poi.ExcelWirteForTable;
import com.lcvc.new_coronavirus_report.util.poi.ExcelWirteForThirdSheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 专门处理9个表的内容，并将其用excel导出
 * https://blog.csdn.net/shrek11/article/details/88988638
 */
@RestController
@RequestMapping(value = "/api/frontdesk/excel")
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
    @GetMapping("/1-1")
    public String getTable1(HttpServletResponse response){
        DailyReportTable  dailyReportTable=  dailyReportService.getDailyReportInTodayAndYesterDay();
        //导出表格
        XSSFWorkbook book= ExcelWirteForTable.getExcel1(dailyReportTable);//根据记录，生成excel表格
        this.outExcelStream(response,book,"柳州市重点人群排查工作相关表格");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }

    //模板，张峰-看好案例，获取今天的表1-2的内容
    @GetMapping("/1-2")
    public String getTable2(HttpServletRequest request,HttpServletResponse response){
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
//        questionnairequery.setQueryDate(new Date());//查找今天的表内容
//        questionnairequery.setComefromWuHan(true);//查询来自武汉的记录
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        //导出表格
        XSSFWorkbook book= ExcelWirteForTable.getExcel2(list);//根据记录，生成excel表格
        //创建文件对象，导出
        this.outExcelStream(response,book,"来自武汉市的市外人员排查日报表（一）");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }


    //获取今天的表1-3的内容
    @GetMapping("/1-3")
    public String getTable3(Integer page, Integer limit,HttpServletResponse response){
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setComefromHuBei(true);//查询来自湖北的记录
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        //导出表格
        XSSFWorkbook book= ExcelWirteForTable.getExcel3(list);//根据记录，生成excel表格
        //创建文件对象，导出
        this.outExcelStream(response,book,"来自湖北省（除武汉市）的市外人员排查日报表（二）");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }

    //获取今天的表1-4的内容
    @GetMapping("/1-4")
    public String getTable4(Integer page, Integer limit,HttpServletResponse response){
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setArriveWuHan(true);//查询去过武汉的记录
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        //导出表格
        XSSFWorkbook book= ExcelWirteForTable.getExcel4(list);//根据记录，生成excel表格
        //创建文件对象，导出
        this.outExcelStream(response,book,"我市到过武汉市的人员排查日报表（三）");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }

    //获取今天的表1-5的内容
    @GetMapping("/1-5")
    public String getTable5(Integer page, Integer limit,HttpServletResponse response){
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setArriveHuBei(true);//查询去过湖北的记录
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        //导出表格
        XSSFWorkbook book= ExcelWirteForTable.getExcel5(list);//根据记录，生成excel表格
        //创建文件对象，导出
        this.outExcelStream(response,book,"我市到过湖北省（除武汉市）的人员排查日报表（四）");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }

    //获取今天的表1-6的内容
    @GetMapping("/1-6")
    public String getTable6(Integer page, Integer limit,HttpServletResponse response){
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setStayInHubei(true);//查询当前依旧停留在湖北的
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        //导出表格
        XSSFWorkbook book= ExcelWirteForTable.getExcel6(list);//根据记录，生成excel表格
        //创建文件对象，导出
        this.outExcelStream(response,book," 1月16日后我市现在仍在湖北出差、休假、旅游、探亲等短时停留人员(五）");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }

    //获取今天的表1-7的内容
    @GetMapping("/1-7")
    public String getTable7(Integer page, Integer limit,HttpServletResponse response){
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
//        questionnairequery.setQueryDate(new Date());//查找今天的表内容
//        questionnairequery.setTouchHuBeiPerson(true);//查询去过密切接触疫区人员的记录
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        //导出表格
        XSSFWorkbook book= ExcelWirteForTable.getExcel7(list);//根据记录，生成excel表格
        //创建文件对象，导出
        this.outExcelStream(response,book," 密切接触过来自或到达过湖北等疫区人员情况表");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }


    //获取今天的学生表日常记录的内容
    @GetMapping("/student")
    public String getStudentTable(Integer page, Integer limit,HttpServletResponse response){
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setStudentQuery(true);//查询学生信息
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        //导出表格
        XSSFWorkbook book= ExcelWirteForTable.getStudent(list);//根据记录，生成excel表格
        //创建文件对象，导出
        this.outExcelStream(response,book," 学生自觉居家观察健康状况表");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }

    //获取今天的教师表日常记录的内容
    @GetMapping("/teacher")
    public String getTeacherTable(Integer page, Integer limit,HttpServletResponse response){
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setTeacherQuery(true);//查询教师信息
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        //导出表格
        XSSFWorkbook book= ExcelWirteForTable.getTeacher(list);//根据记录，生成excel表格
        //创建文件对象，导出
        this.outExcelStream(response,book," 教职工自觉居家观察健康状况表");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }

    //获取今天的学生在公司上班的记录的内容：表名：实习学生当前在单位上岗情况表
    @GetMapping("/practiceWork")
    public String getFromGZHHShengTable(Integer page, Integer limit,HttpServletResponse response){
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
        questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setPractice(true);//正在实习
        questionnairequery.setPracticeWorkStatus("在单位上班");//统计在上班的
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        //导出表格
        //根据记录，生成excel表格
        //创建文件对象，导出
        //this.outExcelStream(response,book," 教职工自觉居家观察健康状况表");
        return "SUCCESS";//这里其实就是随意返回一个字符串
    }

}
