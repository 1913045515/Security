package org.security.controller;

import org.security.entity.Msg;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sang on 2017/1/10.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(Model model) {
        Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "index";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(value="/admin/test",method = RequestMethod.POST)
    @ResponseBody
    public String adminTest1() {
        return "ROLE_USER";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("/auto/test")
    @ResponseBody
    public String adminTest2() {
        return "ROLE_ADMIN";
    }
}
