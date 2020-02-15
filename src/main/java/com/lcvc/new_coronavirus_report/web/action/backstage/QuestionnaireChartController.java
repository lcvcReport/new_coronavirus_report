package com.lcvc.new_coronavirus_report.web.action.backstage;

import com.lcvc.new_coronavirus_report.model.base.Constant;
import com.lcvc.new_coronavirus_report.model.base.JsonCode;
import com.lcvc.new_coronavirus_report.service.ChartService;
import com.lcvc.new_coronavirus_report.service.DailyReportService;
import com.lcvc.new_coronavirus_report.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * 专门用于展示图表数据
 */
@RestController
@RequestMapping(value = "/api/backstage/chart")
public class QuestionnaireChartController {

    @Autowired
    private QuestionnaireService questionnaireService;
    @Autowired
    private DailyReportService dailyReportService;
    @Autowired
    private ChartService chartService;

    //获取各类填报人数的比例，后续会改为只显示当天
    @GetMapping("/rePortPersonProportion")
    public Map<String, Object> dailyReport(){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put(Constant.JSON_CODE, JsonCode.SUCCESS.getValue());
        map.put(Constant.JSON_DATA,chartService.getRePortPersonProportion());
        return map;
    }

    //获取近几天生病的人数
    @GetMapping("/myIll")
    public Map<String, Object> myIll(){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put(Constant.JSON_CODE, JsonCode.SUCCESS.getValue());
        map.put(Constant.JSON_DATA,chartService.getMyIllRecently(5));
        return map;
    }


    //获取近几天来自武汉的人数
    @GetMapping("/comeFromWuHanRecently")
    public Map<String, Object> comeFromWuHanRecently(){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put(Constant.JSON_CODE, JsonCode.SUCCESS.getValue());
        map.put(Constant.JSON_DATA,chartService.getFromWuHanRecently(5));
        return map;
    }

    //获取近几天来自湖北的人数
    @GetMapping("/comeFromHuBeiRecently")
    public Map<String, Object> comeFromHuBeiRecently(){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put(Constant.JSON_CODE, JsonCode.SUCCESS.getValue());
        map.put(Constant.JSON_DATA,chartService.getFromHuBeiRecently(5));
        return map;
    }
    //获取近几天去过武汉的人数
    @GetMapping("/arriveWuHanRecently")
    public Map<String, Object> arriveWuHanRecently(){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put(Constant.JSON_CODE, JsonCode.SUCCESS.getValue());
        map.put(Constant.JSON_DATA,chartService.getArriveWuHanRecently(5));
        return map;
    }

    //获取近几天去过湖北的人数
    @GetMapping("/arriveHuBeiRecently")
    public Map<String, Object> arriveHuBeiRecently(){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put(Constant.JSON_CODE, JsonCode.SUCCESS.getValue());
        map.put(Constant.JSON_DATA,chartService.getArriveHuBeiRecently(5));
        return map;
    }

    //获取近几天停留在湖北的人数
    @GetMapping("/stayHuBeiRecently")
    public Map<String, Object> stayHuBeiRecently(){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put(Constant.JSON_CODE, JsonCode.SUCCESS.getValue());
        map.put(Constant.JSON_DATA,chartService.getStayHubeiRecently(5));
        return map;
    }

    //获取近几天在接触疫区人员的人数
    @GetMapping("/touchPerson")
    public Map<String, Object> touchPerson(){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put(Constant.JSON_CODE, JsonCode.SUCCESS.getValue());
        map.put(Constant.JSON_DATA,chartService.getTouchHuBeiPersonRecently(5));
        return map;
    }


    //获取近几天在单位上岗的学生人数
    @GetMapping("/practiceWorkStudentRecently")
    public Map<String, Object> practiceWorkStudentRecently(){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put(Constant.JSON_CODE, JsonCode.SUCCESS.getValue());
        map.put(Constant.JSON_DATA,chartService.getPracticeWorkStudentRecently(7));
        return map;
    }

}
