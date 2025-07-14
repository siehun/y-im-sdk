package io.yue.im.sdk.client;

import io.yue.im.common.domain.enums.IMTerminalType;
import io.yue.im.common.domain.model.IMGroupMessage;
import io.yue.im.common.domain.model.IMPrivateMessage;

import java.util.List;
import java.util.Map;

/**
 * @description IM客户端
 */
public interface IMClient {
    /**
     * 发送私聊消息
     */
    <T> void sendPrivateMessage(IMPrivateMessage<T> message);

    /**
     * 发送群消息
     */
    <T> void sendGroupMessage(IMGroupMessage<T> message);

    /**
     * 判断用户是否在线
     */
    Boolean isOnline(Long userId);

    /**
     * 筛选出在线的用户
     */
    List<Long> getOnlineUserList(List<Long> userIds);

    /**
     * 获取用户与其在线的终端列表
     */
    Map<Long,List<IMTerminalType>> getOnlineTerminal(List<Long> userIds);
}
