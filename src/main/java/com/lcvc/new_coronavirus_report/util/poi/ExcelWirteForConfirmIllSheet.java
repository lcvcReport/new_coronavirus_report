package com.lcvc.new_coronavirus_report.util.poi;

import com.lcvc.new_coronavirus_report.model.Questionnaire;
import com.lcvc.new_coronavirus_report.model.form.DailyReportTable;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelWirteForConfirmIllSheet {

    /**
     * 可疑、疑似或确诊个体病例明细情况表
     * @return
     */
    public static XSSFSheet getShee(XSSFWorkbook book,  List<Questionnaire> list){
        String header[]={"可疑、疑似或确诊个体病例明细情况表"};
        String header1[]={"填报单位：                         填报人：                          联系电话：                         填报时间：        年      月       日"};
        String title[]={
                "可疑症状人员只需填写白色部分，疑似或确诊人员填完所有项目",
                "疑似或确诊病例明细情况表疑似或确诊病例需有医院相关证明）",
                "备注"
        };
        String title1[]={
                "序号",
                "姓名",
                "学校/单位名称",
                "所在年级班级（专业）",
                "学生/教职工",
                "身份证号码",
                "联系电话",
                "目前在居住地（详细到户号）",
                "目前是否有发热、咳嗽、呼吸困难等可疑症状（请备注出现的症状、症状出现的时间等信息）",
                "是否就医及医疗机构名称（填“否”或机构名称）",
                "是否为新型冠状病毒感染的肺炎疑似病例(填“否”或在列“M”至“S”注明具体情况）",
                "是否确诊及具体情况(填“否”或在列“M”至“S”注明具体情况）",
                "可疑症状消失时间",
                "是否发烧",
                "体温（℃）",
                "是否接触过湖北等疫区人员",
                "接触过湖北等疫区人员的姓名",
                "买药用途（自用或为他人购买，如为他人购买请填写情况并进一步核查）",
                "类别（确诊/疑似）",
                "教育类别",
                "性别",
                "年龄",
                "就诊医院或隔离地",
                "确诊/诊断日期",
                "病情分类",
                "治疗情况",
                "病程",
        };


        // 创建工作表
        XSSFSheet sheet = book.createSheet("Sheet1");

        // 设置单元格表单头部样式
        XSSFCellStyle headerStyle = book.createCellStyle();
        XSSFFont headerFont = book.createFont();
        headerFont.setFontHeightInPoints((short) 16);
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER); //
        headerStyle.setWrapText(true);//自动换行


        // 设置单元格表单标题样式
        XSSFCellStyle titleStyle = book.createCellStyle();
        XSSFFont titlefont = book.createFont();
        titlefont.setFontHeightInPoints((short) 12);
        titleStyle.setFont(titlefont);
        titleStyle.setBorderBottom(BorderStyle.THIN);
        titleStyle.setBorderTop(BorderStyle.THIN);
        titleStyle.setBorderLeft(BorderStyle.THIN);
        titleStyle.setBorderRight(BorderStyle.THIN);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER); //
        titleStyle.setWrapText(true);//自动换行

        //红色字体
        XSSFCellStyle tredFontStyle = book.createCellStyle();
        XSSFFont redFont = book.createFont();
        redFont.setFontHeightInPoints((short) 12);
        redFont.setColor(XSSFFont.COLOR_RED);
        tredFontStyle.setFont(redFont);
        tredFontStyle.setBorderBottom(BorderStyle.THIN);
        tredFontStyle.setBorderTop(BorderStyle.THIN);
        tredFontStyle.setBorderLeft(BorderStyle.THIN);
        tredFontStyle.setBorderRight(BorderStyle.THIN);
        tredFontStyle.setAlignment(HorizontalAlignment.CENTER);
        tredFontStyle.setVerticalAlignment(VerticalAlignment.CENTER); //
        tredFontStyle.setWrapText(true);//自动换行

        //设置行
        //header
        XSSFRow headerRow = sheet.createRow(0);
        headerRow.setHeightInPoints(30);//设置行的高度是50个点
        XSSFRow headerRow1 = sheet.createRow(1);
        headerRow1.setHeightInPoints(30);//设置行的高度是50个点
        //titie
        XSSFRow titleRow = sheet.createRow(2);
        titleRow.setHeightInPoints(30);//设置行的高度是50个点
        XSSFRow titleRow1 = sheet.createRow(3);
        titleRow1.setHeightInPoints(30);//设置行的高度是50个点
        XSSFRow titleRow2 = sheet.createRow(4);
        titleRow2.setHeightInPoints(150);//设置行的高度是50个点


        //跨行跨列
        //header行
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, title1.length-1);
        CellRangeAddress region1= new CellRangeAddress(1, 1, 0, title1.length-1);
        //title
        CellRangeAddress region2= new CellRangeAddress(2, 2, 0, 17);
        CellRangeAddress region3= new CellRangeAddress(2, 2, 18, title1.length-2);
        //又发热情况
        CellRangeAddress region33= new CellRangeAddress(3, 3, 13, 17);
        //title1
        CellRangeAddress region4= new CellRangeAddress(3, 4, 0, 0);
        CellRangeAddress region5= new CellRangeAddress(3, 4, 1, 1);
        CellRangeAddress region6= new CellRangeAddress(3, 4, 2, 2);
        CellRangeAddress region7= new CellRangeAddress(3, 4, 3, 3);
        CellRangeAddress region8= new CellRangeAddress(3, 4, 4, 4);
        CellRangeAddress region9= new CellRangeAddress(3, 4, 5, 5);
        CellRangeAddress region10= new CellRangeAddress(3, 4, 6, 6);
        CellRangeAddress region11= new CellRangeAddress(3, 4, 7, 7);
        CellRangeAddress region12= new CellRangeAddress(3, 4, 8, 8);
        CellRangeAddress region13= new CellRangeAddress(3, 4, 9, 9);
        CellRangeAddress region14= new CellRangeAddress(3, 4, 10, 10);
        CellRangeAddress region15= new CellRangeAddress(3, 4, 11, 11);
        CellRangeAddress region16= new CellRangeAddress(3, 4, 12, 12);
        //备注
        CellRangeAddress region17= new CellRangeAddress(2, 4, title1.length-1, title1.length-1);


        sheet.addMergedRegion(region);
        sheet.addMergedRegion(region1);
        sheet.addMergedRegion(region2);
        sheet.addMergedRegion(region3);
        sheet.addMergedRegion(region33);
        sheet.addMergedRegion(region4);
        sheet.addMergedRegion(region5);
        sheet.addMergedRegion(region6);
        sheet.addMergedRegion(region7);
        sheet.addMergedRegion(region8);
        sheet.addMergedRegion(region9);
        sheet.addMergedRegion(region10);
        sheet.addMergedRegion(region11);
        sheet.addMergedRegion(region12);
        sheet.addMergedRegion(region13);
        sheet.addMergedRegion(region14);
        sheet.addMergedRegion(region15);
        sheet.addMergedRegion(region16);
        sheet.addMergedRegion(region17);


        //添加表头栏
        for (int i = 0; i <title1.length ; i++) {
            XSSFCell headerCell= headerRow.createCell(i);
            XSSFCell headerCelll= headerRow1.createCell(i);
            sheet.setColumnWidth(i, 10 * 256);//设置第i列的宽度是31个字符宽度
            headerCell.setCellStyle(headerStyle);
            headerCelll.setCellStyle(titleStyle);
            if (i==0){
                headerCell.setCellValue(header[0]);
                headerCelll.setCellValue(header1[0]);
            }
        }

        //标题1
        for (int i = 0; i <title1.length ; i++) {
            XSSFCell titleCell= titleRow.createCell(i);
            titleCell.setCellStyle(titleStyle);
            XSSFCell titleCelll= titleRow1.createCell(i);
            titleCelll.setCellStyle(titleStyle);
            XSSFCell titleCell2= titleRow2.createCell(i);
            titleCell2.setCellStyle(titleStyle);
            //title行
            if (i==0){
                titleCell.setCellValue(title[0]);
                titleCell.setCellStyle(tredFontStyle);
            }
            if (i==18){
                titleCell.setCellValue(title[1]);
            }
            //备注
            if (i==title1.length-1){
                titleCell.setCellValue(title[2]);
            }
            //title1行
            //有发热情况
            if(i==13){
                titleCelll.setCellValue("有发热情况填写");
                titleCelll.setCellStyle(tredFontStyle);
            }
            if (i<13){
                titleCelll.setCellValue(title1[i]);
            }
            if (i>=13&&i<title1.length){
                titleCell2.setCellValue(title1[i]);
            }



        }

        return sheet;
    }
}
