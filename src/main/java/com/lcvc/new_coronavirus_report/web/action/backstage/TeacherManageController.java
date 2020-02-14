package com.lcvc.new_coronavirus_report.web.action.backstage;

import com.lcvc.new_coronavirus_report.model.Admin;
import com.lcvc.new_coronavirus_report.model.Questionnaire;
import com.lcvc.new_coronavirus_report.model.base.Constant;
import com.lcvc.new_coronavirus_report.model.base.JsonCode;
import com.lcvc.new_coronavirus_report.model.base.PageObject;
import com.lcvc.new_coronavirus_report.model.query.QuestionnaireQuery;
import com.lcvc.new_coronavirus_report.model.query.TeacherQuery;
import com.lcvc.new_coronavirus_report.service.AdminService;
import com.lcvc.new_coronavirus_report.service.TeacherService;
import com.lcvc.new_coronavirus_report.util.poi.ExcelWirteForTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/backstage/teacher")
public class TeacherManageController {

    @Autowired
    private TeacherService teacherService;

    //分页查询教师记录
    @GetMapping
    public Map<String, Object> teacherManage(Integer page, Integer limit, TeacherQuery teacherQuery){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put(Constant.JSON_CODE, JsonCode.SUCCESS.getValue());
        PageObject pageObject =teacherService.query(page,limit,teacherQuery);
        map.put(Constant.JSON_TOTAL,pageObject.getTotalRecords());
        map.put(Constant.JSON_DATA,pageObject.getList());
        return map;
    }


}
