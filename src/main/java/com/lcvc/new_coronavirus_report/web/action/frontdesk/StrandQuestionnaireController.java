package com.lcvc.new_coronavirus_report.web.action.frontdesk;

import com.lcvc.new_coronavirus_report.model.Strand;
import com.lcvc.new_coronavirus_report.model.base.Constant;
import com.lcvc.new_coronavirus_report.model.base.JsonCode;
import com.lcvc.new_coronavirus_report.service.StrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/frontdesk/strandQuestionnaire")
public class StrandQuestionnaireController {

    @Autowired
    private StrandService strandService;

    @PostMapping
    public Map<String, Object> save(@RequestBody Strand strand) {
        Map<String, Object> map = new HashMap<String, Object>();
        strandService.save(strand);
        map.put(Constant.JSON_CODE, JsonCode.SUCCESS.getValue());
        return map;
    }

}
