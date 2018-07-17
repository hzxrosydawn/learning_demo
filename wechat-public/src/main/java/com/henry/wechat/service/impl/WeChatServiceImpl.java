package com.henry.wechat.service.impl;

import com.henry.wechat.service.WeChatService;
import com.henry.wechat.util.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class WeChatServiceImpl implements WeChatService {
    private Logger logger = LoggerFactory.getLogger(getClass());



    @Override
    public boolean isRequestFromWeChat(String timestamp, String nonce, String signature) {
        return SignUtil.validateSignature(timestamp, timestamp, nonce, signature);
    }
}
