package com.lcvc.new_coronavirus_report.dao;

import com.lcvc.new_coronavirus_report.model.query.TeacherQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherDaoTest{

    @Autowired
    private TeacherDao teacherDao;

    @Test
    public void testGet(){
        System.out.println(teacherDao.get("2006010018").getName());
    }

    @Test
    public void testQuery(){
        TeacherQuery teacherQuery=new TeacherQuery();
        //teacherQuery.setReportTodayQuery(false);
        teacherQuery.setDepartment("信息工程系");
        System.out.println(teacherDao.query(0,1000,teacherQuery).size());
    }


   /* @Test
    public void testGetTeacherNotReportToday(){
        System.out.println(teacherDao.getTeacherNotReportToday("").size());
    }*/

}
