package com.lcvc.new_coronavirus_report.service;

import com.lcvc.new_coronavirus_report.model.Questionnaire;
import com.lcvc.new_coronavirus_report.model.query.QuestionnaireQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionnaireServiceTest {

    @Autowired
    private QuestionnaireService questionnaireService;

    @Test
    public void testSave(){
        Questionnaire questionnaire=new Questionnaire();
        //根据业务逻辑一个一个字段进行验证
        //录入各个字段信息进行保存
        questionnaire.setStayInHubei(true);
        questionnaire.setIdentity("teacher");
        questionnaire.setTeacherNumber("2007010006");
        questionnaire.setName("历史");
        questionnaire.setWorkType("专任教师");
        questionnaire.setMyHealth("健康");
        questionnaire.setTouchHuBeiPerson(true);
        questionnaire.setEpidemicArea("武汉不知道那里");
        questionnaire.setConfirmIll(true);
        questionnaire.setComefromHuBei(true);
        questionnaire.setComefromWuHan(true);
        questionnaire.setArriveHuBei(true);
        questionnaire.setArriveWuHan(true);
        questionnaire.setLeaveHubei(new Date());
        questionnaire.setLeaveHubeiWay("wu");
        questionnaire.setLeaveLiuZhou(new Date());
        questionnaire.setRegisteredPlace("五星街");
        questionnaire.setMyfamilyHealth("健康");
        questionnaire.setSex("男");
        questionnaire.setTouchHuBeiTime(new Date());
        questionnaire.setIdentityCard("111112222233333111");
        questionnaire.setTel("17878011321");
        questionnaire.setPractice(true);
        questionnaire.setTouchHuBeiDescription("就这样");
        questionnaire.setManageMethods("管理方式很严格哦");
        questionnaire.setArriveLiuZhou(new Date());
        questionnaire.setLeaveLiuZhou(new Date());
        questionnaire.setLeaveTogetherPersonName("zhangzhang");
        questionnaire.setIntro("wuwuw");
        questionnaire.setIp("123.1.1");
        questionnaire.setAddressInLiuZhou("鱼峰区");

        questionnaireService.save(questionnaire,"192.142.11.9");//保存后去数据库检查相关字段是否有值
        //questionnaireDao.save(questionnaire);//保存后去数据库检查相关字段是否有值
    }

    @Test
    public void testReadAll(){
        QuestionnaireQuery questionnairequery=new QuestionnaireQuery();
        //questionnairequery.setQueryDate(new Date());//查找今天的表内容
        questionnairequery.setTeacherQuery(true);//查询教师信息
        questionnairequery.setDepartment("信息工程系");//加入部门条件
        List<Questionnaire> list=questionnaireService.query(questionnairequery);//获取数据记录
        System.out.println(list.size());
    }

}
