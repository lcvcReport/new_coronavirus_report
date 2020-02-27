package com.lcvc.new_coronavirus_report.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Strand {
    private int id;  //id
    private String unit;  //单位
    private String name;  //名字
    private String currentCity; //当前所在城市，省份加城市
    private String myHealth;    //健康状况，单选列表：健康，发热，咳嗽，发热和咳嗽。
    private String workType;    //承担课程或岗位任课教师填承担课程名称（如：语文、数学，同时兼任的其他工作不需填写），非任课教师填工作岗位（如：办公室干事、保安）。
    private  String reason;   //无法返回原因
    private  Boolean returnLiuZhou;  //本周内是否返柳
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date returnLiuZhouTime;  //计划返柳时间
    private String returnLiuZhouWay;  //计划返柳交通工具
    private  String intro;          //备注
    private Date createTime;  //提交时间



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getMyHealth() {
        return myHealth;
    }

    public void setMyHealth(String myHealth) {
        this.myHealth = myHealth;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getReturnLiuZhou() {
        return returnLiuZhou;
    }

    public void setReturnLiuZhou(Boolean returnLiuZhou) {
        this.returnLiuZhou = returnLiuZhou;
    }


    public Date getReturnLiuZhouTime() {
        return returnLiuZhouTime;
    }

    public void setReturnLiuZhouTime(Date returnLiuZhouTime) {
        this.returnLiuZhouTime = returnLiuZhouTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReturnLiuZhouWay() {
        return returnLiuZhouWay;
    }

    public void setReturnLiuZhouWay(String returnLiuZhouWay) {
        this.returnLiuZhouWay = returnLiuZhouWay;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
