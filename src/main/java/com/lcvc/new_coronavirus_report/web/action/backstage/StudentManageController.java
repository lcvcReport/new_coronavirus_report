package com.lcvc.new_coronavirus_report.web.action.backstage;

import com.lcvc.new_coronavirus_report.model.base.Constant;
import com.lcvc.new_coronavirus_report.model.base.JsonCode;
import com.lcvc.new_coronavirus_report.model.base.PageObject;
import com.lcvc.new_coronavirus_report.model.query.QuestionnaireQuery;
import com.lcvc.new_coronavirus_report.model.query.StudentQuery;
import com.lcvc.new_coronavirus_report.model.query.TeacherQuery;
import com.lcvc.new_coronavirus_report.service.StudentService;
import com.lcvc.new_coronavirus_report.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/backstage/student")
public class StudentManageController {

    @Autowired
    private StudentService studentService;

    //分页查询学生记录
    @GetMapping
    public Map<String, Object> studentManage(Integer page, Integer limit, StudentQuery studentQuery){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put(Constant.JSON_CODE, JsonCode.SUCCESS.getValue());
        PageObject pageObject =studentService.query(page,limit,studentQuery);
        map.put(Constant.JSON_TOTAL,pageObject.getTotalRecords());
        map.put(Constant.JSON_DATA,pageObject.getList());
        return map;
    }


}
