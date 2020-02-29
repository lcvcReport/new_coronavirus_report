package com.lcvc.new_coronavirus_report.dao;

import com.lcvc.new_coronavirus_report.model.Strand;
import com.lcvc.new_coronavirus_report.service.StrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StrandDaoTest {
    @Autowired
    private StrandQuestionnaireDao strandQuestionnaireDao;

    @Test
    public void testSave(){
        Strand strand =new Strand();
        strand.setName("南宁");
        strand.setUnit("柳州城职");
        strand.setCurrentCity("湖北武汉");
        strand.setMyHealth("健康");
        strand.setWorkType("软件技术");
        strand.setReason("因病治疗");
        strand.setReturnLiuZhou(true);
        strand.setReturnLiuZhouTime(new Date());
        strand.setReturnLiuZhouWay("高铁");
        strand.setIntro("没有说明");
        strand.setCreateTime(new Date());
        strandQuestionnaireDao.save(strand);
    }
}
