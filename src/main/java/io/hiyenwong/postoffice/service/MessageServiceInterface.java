package io.hiyenwong.postoffice.service;

/**
 * @author Hi Yen Wong
 * @date 5/5/2021 12:49 PM
 */
public interface MessageServiceInterface {
    /**
     * 发送信息
     *
     * @param msg
     * @return
     */
    String send(String msg);

    /**
     * 发送消息给指定对象
     *
     * @param msg
     * @param mentionList
     * @return
     */
    String send(String msg, String... mentionList);
}
