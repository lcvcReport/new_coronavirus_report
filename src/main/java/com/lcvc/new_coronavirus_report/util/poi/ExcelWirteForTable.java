package com.lcvc.new_coronavirus_report.util.poi;

import com.lcvc.new_coronavirus_report.model.Questionnaire;
import com.lcvc.new_coronavirus_report.model.form.DailyReportTable;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 负责处理所有要导出的表格
 */
@Service
public class ExcelWirteForTable {

    /**
     * 1.1重点人群排查管理日报表
     * @param list 要遍历的数据集合
     */
    public static XSSFWorkbook getDailyReportSheet(DailyReportTable list) {
        // 创建工作簿
        XSSFWorkbook book = new XSSFWorkbook();
        // 创建工作表1
        ExcelWirteForDailyReportSheet.getShee(book,list);
        return book;
    }

    /**
     * 1.2来自武汉市的市外人员排查日报表（一）
     * @param list 要遍历的数据集合
     */
    public static XSSFWorkbook getComeFromWuHanSheet(List<Questionnaire> list) {
        // 创建工作簿
        XSSFWorkbook book = new XSSFWorkbook();
        // 创建工作表1
        ExcelWirteForComeFromWuHanSheet.getShee(book,list);
        return book;
    }

    /**
     * 1.3来自湖北省（除武汉市）的市外人员排查日报表（二）
     * @param list 要遍历的数据集合
     */
    public static XSSFWorkbook getComeFromHuBeiSheet(List<Questionnaire> list) {
        // 创建工作簿
        XSSFWorkbook book = new XSSFWorkbook();
        // 创建工作表1
        ExcelWirteForComeFromHuBeiSheet.getShee(book,list);
        return book;
    }
    /**
     * 1.4我市到过武汉市的人员排查日报表（三）
     * @param list 要遍历的数据集合
     */
    public static XSSFWorkbook getArriveWuHanSheet(List<Questionnaire> list) {
        // 创建工作簿
        XSSFWorkbook book = new XSSFWorkbook();
        // 创建工作表1
        ExcelWirteForArriveWuHanSheet.getShee(book,list);
        return book;
    }
    /**
     * 1.5我市到过湖北省（除武汉市）的人员排查日报表（四）
     * @param list 要遍历的数据集合
     */
    public static XSSFWorkbook getArriveHuBeiSheet(List<Questionnaire> list) {
        // 创建工作簿
        XSSFWorkbook book = new XSSFWorkbook();
        // 创建工作表1
        ExcelWirteForArriveHuBeiSheet.getShee(book,list);
        return book;
    }
    /**
     * 1.6
     * 1月16日后我市现在仍在湖北出差、休假、旅游、探亲等短时停留人员(五）
     * @param list 要遍历的数据集合
     */
    public static XSSFWorkbook getStayHuBeiSheet(List<Questionnaire> list) {
        // 创建工作簿
        XSSFWorkbook book = new XSSFWorkbook();
        // 创建工作表
        ExcelWirteForStayHuBeiSheet.getShee(book,list);
        return book;
    }
    /**
     * 1.7   密切接触过来自或到达过湖北等疫区人员情况表
     * @param list 要遍历的数据集合
     */
    public static XSSFWorkbook getTouchHuBeiSheet(List<Questionnaire> list) {
        // 创建工作簿
        XSSFWorkbook book = new XSSFWorkbook();
        // 创建工作表
        ExcelWirteForTouchHuBeiSheet.getShee(book,list);
        return book;
    }
    /**
     * 学生自觉居家观察健康状况表
     * @param list 要遍历的数据集合
     */
    public static XSSFWorkbook getStudentSheet(List<Questionnaire> list) {
        // 创建工作簿
        XSSFWorkbook book = new XSSFWorkbook();
        // 创建工作表
        ExcelWirteForStudentSheet.getShee(book,list);
        return book;
    }
    /**
     * 教职工自觉居家观察健康状况表
     * @param list 要遍历的数据集合
     */
    public static XSSFWorkbook getTeacherSheet(List<Questionnaire> list) {
        // 创建工作簿
        XSSFWorkbook book = new XSSFWorkbook();
        // 创建工作表
        ExcelWirteForTeacherSheet.getShee(book,list);
        return book;
    }
    /**
     * 来自广东、浙江、河南、湖南省的市外人员排查日报表（六）
     * @param list 要遍历的数据集合
     */
    public static XSSFWorkbook getComeFromGZHHSheet(List<Questionnaire> list) {
        // 创建工作簿
        XSSFWorkbook book = new XSSFWorkbook();
        // 创建工作表
        ExcelWirteForComeFromGZHHSheet.getShee(book,list);
        return book;
    }

