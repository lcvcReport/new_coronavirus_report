package com.lcvc.new_coronavirus_report.model.query;

import com.lcvc.new_coronavirus_report.model.Strand;

public class StrandQuery extends Strand {
    private Boolean returnLiuZhouQuery;//是否查询本周返回柳州记录
    private  Boolean weekQuery;//是否查询本周全部的记录

    public Boolean getReturnLiuZhouQuery() {
        return returnLiuZhouQuery;
    }

    public void setReturnLiuZhouQuery(Boolean returnLiuZhouQuery) {
        this.returnLiuZhouQuery = returnLiuZhouQuery;
    }

    public Boolean getWeekQuery() {
        return weekQuery;
    }

    public void setWeekQuery(Boolean weekQuery) {
        this.weekQuery = weekQuery;
    }
}
