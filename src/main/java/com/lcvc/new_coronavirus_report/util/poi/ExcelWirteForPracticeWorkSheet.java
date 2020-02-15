package com.lcvc.new_coronavirus_report.util.poi;


import com.lcvc.new_coronavirus_report.model.Questionnaire;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExcelWirteForPracticeWorkSheet {

    /**
     *
     *实习学生当前在单位上岗情况表
     * @param book 传递进来的工作部对象
     * @param list 要遍历的数据集合
     * @return
     */
    public static XSSFSheet getShee(XSSFWorkbook book, List<Questionnaire> list) {
        String header[] = {"  实习学生当前在单位上岗情况表"};
         String title[] = {"学生姓名", "性别", "专业班级", "电话号码", "是否实习", "当前实习情况"};

        // 创建一个工作表
        XSSFSheet sheet = book.createSheet("实习学生当前在单位上岗情况表");

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

        //设置行
        //header
        XSSFRow headerRow = sheet.createRow(0);
        headerRow.setHeightInPoints(35);//设置行的高度是50个点
        //titie
        XSSFRow titleRow = sheet.createRow(1);
        titleRow.setHeightInPoints(30);//设置行的高度是50个点


        //跨行跨列
        //header行
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, title.length - 1);
        sheet.addMergedRegion(region);

        //添加表头栏
        for (int i = 0; i < title.length; i++) {
            XSSFCell headerCell = headerRow.createCell(i);
            sheet.setColumnWidth(i, 15 * 256);//设置第i列的宽度是31个字符宽度
            headerCell.setCellStyle(headerStyle);
            if (i == 0) {
                headerCell.setCellValue(header[0]);
            }
        }

        //title
        for (int i = 0; i < title.length; i++) {
            XSSFCell titleCell = titleRow.createCell(i);
            sheet.setColumnWidth(i, 15 * 256);//设置第i列的宽度是31个字符宽度
            titleCell.setCellValue(title[i]);
            titleCell.setCellStyle(titleStyle);
        }

        //list数据
        for (int i = 0; i < list.size(); i++) {
            XSSFRow listRow = sheet.createRow(i + 2);

            XSSFCell name = listRow.createCell(0);
            name.setCellValue(list.get(i).getName());
            name.setCellStyle(titleStyle);

            XSSFCell sex = listRow.createCell(1);
            sex.setCellValue(list.get(i).getSex());
            sex.setCellStyle(titleStyle);

            XSSFCell schoolClass = listRow.createCell(2);
            schoolClass.setCellValue(list.get(i).getSchoolClass());
            schoolClass.setCellStyle(titleStyle);

            XSSFCell tel = listRow.createCell(3);
            tel.setCellValue(list.get(i).getTel());
            tel.setCellStyle(titleStyle);

            XSSFCell practice = listRow.createCell(4);
            practice.setCellStyle(titleStyle);
            if (list.get(i).getPractice()!=null){
                if (list.get(i).getPractice()){
                    practice.setCellValue("是");
                }else{
                    practice.setCellValue("否");
                }
            }

            XSSFCell practiceWorkStatus = listRow.createCell(5);
            practiceWorkStatus.setCellStyle(titleStyle);
            practiceWorkStatus.setCellValue(list.get(i).getPracticeWorkStatus());

        }


        return sheet;
    }
}
