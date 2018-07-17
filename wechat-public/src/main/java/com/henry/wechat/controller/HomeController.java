package com.henry.wechat.controller;

import com.henry.wechat.service.WeChatService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 主控制器。
 *
 * @author Vincent
 * Created on 2018/07/16 9:35 PM
 **/
@RequestMapping("/wechat/subscription")
@Controller
public class HomeController {
    private final static Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    private WeChatService weChatService;

    @Autowired
    public HomeController(WeChatService weChatService) {
        this.weChatService = weChatService;
    }

    @RequestMapping("/subscription")
    public String isRequestFromWeChat() {

        return null;
    }

    /**
     * 校验签名信息是否来自微信公众平台。
     *
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机字符串
     * @return
     */
    @ResponseBody
    @GetMapping(produces = "text/plain;charset=utf-8")
    public String isRequestFromWeChat(@RequestParam(name = "signature", required = false) String signature,
                                      @RequestParam(name = "timestamp", required = false) String timestamp,
                                      @RequestParam(name = "nonce", required = false) String nonce,
                                      @RequestParam(name = "echostr", required = false) String echostr) {
        LOGGER.info("接收到微信服务器的认证消息：[{}, {}, {}, {}]", new String[] {signature, timestamp, nonce, echostr});

        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求的签名参数非法，请核实!");
        }

        if (weChatService.isRequestFromWeChat(timestamp, nonce, signature)) {
            return echostr;
        }

        return "非法请求";
    }
}
