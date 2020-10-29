package cn.huanzi.qch.baseadmin.consumer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author create by zhaoxu
 * @create 2020/10/20
 */
@Controller
@RequestMapping("/consumer")
public class consumerPageController {
    @GetMapping("/savePage")
    public ModelAndView savePage(){
        return  new ModelAndView("/save-consumer.html");
    }
    @GetMapping("find")
    public String findList(){
        return "consumer-list";
    }


}
