package com.hframework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * User: zhangqh6
 * Date: 2016/5/22 16:18:18
 */
@Controller
@RequestMapping(value = "/")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @RequestMapping(value = "/demo.html")
    public ModelAndView gotoLoginPage(@ModelAttribute("account") String account, @ModelAttribute("password") String password){
        logger.debug("request : {}",account, password);
        ModelAndView mav = new ModelAndView();
        mav.addObject("account", account);
        mav.addObject("password", password);
        mav.setViewName("/demo");
        return mav;
    }
}
