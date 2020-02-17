package com.lcvc.new_coronavirus_report.util.poi;


import com.lcvc.new_coronavirus_report.model.form.DailyReportTable;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;


@Service
public class ExcelWirteForDailyReportSheet {

    /**
     * 1.1重点人群排查管理日报表
     *
     * @param book 传递进来的工作部对象
     * @param list 要遍历的数据集合
     * @return
     */
    public static XSSFSheet getShee(XSSFWorkbook book, DailyReportTable list) {
        String header[] = {"重点人群排查管理日报表"};
        String header1[] = {"填报单位:", "签发时间：       年     月    日"};
        String title[] = {"来自武汉市的市外人员",
                "来自湖北省(除武汉市外)的市外人员",
                "我市到过武汉市的人员",
                "我市到过湖北省(除武汉市外)的人员",
                "密切接触者人数",
                "我市现在仍在湖北出差、休假、旅游、探亲等短时停留人员",
                "其他发热人员",
                "备注"
        };
        String sumTitle[] = {"当日增减", "当日存量"};
        String footer[] = {"填报人：", "审核人：", "签发人："};


        // 创建一个工作表
        XSSFSheet sheet = book.createSheet("表一");

        // 设置单元格表单头部样式
        CellStyle headerStyle = book.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        XSSFFont headerFont = book.createFont();
        headerFont.setFontHeightInPoints((short) 16);
        headerStyle.setFont(headerFont);

        //向左对齐
        XSSFCellStyle leftAlignStyle = book.createCellStyle();
        leftAlignStyle.setAlignment(HorizontalAlignment.LEFT);
        leftAlignStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        //向右对齐
        XSSFCellStyle rightAlignStyle = book.createCellStyle();
        rightAlignStyle.setAlignment(HorizontalAlignment.RIGHT);
        rightAlignStyle.setVerticalAlignment(VerticalAlignment.CENTER);

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

        //背景颜色
        XSSFCellStyle bgcolorStyle = book.createCellStyle();
        bgcolorStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        bgcolorStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        bgcolorStyle.setFont(listFont);
        bgcolorStyle.setBorderBottom(BorderStyle.THIN);
        bgcolorStyle.setBorderTop(BorderStyle.THIN);
        bgcolorStyle.setBorderLeft(BorderStyle.THIN);
        bgcolorStyle.setBorderRight(BorderStyle.THIN);
        bgcolorStyle.setAlignment(HorizontalAlignment.CENTER);
        bgcolorStyle.setVerticalAlignment(VerticalAlignment.CENTER); //
        bgcolorStyle.setWrapText(true);//自动换行
        //设置行
        //第一行无
        XSSFRow noneRow = sheet.createRow(0);

        //headerRow
        XSSFRow headerRow = sheet.createRow(1);
        headerRow.setHeightInPoints(25);//设置行的高度是50个点
        //headerRow
        XSSFRow headerRow1 = sheet.createRow(2);
        headerRow1.setHeightInPoints(30);//设置行的高度是50个点

        //titleRow
        XSSFRow titleRow = sheet.createRow(3);
        titleRow.setHeightInPoints(45);//设置行的高度是50个点
        //titleRow1
        XSSFRow titleRow1 = sheet.createRow(4);
        titleRow1.setHeightInPoints(30);//设置行的高度是50个点


        //跨行跨列
        //header行
        CellRangeAddress region = new CellRangeAddress(1, 1, 0, title.length * 2 - 1);
        //header1
        CellRangeAddress region1 = new CellRangeAddress(2, 2, 0, 4);
        CellRangeAddress region2 = new CellRangeAddress(2, 2, 8, 11);
        //title
        CellRangeAddress region3 = new CellRangeAddress(3, 3, 0, 1);
        CellRangeAddress region4 = new CellRangeAddress(3, 3, 2, 3);
        CellRangeAddress region5 = new CellRangeAddress(3, 3, 4, 5);
        CellRangeAddress region6 = new CellRangeAddress(3, 3, 6, 7);
        CellRangeAddress region7 = new CellRangeAddress(3, 3, 8, 9);
        CellRangeAddress region8 = new CellRangeAddress(3, 3, 10, 11);
        CellRangeAddress region12 = new CellRangeAddress(3, 3, 12, 13);
        CellRangeAddress region13 = new CellRangeAddress(3, 4, 14, 14);
        //footer
        CellRangeAddress region9 = new CellRangeAddress(6, 6, 0, 3);
        CellRangeAddress region10 = new CellRangeAddress(6, 6, 4, 7);
        CellRangeAddress region11 = new CellRangeAddress(6, 6, 8, 11);

        sheet.addMergedRegion(region);
        sheet.addMergedRegion(region1);
        sheet.addMergedRegion(region2);
        sheet.addMergedRegion(region3);
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


        //第一行
        for (int i = 0; i < title.length * 2; i++) {
            sheet.setColumnWidth(i, 15 * 256);//设置第i列的宽度是31个字符宽度
        }
        //第二行表头
        XSSFCell headerCell = headerRow.createCell(0);
        headerCell.setCellValue(header[0]);
        headerCell.setCellStyle(headerStyle);

        //第三行 左边
        XSSFCell headerCell1 = headerRow1.createCell(0);
        headerCell1.setCellValue(header1[0]);
        headerCell1.setCellStyle(leftAlignStyle);
        //第三行 右边
        XSSFCell headerCell2 = headerRow1.createCell(8);
        headerCell2.setCellValue(header1[1]);
        headerCell2.setCellStyle(rightAlignStyle);

        //title
        int titleI = 0;
        for (int i = 0; i < title.length * 2-1; i++) {
            XSSFCell titleCell = titleRow.createCell(i);
            titleCell.setCellStyle(titleStyle);
            if (i % 2 == 0) {
                titleCell.setCellValue(title[titleI]);
                titleI++;
            }
//            if(i==12|i==13){
//                titleCell.setCellStyle(bgcolorStyle);
//            }
        }

        for (int i = 0; i < title.length * 2-2; i++) {
            XSSFCell titleCell1 = titleRow1.createCell(i);
            titleCell1.setCellStyle(titleStyle);
            if (i % 2 == 0) {
                titleCell1.setCellValue(sumTitle[0]);
            } else {
                titleCell1.setCellValue(sumTitle[1]);
            }
            XSSFCell titleCell14 = titleRow1.createCell(14);
            titleCell14.setCellStyle(titleStyle);
        }

        //数据栏  list
        XSSFRow listRow = sheet.createRow(5);
        listRow.setHeightInPoints(30);//设置行的高度是50个点

        XSSFCell formWuHanDeIn = listRow.createCell(0);
        XSSFCell formWuHanSum = listRow.createCell(1);
        XSSFCell formHuBeiDeIn = listRow.createCell(2);
        XSSFCell formHubeiSum = listRow.createCell(3);
        XSSFCell arriveWuHanDeIn = listRow.createCell(4);
        XSSFCell arriveWuHanSum = listRow.createCell(5);
        XSSFCell arriveHuBeiDeIn = listRow.createCell(6);
        XSSFCell arriveHuBeiSum = listRow.createCell(7);
        XSSFCell touchPersonDeIn = listRow.createCell(8);
        XSSFCell touchPersonSum = listRow.createCell(9);
        XSSFCell stayInHubeiDeIn = listRow.createCell(10);
        XSSFCell stayInHubeiSum = listRow.createCell(11);
        XSSFCell observePeopleDeIn = listRow.createCell(12);
        XSSFCell observePeoplesum = listRow.createCell(13);
        XSSFCell intro = listRow.createCell(14);

        //来自武汉市的市外人员 当日增减量
        formWuHanDeIn.setCellValue(list.getDailyReportToday().getComefromWuHanNumber() - list.getDailyReportInYesterday().getComefromWuHanNumber());
        formWuHanDeIn.setCellStyle(listStyle);
        //自武汉市的市外人员  当日存量
        formWuHanSum.setCellValue(list.getDailyReportToday().getComefromWuHanNumber());
        formWuHanSum.setCellStyle(listStyle);
        //来自湖北省（除武汉市外）的市外人员 当日增减量
        formHuBeiDeIn.setCellValue(list.getDailyReportToday().getComefromHuBeiNumber() - list.getDailyReportInYesterday().getComefromHuBeiNumber());
        formHuBeiDeIn.setCellStyle(listStyle);
        //来自湖北省（除武汉市外）的市外人员  当日存量
        formHubeiSum.setCellValue(list.getDailyReportToday().getComefromHuBeiNumber());
        formHubeiSum.setCellStyle(listStyle);
        //我市到过武汉市的人员 当日增减量
        arriveWuHanDeIn.setCellValue(list.getDailyReportToday().getArriveWuHanNumber() - list.getDailyReportInYesterday().getArriveWuHanNumber());
        arriveWuHanDeIn.setCellStyle(listStyle);
        //我市到过武汉市的人员  当日存量
        arriveWuHanSum.setCellValue(list.getDailyReportToday().getArriveWuHanNumber());
        arriveWuHanSum.setCellStyle(listStyle);
        //我市到过湖北省（除武汉市外）的人员  当日增减量
        arriveHuBeiDeIn.setCellValue(list.getDailyReportToday().getArriveHuBeiNumber() - list.getDailyReportInYesterday().getArriveHuBeiNumber());
        arriveHuBeiDeIn.setCellStyle(listStyle);
        //我市到过湖北省（除武汉市外）的人员  当日存量
        arriveHuBeiSum.setCellValue(list.getDailyReportToday().getArriveHuBeiNumber());
        arriveHuBeiSum.setCellStyle(listStyle);
        //密切接触者人数  当日增减量
        touchPersonDeIn.setCellValue(list.getDailyReportToday().getTouchHuBeiPersonNumber() - list.getDailyReportInYesterday().getTouchHuBeiPersonNumber());
        touchPersonDeIn.setCellStyle(listStyle);
        //密切接触者人数 当日存量
        touchPersonSum.setCellValue(list.getDailyReportToday().getTouchHuBeiPersonNumber());
        touchPersonSum.setCellStyle(listStyle);
        //我市现在仍在湖北出差、休假、旅游、探亲等短时停留人员  当日增减量
        stayInHubeiDeIn.setCellValue(list.getDailyReportToday().getStayInHubeiNumber() - list.getDailyReportInYesterday().getStayInHubeiNumber());
        stayInHubeiDeIn.setCellStyle(listStyle);
        //我市现在仍在湖北出差、休假、旅游、探亲等短时停留人员  当日存量
        stayInHubeiSum.setCellValue(list.getDailyReportToday().getStayInHubeiNumber());
        stayInHubeiSum.setCellStyle(listStyle);
        //发热人数
        if(list.getDailyReportToday().getFeverNumber()!=null&&list.getDailyReportInYesterday().getFeverNumber()!=null){
            observePeopleDeIn.setCellValue(list.getDailyReportToday().getFeverNumber() - list.getDailyReportInYesterday().getFeverNumber());
            observePeoplesum.setCellValue(list.getDailyReportToday().getFeverNumber());
        }
        observePeopleDeIn.setCellStyle(listStyle);
        observePeoplesum.setCellStyle(listStyle);
        //备注
        intro.setCellValue("");
        intro.setCellStyle(listStyle);

        //签名栏
        XSSFRow footerRow = sheet.createRow(6);
        footerRow.setHeightInPoints(30);//设置行的高度
        XSSFCell footerCell0 = footerRow.createCell(0);
        footerCell0.setCellValue(footer[0]);

        XSSFCell footerCell4 = footerRow.createCell(4);
        footerCell4.setCellValue(footer[1]);

        XSSFCell footerCell8 = footerRow.createCell(8);
        footerCell8.setCellValue(footer[2]);
        return sheet;
    }
}
