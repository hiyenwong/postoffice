package io.hiyenwong.postoffice.dingtalk.service;

import io.hiyenwong.postoffice.service.MessageServiceInterface;

/**
 * @author Hi Yen Wong
 * @date 5/5/2021 12:48 PM
 */
public class DingTalkServiceImp implements MessageServiceInterface {
    /**
     * 发送信息
     *
     * @param msg
     * @return
     */
    @Override
    public String send(String msg) {
        return null;
    }

    /**
     * 发送消息给指定对象
     *
     * @param msg
     * @param mentionList
     * @return
     */
    @Override
    public String send(String msg, String... mentionList) {
        return null;
    }
}
