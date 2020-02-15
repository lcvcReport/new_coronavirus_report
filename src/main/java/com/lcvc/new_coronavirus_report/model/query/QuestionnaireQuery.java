package com.lcvc.new_coronavirus_report.model.query;

import com.lcvc.new_coronavirus_report.model.Questionnaire;

import java.util.Date;

/**
 * 负责接收调查问卷表单
 * 统计1月16日后的信息
 */
public class QuestionnaireQuery extends Questionnaire {
    private Date queryDate;//当天日期，作为查询的条件（即查询当天的情况）
    private Boolean arriveHuBeiOrWuHanQuery;//1月16日后，是否去过湖北(包括武汉市)。
    private Boolean studentQuery;//查询学生信息
    private Boolean teacherQuery;//查询教师信息
    private Boolean myIllQuery;//查询本人是否生病
    private Boolean feverQuery;//查询是否发热

    public Boolean getFeverQuery() {
        return feverQuery;
    }

    public void setFeverQuery(Boolean feverQuery) {
        this.feverQuery = feverQuery;
    }

    public Date getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(Date queryDate) {
        this.queryDate = queryDate;
    }

    public Boolean getArriveHuBeiOrWuHanQuery() {
        return arriveHuBeiOrWuHanQuery;
    }

    public Boolean getStudentQuery() {
        return studentQuery;
    }

    public void setStudentQuery(Boolean studentQuery) {
        this.studentQuery = studentQuery;
    }

    public Boolean getTeacherQuery() {
        return teacherQuery;
    }

    public void setTeacherQuery(Boolean teacherQuery) {
        this.teacherQuery = teacherQuery;
    }

    public void setArriveHuBeiOrWuHanQuery(Boolean arriveHuBeiOrWuHanQuery) {
        this.arriveHuBeiOrWuHanQuery = arriveHuBeiOrWuHanQuery;
    }

    public Boolean getMyIllQuery() {
        return myIllQuery;
    }

    public void setMyIllQuery(Boolean myIllQuery) {
        this.myIllQuery = myIllQuery;
    }
}
