package com.lcvc.new_coronavirus_report.util.poi;


import com.lcvc.new_coronavirus_report.model.Questionnaire;
import com.lcvc.new_coronavirus_report.model.form.DailyReportTable;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;


@Service
public class ExcelWirteForStudentSheet {

    /**
     * 学生自觉居家观察健康状况表
     *
     * @param book 传递进来的工作部对象
     * @param list 要遍历的数据集合
     * @return
     */
    public static XSSFSheet getShee(XSSFWorkbook book,  List<Questionnaire> list) {
        String header[] = {"学生自觉居家观察健康状况表"};
        String header1[] = {"单位：                             年级班级：                               填报人：                                                                            填报日期：     年     月     日"};
        String title[] = {"序号",
                "学生姓名",
                "当天本人健康状况",
                "当天家庭成员健康状况",
                "当天是否密切接触来自或到达过武汉及湖北其他地区人员",
                "是否为疑似病例或确诊病例",
                "备注"
        };
        String footer[] = {"注：学生、家庭成员健康状况项，无问题的填写“健康”，如存在发热、咳嗽、呼吸困难等可疑症状或为疑似、确诊病例的，需进行具体描述。"};


        // 创建一个工作表
        XSSFSheet sheet = book.createSheet("学生自觉居家观察健康状况表");

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
        //footer
        XSSFRow footerRow = sheet.createRow(list.size()+3);
        footerRow.setHeightInPoints(30);//设置行的高度是50个点


        //跨行跨列
        //header行
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, title.length-1);
        CellRangeAddress region1 = new CellRangeAddress(1, 1, 0, title.length-1);
        //footer
        CellRangeAddress region2 = new CellRangeAddress(list.size()+3, list.size()+3, 0, title.length-1);
        sheet.addMergedRegion(region);
        sheet.addMergedRegion(region1);
        sheet.addMergedRegion(region2);

        for(int i=0;i<title.length;i++){
            sheet.setColumnWidth(i, 20 * 256);//设置第i列的宽度是31个字符宽度
            XSSFCell headerCell1 = headerRow1.createCell(i);
            if (i==0){
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
            XSSFRow listRow = sheet.createRow(i + 3);
            XSSFCell id = listRow.createCell(0);
            id.setCellValue(list.get(i).getId());
            id.setCellStyle(titleStyle);

            XSSFCell name = listRow.createCell(1);
            name.setCellValue(list.get(i).getName());
            name.setCellStyle(titleStyle);

            XSSFCell myHealth = listRow.createCell(2);
            myHealth.setCellValue(list.get(i).getMyHealth());
            myHealth.setCellStyle(titleStyle);

            XSSFCell myfamilyHealth = listRow.createCell(3);
            myfamilyHealth.setCellValue(list.get(i).getMyfamilyHealth());
            myfamilyHealth.setCellStyle(titleStyle);

            XSSFCell touchHuBeiPerson = listRow.createCell(4);
            touchHuBeiPerson.setCellValue("否");
            touchHuBeiPerson.setCellStyle(titleStyle);
            if(list.get(i).getTouchHuBeiPerson()!=null){
                if (list.get(i).getTouchHuBeiPerson()){
                    touchHuBeiPerson.setCellValue("是");
                }else{
                    touchHuBeiPerson.setCellValue("否");
                }
            }

            XSSFCell confirmIll = listRow.createCell(5);
            confirmIll.setCellStyle(titleStyle);
            if(list.get(i).getConfirmIll()!=null){
                if (list.get(i).getConfirmIll()){
                    confirmIll.setCellValue("是");
                }else{
                    confirmIll.setCellValue("否");
                }
            }


            XSSFCell intro = listRow.createCell(6);
            intro.setCellValue(list.get(i).getIntro());
            intro.setCellStyle(titleStyle);
        }
        XSSFCell footerCell = footerRow.createCell(0);
        footerCell.setCellValue(footer[0]);
        return sheet;
    }
}
