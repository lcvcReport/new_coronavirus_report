package com.lcvc.new_coronavirus_report.web.action.backstage;

import com.lcvc.new_coronavirus_report.model.Admin;
import com.lcvc.new_coronavirus_report.model.base.Constant;
import com.lcvc.new_coronavirus_report.model.base.JsonCode;
import com.lcvc.new_coronavirus_report.model.form.AdminPasswordEditForm;
import com.lcvc.new_coronavirus_report.service.AdminService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PatchMapping("/password")
    public Map<String, Object> updatePassword(@RequestBody @Validated AdminPasswordEditForm adminPasswordEditForm, HttpSession session) {
        Admin admin=((Admin) session.getAttribute("admin"));
        Map<String, Object> map = new HashMap<String, Object>();
        adminService.updatePassword(admin.getUsername(),adminPasswordEditForm.getPassword(),adminPasswordEditForm.getNewPass(),adminPasswordEditForm.getRePass());
        map.put(Constant.JSON_CODE, JsonCode.SUCCESS.getValue());
        map.put(Constant.JSON_MESSAGE, "密码修改成功");
        return map;
    }

}
