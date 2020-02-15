package com.lcvc.new_coronavirus_report.model.query;

import com.lcvc.new_coronavirus_report.model.Teacher;

public class TeacherQuery extends Teacher {
    private Boolean reportTodayQuery;//检查当天提交报告的教师的名字

    public Boolean getReportTodayQuery() {
        return reportTodayQuery;
    }

    public void setReportTodayQuery(Boolean reportTodayQuery) {
        this.reportTodayQuery = reportTodayQuery;
    }
}
