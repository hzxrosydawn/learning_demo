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
    private static final Logger logger = LoggerFactory.getLogger(WeChatServiceImpl.class);
    private static final  String token = "";

    @Override
    public boolean isRequestFromWeChat(String timestamp, String nonce, String signature) {
        return SignUtil.validateSignature(timestamp, timestamp, nonce, signature);
    }
}
