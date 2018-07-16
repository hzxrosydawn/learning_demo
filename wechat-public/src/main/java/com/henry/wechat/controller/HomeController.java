package com.henry.wechat.controller;

import com.henry.wechat.service.HomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主控制器。
 *
 * @author Vincent
 * Created on 2018/07/16 9:35 PM
 **/
@RequestMapping("/wechat_subscribe")
public class HomeController {
    private final static Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    private HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }


}
