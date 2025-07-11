package io.yue.im.sdk.domain.listener;


import io.yue.im.common.domain.model.IMSendResult;

/**
 * @description 消息监听接口
 */
public interface MessageListener<T> {

    /**
     * 处理发送的结果
     */
    void doProcess(IMSendResult<T> result);
}
