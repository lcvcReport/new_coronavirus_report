package com.lcvc.new_coronavirus_report.web.action.backstage;

import com.lcvc.new_coronavirus_report.model.Strand;
import com.lcvc.new_coronavirus_report.model.StrandCount;
import com.lcvc.new_coronavirus_report.model.base.Constant;
import com.lcvc.new_coronavirus_report.model.base.JsonCode;
import com.lcvc.new_coronavirus_report.model.base.PageObject;
import com.lcvc.new_coronavirus_report.model.query.StrandQuery;
import com.lcvc.new_coronavirus_report.service.StrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/backstage/query")
public class StrandController {

    @Autowired
    private StrandService strandService;



    //滞留疫区无法返回教职工信息表
    @GetMapping("strand")
    public Map<String, Object> stand(Integer page, Integer limit){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put(Constant.JSON_CODE, JsonCode.SUCCESS.getValue());
        StrandQuery strandQuery=new StrandQuery();
        strandQuery.setWeekQuery(true);
        PageObject pageObject = strandService.query(page,limit,strandQuery);
        map.put(Constant.JSON_TOTAL,pageObject.getTotalRecords());
        map.put(Constant.JSON_DATA,pageObject.getList());
        return map;
    }

    //滞留疫区无法返回教职工情况统计
    @GetMapping("strandCount")
    public Map<String, Object> strandCount(){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put(Constant.JSON_CODE, JsonCode.SUCCESS.getValue());
        StrandCount strandCount = strandService.queryCount();
        map.put(Constant.JSON_DATA, strandCount);
        return map;
    }

}
