package com.lcvc.new_coronavirus_report.model;


public class StrandCount {
    private  int id; //序号
    private String unit;  //单位
    private String schoolSection;  //教师人数
    private int teacherNum;  //教师人数
    private int strandNum;  //滞留疫区无法返回教职工人数
    private int returnLiuZhouNum;  //本周内预计返柳人数
    private String intro;  //备注

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

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

    public String getSchoolSection() {
        return schoolSection;
    }

    public void setSchoolSection(String schoolSection) {
        this.schoolSection = schoolSection;
    }

    public int getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(int teacherNum) {
        this.teacherNum = teacherNum;
    }

    public int getStrandNum() {
        return strandNum;
    }

    public void setStrandNum(int strandNum) {
        this.strandNum = strandNum;
    }

    public int getReturnLiuZhouNum() {
        return returnLiuZhouNum;
    }

    public void setReturnLiuZhouNum(int returnLiuZhouNum) {
        this.returnLiuZhouNum = returnLiuZhouNum;
    }
}
