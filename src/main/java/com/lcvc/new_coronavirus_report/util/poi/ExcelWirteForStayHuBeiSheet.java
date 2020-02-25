package com.lcvc.new_coronavirus_report.util.poi;


import com.lcvc.new_coronavirus_report.model.Questionnaire;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;


@Service
public class ExcelWirteForStayHuBeiSheet {

    /**
     *我市现在仍在湖北（含武汉市）、广东、浙江、河南、湖南出差、休假、旅游、探亲等短时停留的师生员工(六)
     *
     * @param book 传递进来的工作部对象
     * @param list 要遍历的数据集合
     * @return
     */
    public static XSSFSheet getShee(XSSFWorkbook book, List<Questionnaire> list) {
        String header[] = {"我市现在仍在湖北（含武汉市）、广东、浙江、河南、湖南出差、休假、旅游、探亲等短时停留的师生员工"};
        String title[] = {
                "序号",
                "姓名",
                "单位/学校年级班级（专业）",
                "性别",
                "身份证号码",
                "手机号码",
                "疫区居住地",
                "柳州居住地",
                "离柳时间",
                "回柳时间",
                "备注"};
        String footer[]={"填表说明：仍在上述五省，未回柳州的师生员工。"};

        // 创建一个工作表
        XSSFSheet sheet = book.createSheet("表六");

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
        headerRow.setHeightInPoints(50);//设置行的高度是50个点
        //titie
        XSSFRow titleRow = sheet.createRow(1);
        titleRow.setHeightInPoints(30);//设置行的高度是50个点


        //跨行跨列
        //header行
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, title.length - 1);
        CellRangeAddress region1 = new CellRangeAddress(list.size()+2, list.size()+2, 0, title.length - 1);

        sheet.addMergedRegion(region);
        sheet.addMergedRegion(region1);

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

            XSSFCell schoolClassOrworkType = listRow.createCell(2);
            schoolClassOrworkType.setCellStyle(titleStyle);
            if (list.get(i).getStudentNumber()!=null){
                schoolClassOrworkType.setCellValue(list.get(i).getSchoolClass());
            }
            if (list.get(i).getTeacherNumber()!=null){
                schoolClassOrworkType.setCellValue(list.get(i).getWorkType());
            }

            XSSFCell sex = listRow.createCell(3);
            sex.setCellValue(list.get(i).getSex());
            sex.setCellStyle(titleStyle);

            XSSFCell identityCard = listRow.createCell(4);
            identityCard.setCellValue(list.get(i).getIdentityCard());
            identityCard.setCellStyle(titleStyle);

            XSSFCell tel = listRow.createCell(5);
            tel.setCellValue(list.get(i).getTel());
            tel.setCellStyle(titleStyle);

            XSSFCell epidemicArea = listRow.createCell(6);
            epidemicArea.setCellValue(list.get(i).getEpidemicArea());
            epidemicArea.setCellStyle(titleStyle);

            XSSFCell addressInLiuZhou = listRow.createCell(7);
            addressInLiuZhou.setCellValue(list.get(i).getAddressInLiuZhou());
            addressInLiuZhou.setCellStyle(titleStyle);

            XSSFCell leaveLiuZhou = listRow.createCell(8);
            if (list.get(i).getLeaveLiuZhou()!=null){
                SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
                leaveLiuZhou.setCellValue(formatter.format(list.get(i).getLeaveLiuZhou()));
            }
            leaveLiuZhou.setCellStyle(titleStyle);

            XSSFCell arriveLiuZhou = listRow.createCell(9);
            if (list.get(i).getArriveLiuZhou()!=null){
                SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
                arriveLiuZhou.setCellValue(formatter.format(list.get(i).getArriveLiuZhou()));
            }
            arriveLiuZhou.setCellStyle(titleStyle);

            XSSFCell intro = listRow.createCell(10);
            intro.setCellValue(list.get(i).getIntro());
            intro.setCellStyle(titleStyle);
        }

        //footer
        XSSFRow footerRow = sheet.createRow(list.size()+2);
        footerRow.setHeightInPoints(30);//设置行的高度是50个点

        XSSFCell footerCell0 = footerRow.createCell(0);
        footerCell0.setCellValue(footer[0]);
        return sheet;
    }
}
