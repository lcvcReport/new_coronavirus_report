package com.lcvc.new_coronavirus_report.dao;

import com.lcvc.new_coronavirus_report.model.Strand;
import org.springframework.stereotype.Repository;

@Repository//为了不让idea报错加上
public interface StrandQuestionnaireDao extends IBaseDao<Strand>{
    /**
     * 滞留疫区无法返回教职工的数量
     * @param teacherNumber
     * @return 返回整数
     */
    int getteacherSrandQuestionnaireNumber(String teacherNumber);
}
