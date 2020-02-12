package com.lcvc.new_coronavirus_report.util.poi;

import com.lcvc.new_coronavirus_report.model.DailyReport;
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
        ExcelWirteForFisrtSheet.getShee(book,list);
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
        ExcelWirteForSecondSheet.getShee(book,list);
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
        ExcelWirteForThirdSheet.getShee(book,list);
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
        ExcelWirteForFourthSheet.getShee(book,list);
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
        ExcelWirteForFifthSheet.getShee(book,list);
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
        ExcelWirteForSixthSheet.getShee(book,list);
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
        ExcelWirteForSeventhSheet.getShee(book,list);
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
}