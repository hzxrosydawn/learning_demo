package com.henry.wechat.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 *
 */
public interface HomeService {

    /**
     * 处理 HttpGet 请求。
     *
     * @param urlWithParams
     * @throws Exception
     */
    void requestGet(String urlWithParams) throws IOException;

    /**
     * 处理 HttpPost 请求。
     *
     * @param url
     * @param params
     * @throws ClientProtocolException
     * @throws IOException
     */
    void requestPost(String url, List<NameValuePair> params) throws ClientProtocolException, IOException;

    /**
     * 刷新消息路由器。
     */
    void refreshRouter();

    /**
     * 路由消息。
     *
     * @param inMessage
     * @return
     */
    WxMpXmlOutMessage route(WxMpXmlMessage inMessage);

    /**
     * 通过openid获得基本用户信息。
     *
     * @param openid 微信用户在该公众号中的openId
     * @param lang
     * @return 微信用户的信息
     */
    WxMpUser getUserInfo(String openid, String lang);

}
