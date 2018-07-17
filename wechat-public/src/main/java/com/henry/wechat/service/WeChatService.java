package com.henry.wechat.service;

/**
 *
 */
public interface WeChatService {
    boolean isRequestFromWeChat(String timestamp, String nonce, String signature);
}
