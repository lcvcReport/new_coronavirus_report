package com.lcvc.new_coronavirus_report.util.poi;


import com.lcvc.new_coronavirus_report.model.Strand;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;


@Service
public class ExcelWirteForStrandSheet {

    /**
     * 滞留疫区无法返回教职工信息表
     *
     * @param book 传递进来的工作部对象
     * @param list 要遍历的数据集合
     * @return
     */
    public static XSSFSheet getShee(XSSFWorkbook book, List<Strand> list) {
        String header[] = {"滞留疫区无法返回教职工情况统计"};
        String header1[] = {"单位：                                填表人：                          联系电话：                  填表时间：                           "};
        String title[] = {
                "序号",
                "单位",
                "姓名",
                "目前所在城市",
                "健康状况",
                "2020年春学期原计划承担课程或岗位",
                "无法返回原因",
                "本周内是否返柳",
                "计划返柳时间",
                "计划返柳交通工具",
                "备注"
        };

        // 创建一个工作表
        XSSFSheet sheet = book.createSheet("附件2");

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
        for (int i = 0; i < list.size(); i++) {

            XSSFRow listRow = sheet.createRow(i+3);
            XSSFCell getId = listRow.createCell(0);
            getId.setCellValue(list.get(i).getId());
            getId.setCellStyle(titleStyle);

            XSSFCell getUnit = listRow.createCell(1);
            getUnit.setCellValue(list.get(i).getUnit());
            getUnit.setCellStyle(titleStyle);

            XSSFCell getName = listRow.createCell(2);
            getName.setCellValue(list.get(i).getName());
            getName.setCellStyle(titleStyle);

            XSSFCell getCurrentCity = listRow.createCell(3);
            getCurrentCity.setCellValue(list.get(i).getCurrentCity());
            getCurrentCity.setCellStyle(titleStyle);

            XSSFCell getMyHealth = listRow.createCell(4);
            getMyHealth.setCellValue(list.get(i).getMyHealth());
            getMyHealth.setCellStyle(titleStyle);

            XSSFCell getWorkType = listRow.createCell(5);
            getWorkType.setCellValue(list.get(i).getWorkType());
            getWorkType.setCellStyle(titleStyle);


            XSSFCell getReason = listRow.createCell(6);
            getReason.setCellStyle(titleStyle);
            getReason.setCellValue(list.get(i).getReason());


            XSSFCell getReturnLiuZhou = listRow.createCell(7);
            getReturnLiuZhou.setCellStyle(titleStyle);
            if (list.get(i).getReturnLiuZhou() != null) {
                if (list.get(i).getReturnLiuZhou()) {
                    getReturnLiuZhou.setCellValue("是");
                } else {
                    getReturnLiuZhou.setCellValue("否");
                }
            }
            XSSFCell getReturnLiuZhouTime = listRow.createCell(8);
            getReturnLiuZhouTime.setCellStyle(titleStyle);
            if (list.get(i).getReturnLiuZhouTime()!=null){
                SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
                getReturnLiuZhouTime.setCellValue(formatter.format(list.get(i).getReturnLiuZhouTime()));
            }

            XSSFCell getReturnLiuZhouWay = listRow.createCell(9);
            getReturnLiuZhouWay.setCellStyle(titleStyle);
            getReturnLiuZhouWay.setCellValue(list.get(i).getReturnLiuZhouWay());


            XSSFCell getIntro = listRow.createCell(10);
            getIntro.setCellStyle(titleStyle);
            getIntro.setCellValue(list.get(i).getIntro());
        }
        return sheet;
    }
}
