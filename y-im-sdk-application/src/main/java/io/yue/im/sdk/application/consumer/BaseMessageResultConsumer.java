package io.yue.im.sdk.application.consumer;

import com.alibaba.fastjson.JSONObject;
import io.yue.im.common.domain.constants.IMConstants;
import io.yue.im.common.domain.model.IMSendResult;

/**
 * @description 基础结果消费者类
 */
public class BaseMessageResultConsumer {

    /**
     * 解析数据
     */
    protected IMSendResult<?> getResultMessage(String msg){
        JSONObject jsonObject = JSONObject.parseObject(msg);
        String eventStr = jsonObject.getString(IMConstants.MSG_KEY);
        return JSONObject.parseObject(eventStr, IMSendResult.class);
    }
}
