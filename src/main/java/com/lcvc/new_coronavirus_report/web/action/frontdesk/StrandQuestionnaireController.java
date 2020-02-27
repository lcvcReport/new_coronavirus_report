package com.lcvc.new_coronavirus_report.web.action.frontdesk;

import com.lcvc.new_coronavirus_report.model.Questionnaire;
import com.lcvc.new_coronavirus_report.model.Strand;
import com.lcvc.new_coronavirus_report.model.Teacher;
import com.lcvc.new_coronavirus_report.model.base.Constant;
import com.lcvc.new_coronavirus_report.model.base.JsonCode;
import com.lcvc.new_coronavirus_report.service.StrandService;
import com.lcvc.new_coronavirus_report.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/frontdesk/")
public class StrandQuestionnaireController {

    @Autowired
    private StrandService strandService;
    @Autowired
    private TeacherService teacherService;


    @PostMapping("strandQuestionnaire")
    public Map<String, Object> save(@RequestBody Strand strand) {
        Map<String, Object> map = new HashMap<String, Object>();
        strandService.save(strand);
        map.put(Constant.JSON_CODE, JsonCode.SUCCESS.getValue());
        return map;
    }


    //读取指定教师信息，并读取上一次问题的选项（如果存在的话）
    @GetMapping("strand/{teacherNumber}")
    public Map<String, Object> teacherNumber1(@PathVariable String teacherNumber){
        Map<String, Object> map=new HashMap<String, Object>();

        Teacher teacher=teacherService.get(teacherNumber);
        if (teacher!=null){
            Boolean stutas=strandService.getStrandQuestionnaire(teacher.getName());
            if (!stutas){
                map.put(Constant.JSON_CODE, JsonCode.ERROR.getValue());
                map.put(Constant.JSON_MESSAGE, "本周已经提交过调查表，请下周再来");
            }else{
                map.put(Constant.JSON_CODE, JsonCode.SUCCESS.getValue());
                map.put(Constant.JSON_DATA,teacher);
            }
        }else{
            map.put(Constant.JSON_CODE, JsonCode.ERROR.getValue());
            map.put(Constant.JSON_MESSAGE, "教师号不正确");
        }
        return map;
    }
}
