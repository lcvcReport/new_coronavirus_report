package com.lcvc.new_coronavirus_report.service;

import com.lcvc.new_coronavirus_report.model.Strand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StrandServiceTest {
    @Autowired
    private StrandService strandService;

    @Test
    public void testSave(){
        Strand strand =new Strand();
        Date date=new Date();
        date.setTime(2020-02-26);
        strand.setName("xiaom");
        strand.setUnit("柳州城职");
        strand.setCurrentCity("湖北武汉");
        strand.setMyHealth("健康");
        strand.setWorkType("软件技术");
        strand.setReason("因病治疗");
        strand.setReturnLiuZhou(true);
        strand.setReturnLiuZhouTime(date);
        strand.setReturnLiuZhouWay("高铁");
        strand.setIntro("没有说明");
        strand.setCreateTime(new Date());
       strandService.save(strand);
    }
}
