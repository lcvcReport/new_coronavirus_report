package com.lcvc.new_coronavirus_report.dao;

import com.lcvc.new_coronavirus_report.model.Student;
import com.lcvc.new_coronavirus_report.model.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//为了不让idea报错加上
public interface TeacherDao extends IBaseDao<Teacher>{

    /**
     * 获取今天还没有投票的教师信息
     * @param department 指定部门，如果为null或为空，则查询所有部门
     */
    List<Teacher> getTeacherNotReportToday(@Param(value = "department")String department);

}
