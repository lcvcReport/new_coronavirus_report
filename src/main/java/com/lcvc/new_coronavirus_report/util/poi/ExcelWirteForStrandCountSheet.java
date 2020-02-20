package com.lcvc.new_coronavirus_report.util.poi;


import com.lcvc.new_coronavirus_report.model.StrandCount;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;



@Service
public class ExcelWirteForStrandCountSheet {

    /**
     * 滞留疫区无法返回教职工情况统计
     *
     * @param book                     传递进来的工作部对象
     * @param strandCount 要遍历的数据集合
     * @return
     */
    public static XSSFSheet getShee(XSSFWorkbook book, StrandCount strandCount) {
        String header[] = {"滞留疫区无法返回教职工情况统计"};
        String header1[] = {"单位：                                                     填表人：                                                   联系电话：                                         填表时间：                           "};
        String title[] = {
                "序号",
                "单位",
                "学段",
                "教职工人数",
                "滞留疫区无法返回教职工人数",
                "本周内预计返柳人数",
                "备注"
        };

        // 创建一个工作表
        XSSFSheet sheet = book.createSheet("附件1");

        // 设置单元格表单头部样式
        CellStyle headerStyle = book.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        XSSFFont headerFont = book.createFont();
        headerFont.setFontHeightInPoints((short) 16);
        headerStyle.setFont(headerFont);

        // 设置单元格表单标题样式
        XSSFCellStyle titleStyle = book.createCellStyle();
        XSSFFont font1 = book.createFont();
        font1.setFontHeightInPoints((short) 12);
        titleStyle.setFont(font1);
        titleStyle.setBorderBottom(BorderStyle.THIN);
        titleStyle.setBorderTop(BorderStyle.THIN);
        titleStyle.setBorderLeft(BorderStyle.THIN);
        titleStyle.setBorderRight(BorderStyle.THIN);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER); //
        titleStyle.setWrapText(true);//自动换行

        // 设置数据样式
        XSSFCellStyle listStyle = book.createCellStyle();
        XSSFFont listFont = book.createFont();
        listFont.setFontHeightInPoints((short) 12);
        listStyle.setFont(listFont);
        listStyle.setBorderBottom(BorderStyle.THIN);
        listStyle.setBorderTop(BorderStyle.THIN);
        listStyle.setBorderLeft(BorderStyle.THIN);
        listStyle.setBorderRight(BorderStyle.THIN);
        listStyle.setAlignment(HorizontalAlignment.CENTER);
        listStyle.setVerticalAlignment(VerticalAlignment.CENTER); //
        listStyle.setWrapText(true);//自动换行

        //设置行
        //headerRow
        XSSFRow headerRow = sheet.createRow(0);
        headerRow.setHeightInPoints(30);//设置行的高度是50个点
        XSSFRow headerRow1 = sheet.createRow(1);
        headerRow1.setHeightInPoints(30);//设置行的高度是50个点
        //titleRow
        XSSFRow titleRow = sheet.createRow(2);
        titleRow.setHeightInPoints(30);//设置行的高度是50个点


        //跨行跨列
        //header行
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, title.length - 1);
        CellRangeAddress region1 = new CellRangeAddress(1, 1, 0, title.length - 1);
        sheet.addMergedRegion(region);
        sheet.addMergedRegion(region1);


        for (int i = 0; i < title.length; i++) {
            sheet.setColumnWidth(i, 20 * 256);//设置第i列的宽度是31个字符宽度
            XSSFCell headerCell1 = headerRow1.createCell(i);
            if (i == 0) {
                XSSFCell headerCell = headerRow.createCell(0);
                headerCell.setCellValue(header[0]);
                headerCell.setCellStyle(headerStyle);

                headerCell1.setCellValue(header1[0]);
            }
        }


        for (int i = 0; i < title.length; i++) {
            XSSFCell titleCell = titleRow.createCell(i);
            titleCell.setCellStyle(titleStyle);
            titleCell.setCellValue(title[i]);
        }

        //list行
        //list数据

        XSSFRow listRow = sheet.createRow(3);
        XSSFCell getId = listRow.createCell(0);
        getId.setCellValue(strandCount.getId());
        getId.setCellStyle(titleStyle);

        XSSFCell getUnit = listRow.createCell(1);
        getUnit.setCellValue(strandCount.getUnit());
        getUnit.setCellStyle(titleStyle);

        XSSFCell getSchoolSection = listRow.createCell(2);
        getSchoolSection.setCellValue(strandCount.getSchoolSection());
        getSchoolSection.setCellStyle(titleStyle);

        XSSFCell getTeacherNum = listRow.createCell(3);
        getTeacherNum.setCellValue(strandCount.getTeacherNum());
        getTeacherNum.setCellStyle(titleStyle);

        XSSFCell getStrandNum = listRow.createCell(4);
        getStrandNum.setCellValue(strandCount.getStrandNum());
        getStrandNum.setCellStyle(titleStyle);


        XSSFCell getReturnLiuZhouNum = listRow.createCell(5);
        getReturnLiuZhouNum.setCellStyle(titleStyle);
        getReturnLiuZhouNum.setCellValue(strandCount.getReturnLiuZhouNum());


        XSSFCell getIntro = listRow.createCell(6);
        getIntro.setCellValue(strandCount.getIntro());
        getIntro.setCellStyle(titleStyle);


        return sheet;
    }
}
