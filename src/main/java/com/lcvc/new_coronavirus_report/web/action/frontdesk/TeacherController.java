package com.lcvc.new_coronavirus_report.web.action.frontdesk;

import com.lcvc.new_coronavirus_report.model.Questionnaire;
import com.lcvc.new_coronavirus_report.model.Teacher;
import com.lcvc.new_coronavirus_report.model.base.Constant;
import com.lcvc.new_coronavirus_report.model.base.JsonCode;
import com.lcvc.new_coronavirus_report.service.AdminService;
import com.lcvc.new_coronavirus_report.service.QuestionnaireService;
import com.lcvc.new_coronavirus_report.service.TeacherService;
import com.lcvc.new_coronavirus_report.util.date.MyDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/frontdesk/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private QuestionnaireService questionnaireService;


    //读取指定教师信息，并读取上一次问题的选项（如果存在的话）
    @GetMapping("/{teacherNumber}")
    public Map<String, Object> doPostQuestionnaire(@PathVariable String teacherNumber){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put(Constant.JSON_CODE, JsonCode.SUCCESS.getValue());
        Teacher teacher=teacherService.get(teacherNumber);
        if(teacher!=null){//如果能够找到记录
            Questionnaire questionnaire=questionnaireService.getQuestionnaire("teacher",teacherNumber,new Date());//获取当天的投票记录
            if(questionnaire==null){//没有填报过
                map.put(Constant.JSON_DATA,teacher);
            }else{//已经填报过
                map.put(Constant.JSON_CODE, JsonCode.ERROR.getValue());
                map.put(Constant.JSON_MESSAGE, "今天已经提交过调查表，请明天再来");
            }
        }

        map.put(Constant.JSON_DATA,teacher);
        return map;
    }

}
