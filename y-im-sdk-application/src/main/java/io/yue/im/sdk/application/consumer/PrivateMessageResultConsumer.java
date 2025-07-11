package io.yue.im.sdk.application.consumer;

import cn.hutool.core.util.StrUtil;
import io.yue.im.common.domain.constants.IMConstants;
import io.yue.im.common.domain.enums.IMListenerType;
import io.yue.im.common.domain.model.IMSendResult;
import io.yue.im.sdk.infrastruture.multicaster.MessageListenerMulticaster;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @description 接收私聊消息的结果数据
 */
@Component
@ConditionalOnProperty(name = "message.mq.type", havingValue = "rocketmq")
@RocketMQMessageListener(consumerGroup = IMConstants.IM_RESULT_PRIVATE_CONSUMER_GROUP, topic = IMConstants.IM_RESULT_PRIVATE_QUEUE)
public class PrivateMessageResultConsumer extends BaseMessageResultConsumer implements RocketMQListener<String> {

    private final Logger logger = LoggerFactory.getLogger(PrivateMessageResultConsumer.class);

    @Autowired
    private MessageListenerMulticaster messageListenerMulticaster;

    @Override
    public void onMessage(String message) {
        if (StrUtil.isEmpty(message)){
            logger.warn("PrivateMessageResultConsumer.onMessage|接收到的消息为空");
            return;
        }
        IMSendResult<?> imSendResult = this.getResultMessage(message);
        if (imSendResult == null){
            logger.warn("PrivateMessageResultConsumer.onMessage|转化后的数据为空");
            return;
        }
        messageListenerMulticaster.multicast(IMListenerType.PRIVATE_MESSAGE, imSendResult);
    }

}
