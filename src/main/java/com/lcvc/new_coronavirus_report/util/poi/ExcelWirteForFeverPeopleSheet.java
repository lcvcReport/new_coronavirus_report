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
public class ExcelWirteForFeverPeopleSheet {

    /**
     * 其他发热人员情况登记表
     *
     * @param book 传递进来的工作部对象
     * @param list 要遍历的数据集合
     * @return
     */
    public static XSSFSheet getShee(XSSFWorkbook book, List<Questionnaire> list) {
         String header[] = {"其他发热人员情况登记表"};
         String title[] = {"序号",
                 "姓名",
                 "身份证号码",
                 "联系电话",
                 "目前在居住地(详细到户号)",
                 "是否发烧",
                 "体温(℃)",
                 "是否接触过湖北等疫区人员",
                 "接触过湖北等疫区人员的姓名",
                 "买药用途(自用或为他人购买，如为他人购买请填写情况并进一步核查)",
                 "备注",
                 "单位/学校年级班级(专业)"};
         String footer[]={"填表说明：指除了“医疗机构发现发热人员”和“药店登记发热人员”外的人员，主要是社区、单位等发现的"};

        // 创建一个工作表
        XSSFSheet sheet = book.createSheet("其他发热人员情况登记表");

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
        titleRow.setHeightInPoints(50);//设置行的高度是50个点


        //跨行跨列
        //header行
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, title.length - 1);
        CellRangeAddress region1 = new CellRangeAddress(list.size()+3, list.size()+3, 0, title.length - 1);
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

            XSSFCell identityCard = listRow.createCell(2);
            identityCard.setCellValue(list.get(i).getIdentityCard());
            identityCard.setCellStyle(titleStyle);

            XSSFCell tel = listRow.createCell(3);
            tel.setCellValue(list.get(i).getTel());
            tel.setCellStyle(titleStyle);

            XSSFCell addressInLiuZhou = listRow.createCell(4);
            addressInLiuZhou.setCellValue(list.get(i).getAddressInLiuZhou());
            addressInLiuZhou.setCellStyle(titleStyle);

            XSSFCell myHealth = listRow.createCell(5);
            myHealth.setCellStyle(titleStyle);
            if(list.get(i).getMyHealth()!=null){
                if(list.get(i).getMyHealth().contains("发热")){
                    myHealth.setCellValue("是");
                }else{
                    myHealth.setCellValue("否");
                }
            }

            XSSFCell temperature = listRow.createCell(6);
            temperature.setCellValue(list.get(i).getTemperature());//Fever
            temperature.setCellStyle(titleStyle);

            XSSFCell touchHuBeiPerson = listRow.createCell(7);
            if (list.get(i).getTouchHuBeiPerson()!=null){
                if(list.get(i).getTouchHuBeiPerson()){
                    touchHuBeiPerson.setCellValue("是");
                }else{
                    touchHuBeiPerson.setCellValue("否");
                }
            }
            touchHuBeiPerson.setCellStyle(titleStyle);

            XSSFCell touchHuBeiPersonName = listRow.createCell(8);
            touchHuBeiPersonName.setCellValue(list.get(i).getTouchHuBeiPersonName());
            touchHuBeiPersonName.setCellStyle(titleStyle);

            XSSFCell useMedicines = listRow.createCell(9);
            useMedicines.setCellValue("");
            useMedicines.setCellStyle(titleStyle);

            XSSFCell intro = listRow.createCell(10);
            intro.setCellValue(list.get(i).getIntro());
            intro.setCellStyle(titleStyle);

            XSSFCell schoolClassAndWorkType = listRow.createCell(11);
            if (list.get(i).getStudentNumber()!=null){
                schoolClassAndWorkType.setCellValue(list.get(i).getSchoolClass());
            }
            if (list.get(i).getTeacherNumber()!=null){
                schoolClassAndWorkType.setCellValue(list.get(i).getWorkType());
            }
            schoolClassAndWorkType.setCellStyle(titleStyle);
        }
        //表尾
        XSSFRow footerRow = sheet.createRow(list.size()+2);
        footerRow.setHeightInPoints(30);//设置行的高度
        XSSFCell footerCell = footerRow.createCell(0);
        footerCell.setCellValue(footer[0]);
        return sheet;
    }
}
