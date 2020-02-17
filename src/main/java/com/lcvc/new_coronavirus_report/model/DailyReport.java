package com.lcvc.new_coronavirus_report.model;

import java.sql.Date;

/**
 * 重点人群排查管理日报表
 */
public class DailyReport {
    private Integer id;//自动编号
    private Integer comefromWuHanNumber;//来自武汉市的市外人员人数
    private Integer comefromHuBeiNumber;//来自湖北省（除武汉市外）的市外人员人数
    private Integer arriveHuBeiNumber;//我市到过武汉市的人员人数
    private Integer arriveWuHanNumber;//我市到过湖北省（除武汉市外）的人员人数
    private Integer touchHuBeiPersonNumber;//密切接触者过疫区人员的人数（不是指疫区人员人数）
    private Integer stayInHubeiNumber;//我市现在仍在湖北出差、休假、旅游、探亲等短时停留人员的人数
    private Integer feverNumber;//发热的人数
    private Date reportDate;//该报告的当天日期。（记录的当天数据）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getComefromWuHanNumber() {
        return comefromWuHanNumber;
    }

    public void setComefromWuHanNumber(Integer comefromWuHanNumber) {
        this.comefromWuHanNumber = comefromWuHanNumber;
    }

    public Integer getComefromHuBeiNumber() {
        return comefromHuBeiNumber;
    }

    public void setComefromHuBeiNumber(Integer comefromHuBeiNumber) {
        this.comefromHuBeiNumber = comefromHuBeiNumber;
    }

    public Integer getArriveHuBeiNumber() {
        return arriveHuBeiNumber;
    }

    public void setArriveHuBeiNumber(Integer arriveHuBeiNumber) {
        this.arriveHuBeiNumber = arriveHuBeiNumber;
    }

    public Integer getArriveWuHanNumber() {
        return arriveWuHanNumber;
    }

    public void setArriveWuHanNumber(Integer arriveWuHanNumber) {
        this.arriveWuHanNumber = arriveWuHanNumber;
    }

    public Integer getTouchHuBeiPersonNumber() {
        return touchHuBeiPersonNumber;
    }

    public void setTouchHuBeiPersonNumber(Integer touchHuBeiPersonNumber) {
        this.touchHuBeiPersonNumber = touchHuBeiPersonNumber;
    }

    public Integer getStayInHubeiNumber() {
        return stayInHubeiNumber;
    }

    public void setStayInHubeiNumber(Integer stayInHubeiNumber) {
        this.stayInHubeiNumber = stayInHubeiNumber;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Integer getFeverNumber() {
        return feverNumber;
    }

    public void setFeverNumber(Integer feverNumber) {
        this.feverNumber = feverNumber;
    }
}