    /**
     * 我市到过广东、浙江、河南、湖南省的人员排查日报表（七）
     * @param list 要遍历的数据集合
     */
    public static XSSFWorkbook getArriveGZHHSheet(List<Questionnaire> list) {
        // 创建工作簿
        XSSFWorkbook book = new XSSFWorkbook();
        // 创建工作表
        ExcelWirteForArriveGZHHSheet.getShee(book,list);
        return book;
    }
    /**
     * 获取今天的学生在公司上班的记录的内容：表名：实习学生当前在单位上岗情况表
     * @param list 要遍历的数据集合
     */
    public static XSSFWorkbook getPracticeWork(List<Questionnaire> list) {
        // 创建工作簿
        XSSFWorkbook book = new XSSFWorkbook();
        // 创建工作表
        ExcelWirteForPracticeWorkSheet.getShee(book,list);
        return book;
    }

    /**
     * 其他发热人员情况登记表
     * @param list 要遍历的数据集合
     */
    public static XSSFWorkbook getForFeverPeople(List<Questionnaire> list) {
        // 创建工作簿
        XSSFWorkbook book = new XSSFWorkbook();
        // 创建工作表
        ExcelWirteForFeverPeopleSheet.getShee(book,list);
        return book;
    }

    /**
     * 获取导出表一到密切接触者表的全部数据  表名：柳州市重点人群排查工作相关表格
     */
    public static XSSFWorkbook getAllSheet(DailyReportTable dailyReportTable,
                                           List<Questionnaire> list2,
                                           List<Questionnaire> list3,
                                           List<Questionnaire> list4,
                                           List<Questionnaire> list5,
                                           List<Questionnaire> list6,
                                           List<Questionnaire> list7,
                                           List<Questionnaire> list8,
                                           List<Questionnaire> touchlist){
        // 创建工作簿
        XSSFWorkbook book = new XSSFWorkbook();
        //表一 重点人群排查管理日报表
        ExcelWirteForDailyReportSheet.getShee(book,dailyReportTable);
        //表二 来自武汉市的市外人员排查日报表（一）
        ExcelWirteForComeFromWuHanSheet.getShee(book,list2);
        //表三  来自湖北省（除武汉市）的市外人员排查日报表（二）
        ExcelWirteForComeFromHuBeiSheet.getShee(book,list3);
        //表四  我市到过武汉市的人员排查日报表（三）
        ExcelWirteForArriveWuHanSheet.getShee(book,list4);
        //表五  我市到过湖北省（除武汉市）的人员排查日报表（四）
        ExcelWirteForArriveHuBeiSheet.getShee(book,list5);
        //表六  我市现在仍在湖北出差、休假、旅游、探亲等短时停留人员(五）
        ExcelWirteForStayHuBeiSheet.getShee(book,list6);
        //表七  来自广东、浙江、河南、湖南省的市外人员排查日报表（六）
        ExcelWirteForComeFromGZHHSheet.getShee(book,list7);
        //表八  我市到过广东、浙江、河南、湖南省的人员排查日报表（七）
        ExcelWirteForArriveGZHHSheet.getShee(book,list8);
        //密切接触着  密切接触过来自或到达过湖北等疫区人员情况表
        ExcelWirteForTouchHuBeiSheet.getShee(book,touchlist);
        return book;
    }

    /**
     * 居家隔离观察健康状况表
     * @param StudentList 要遍历的数据集合
     * @param teacherList 要遍历的数据集合
     */
    public static XSSFWorkbook getStudentTeacherSheet(List<Questionnaire> StudentList,List<Questionnaire> teacherList) {
        // 创建工作簿
        XSSFWorkbook book = new XSSFWorkbook();
        // 创建工作表
        //学生表
        ExcelWirteForStudentSheet.getShee(book,StudentList);
        //教师表
        ExcelWirteForTeacherSheet.getShee(book,teacherList);
        return book;
    }

    /**
     * 可疑、疑似或确诊个体病例明细情况表
     */
    public static XSSFWorkbook getConfirmIllSheet(List<Questionnaire> list) {
        // 创建工作簿
        XSSFWorkbook book = new XSSFWorkbook();
        // 创建工作表
        //学生表
        ExcelWirteForConfirmIllSheet.getShee(book,list);
        return book;
    }
}
