package com.lcvc.new_coronavirus_report.util.poi;


import com.lcvc.new_coronavirus_report.model.Questionnaire;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;


@Service
public class ExcelWirteForArriveGZHHSheet {

    /**
     * 我市到过广东、浙江、河南、湖南省的人员排查日报表（七）
     *
     * @param book 传递进来的工作部对象
     * @param list 要遍历的数据集合
     * @return
     */
    public static XSSFSheet getShee(XSSFWorkbook book,  List<Questionnaire> list) {
        String header[]={"我市到过广东、浙江、河南、湖南省的人员排查日报表（七）"};
        String title[]={"序号",
                "姓名",
                "身份证号码",
                "电话号码",
                "电话排查内容",
                "入户排查内容",
                "管控措施",
                "备注"};
        String title1[]={"省份",
                "离开时间",
                "目前在柳居住地",
                "是否发烧",
                "是否有咳嗽、胸闷等不适症状",
                "省份",
                "离开时间",
                "到柳时间",
                "目前在柳居住地",
                "是否发烧",
                "是否有咳嗽、胸闷等不适症状",
                "车次/航班/汽车/自驾等回柳方式",
                "同行人姓名"};

        // 创建一个工作表
        XSSFSheet sheet = book.createSheet("表八");


        // 设置单元格表单头部样式
        XSSFCellStyle headerStyle = book.createCellStyle();
        XSSFFont headerFont = book.createFont();
        headerFont.setFontHeightInPoints((short) 16);
        headerStyle.setFont(headerFont);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
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

        //第二行标题栏样式
        XSSFCellStyle title1Style = book.createCellStyle();
        title1Style.setFont(titlefont);
        title1Style.setBorderBottom(BorderStyle.THIN);
        title1Style.setBorderTop(BorderStyle.THIN);
        title1Style.setBorderLeft(BorderStyle.THIN);
        title1Style.setBorderRight(BorderStyle.THIN);
        title1Style.setAlignment(HorizontalAlignment.CENTER);
        title1Style.setVerticalAlignment(VerticalAlignment.CENTER); //
        title1Style.setWrapText(true);//自动换行

        XSSFCellStyle bgcolorStyle = book.createCellStyle();
        bgcolorStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        bgcolorStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        bgcolorStyle.setFont(titlefont);
        bgcolorStyle.setBorderBottom(BorderStyle.THIN);
        bgcolorStyle.setBorderTop(BorderStyle.THIN);
        bgcolorStyle.setBorderLeft(BorderStyle.THIN);
        bgcolorStyle.setBorderRight(BorderStyle.THIN);
        bgcolorStyle.setAlignment(HorizontalAlignment.CENTER);
        bgcolorStyle.setVerticalAlignment(VerticalAlignment.CENTER); //
        bgcolorStyle.setWrapText(true);//自动换行


        //
        //设置行
        //header
        XSSFRow headerRow = sheet.createRow(0);
        headerRow.setHeightInPoints(30);//设置行的高度是50个点
        //titie
        XSSFRow titleRow = sheet.createRow(1);
        titleRow.setHeightInPoints(25);//设置行的高度是50个点
        XSSFRow titleRow1 = sheet.createRow(2);
        titleRow1.setHeightInPoints(35);//设置行的高度是50个点

        //跨行跨列
        //header行
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 17);
        //列
        CellRangeAddress region0 = new CellRangeAddress(1, 2, 0, 0);
        CellRangeAddress region1 = new CellRangeAddress(1, 2, 1, 1);
        CellRangeAddress region2 = new CellRangeAddress(1, 2, 2, 2);
        CellRangeAddress region3 = new CellRangeAddress(1, 2, 3, 3);

        //电话排查
        CellRangeAddress region5 = new CellRangeAddress(1, 1, 4, 8);
        //户口排查
        CellRangeAddress region6 = new CellRangeAddress(1, 1, 9, 16);
        //管控
        CellRangeAddress region7 = new CellRangeAddress(1, 2, 17, 17);
        //备注
        CellRangeAddress region8 = new CellRangeAddress(1, 2, 18, 18);

        sheet.addMergedRegion(region);
        sheet.addMergedRegion(region0);
        sheet.addMergedRegion(region1);
        sheet.addMergedRegion(region2);
        sheet.addMergedRegion(region3);
        sheet.addMergedRegion(region5);
        sheet.addMergedRegion(region6);
        sheet.addMergedRegion(region7);
        sheet.addMergedRegion(region8);

        //添加表头栏
        for (int i = 0; i <19 ; i++) {
            XSSFCell headerCell= headerRow.createCell(i);
            sheet.setColumnWidth(i, 15 * 256);//设置第i列的宽度是31个字符宽度
            headerCell.setCellStyle(headerStyle);
            if (i==0){
                headerCell.setCellValue(header[0]);
            }
        }

