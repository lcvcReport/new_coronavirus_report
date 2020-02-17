package com.lcvc.new_coronavirus_report.util.poi;


import com.lcvc.new_coronavirus_report.model.Questionnaire;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;


@Service
public class ExcelWirteForTouchHuBeiSheet {

    /**
     * 1.7
     * 1月16日以后密切接触过来自或到达过湖北疫区人员情况表
     *
     * @param book 传递进来的工作部对象
     * @param list 要遍历的数据集合
     * @return
     */
    public static XSSFSheet getShee(XSSFWorkbook book, List<Questionnaire> list) {
        String header[] = {"  密切接触过来自或到达过湖北等疫区人员情况表"};
         String title[] = {"序号", "姓名", "身份证号码", "联系电话", "目前在柳居住地",
                           "接触过疫区人员的姓名","是否发烧", "是否有咳嗽、胸闷等不适症状"};

        // 创建一个工作表
        XSSFSheet sheet = book.createSheet("密切接触者");

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
            XSSFCell id = listRow.createCell(0);
            id.setCellValue(list.get(i).getId());
            id.setCellStyle(titleStyle);

            XSSFCell name = listRow.createCell(1);
            name.setCellValue(list.get(i).getName());
            name.setCellStyle(titleStyle);

            XSSFCell identityCard = listRow.createCell(2);
            identityCard.setCellValue(list.get(i).getIdentityCard());
            identityCard.setCellStyle(titleStyle);

            XSSFCell tel = listRow.createCell(3);
            tel.setCellValue(list.get(i).getTel());
            tel.setCellStyle(titleStyle);

            XSSFCell epidemicArea = listRow.createCell(4);
            epidemicArea.setCellValue(list.get(i).getAddressInLiuZhou());
            epidemicArea.setCellStyle(titleStyle);

            XSSFCell addressInLiuZhou = listRow.createCell(5);
            addressInLiuZhou.setCellValue(list.get(i).getTouchHuBeiPersonName());
            addressInLiuZhou.setCellStyle(titleStyle);
            //是否发烧
            XSSFCell myHealth = listRow.createCell(6);
            myHealth.setCellStyle(titleStyle);
            if(list.get(i).getMyHealth()!=null){
                if(list.get(i).getMyHealth().contains("发热")){
                    myHealth.setCellValue("是");
                }else{
                    myHealth.setCellValue("否");
                }
            }

            XSSFCell myHealth1= listRow.createCell(7);
            myHealth1.setCellValue(list.get(i).getMyHealth());
            myHealth1.setCellStyle(titleStyle);

        }


        return sheet;
    }
}