        int j=0;
        for (int i = 0; i <19 ; i++) {
            XSSFCell titleCell= titleRow.createCell(i);
            XSSFCell titleCell1= titleRow1.createCell(i);
            sheet.setColumnWidth(i, 15 * 256);//设置第i列的宽度是31个字符宽度
            titleCell.setCellStyle(titleStyle);
            titleCell1.setCellStyle(titleStyle);
            if (i<4){
                titleCell.setCellValue(title[i]);

            }
            if (i==4){
                titleCell.setCellValue(title[4]);
            }
            if (i==9){
                titleCell.setCellValue(title[5]);
            }
            //设置背景色
//            if (i==4||i==5||i==8||i==9){
//                titleCell1.setCellStyle(bgcolorStyle);
//            }
            if (i>=4&&i<=16&&j<title1.length){
                titleCell1.setCellValue(title1[j]);
                j++;
            }
            if (i==17){
                titleCell.setCellValue(title[6]);
            }
            if (i==18)titleCell.setCellValue(title[7]);
        }

        //list数据
        for (int i = 0; i <list.size() ; i++) {
            XSSFRow listRow = sheet.createRow(i+3);
            listRow.setHeightInPoints(35);//设置行的高度是50个点

            XSSFCell id= listRow.createCell(0);
            id.setCellValue(list.get(i).getId());
            id.setCellStyle(titleStyle);

            XSSFCell name= listRow.createCell(1);
            name.setCellValue(list.get(i).getName());
            name.setCellStyle(titleStyle);

            XSSFCell identityCard= listRow.createCell(2);
            identityCard.setCellValue(list.get(i).getIdentityCard());
            identityCard.setCellStyle(titleStyle);

            XSSFCell tel= listRow.createCell(3);
            tel.setCellValue(list.get(i).getTel());
            tel.setCellStyle(titleStyle);

            //电话排查内容
            XSSFCell telShengfen= listRow.createCell(4);
            telShengfen.setCellStyle(titleStyle);
            telShengfen.setCellValue(list.get(i).getProvince());

            XSSFCell telLeaveTime= listRow.createCell(5);
            if (list.get(i).getLeaveHubei()!=null){
                SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
                telLeaveTime.setCellValue(formatter.format(list.get(i).getLeaveHubei()));
            }
            telLeaveTime.setCellStyle(titleStyle);

            XSSFCell telAddressInLiuZhou= listRow.createCell(6);
            telAddressInLiuZhou.setCellValue(list.get(i).getAddressInLiuZhou());
            telAddressInLiuZhou.setCellStyle(titleStyle);
            //是否发烧
            XSSFCell telMyHealth= listRow.createCell(7);
            telMyHealth.setCellStyle(titleStyle);
            telMyHealth.setCellValue(list.get(i).getMyHealth());
            if(list.get(i).getMyHealth()!=null){
                if(list.get(i).getMyHealth().contains("发热")){
                    telMyHealth.setCellValue("是");
                }else{
                    telMyHealth.setCellValue("否");
                }
            }
            XSSFCell telMyHealth1= listRow.createCell(8);
            telMyHealth1.setCellStyle(titleStyle);
            telMyHealth1.setCellValue(list.get(i).getMyHealth());


            //入户排查内容
            XSSFCell intoShengfen= listRow.createCell(9);
            intoShengfen.setCellStyle(titleStyle);
            intoShengfen.setCellValue(list.get(i).getProvince());

            XSSFCell intoLeaveTime= listRow.createCell(10);
            if (list.get(i).getLeaveHubei()!=null){
                SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
                intoLeaveTime.setCellValue(formatter.format(list.get(i).getLeaveHubei()));
            }
            intoLeaveTime.setCellStyle(titleStyle);


            XSSFCell intoArriveLiuZhou= listRow.createCell(11);
            if (list.get(i).getArriveLiuZhou()!=null){
                SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
                intoArriveLiuZhou.setCellValue(formatter.format(list.get(i).getArriveLiuZhou()));
            }
            intoArriveLiuZhou.setCellStyle(titleStyle);


            XSSFCell intoAddressInLiuZhou= listRow.createCell(12);
            intoAddressInLiuZhou.setCellValue(list.get(i).getAddressInLiuZhou());
            intoAddressInLiuZhou.setCellStyle(titleStyle);

            XSSFCell intoMyHealth= listRow.createCell(13);
            intoMyHealth.setCellStyle(titleStyle);
            if(list.get(i).getMyHealth()!=null){
                if(list.get(i).getMyHealth().contains("发热")){
                    intoMyHealth.setCellValue("是");
                }else{
                    intoMyHealth.setCellValue("否");
                }
            }

            XSSFCell intoMyHealth1= listRow.createCell(14);
            intoMyHealth1.setCellStyle(titleStyle);
            intoMyHealth1.setCellValue(list.get(i).getMyHealth());


            XSSFCell intoLeaveHubeiWay= listRow.createCell(15);
            intoLeaveHubeiWay.setCellValue(list.get(i).getLeaveHubeiWay());
            intoLeaveHubeiWay.setCellStyle(titleStyle);

            XSSFCell intoLeaveTogetherPersonName= listRow.createCell(16);
            intoLeaveTogetherPersonName.setCellValue(list.get(i).getLeaveTogetherPersonName());
            intoLeaveTogetherPersonName.setCellStyle(titleStyle);

            //管控措施  备注
            XSSFCell manageMethods= listRow.createCell(17);
            manageMethods.setCellValue(list.get(i).getManageMethods());
            manageMethods.setCellStyle(titleStyle);

            XSSFCell intro= listRow.createCell(18);
            intro.setCellValue(list.get(i).getIntro());
            intro.setCellStyle(titleStyle);
        }



        return sheet;
    }
}
