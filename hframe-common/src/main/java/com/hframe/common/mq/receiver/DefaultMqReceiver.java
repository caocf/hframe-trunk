package com.hframe.common.mq.receiver;

import com.hframe.common.helper.LogHelper;
import com.hframe.common.mq.MqConfig;
import com.hframe.common.mq.MqMessage;
import com.hframe.common.mq.processor.MqProcessor;
import com.hframe.common.util.StringUtils;
import org.apache.log4j.Logger;

/**
 * User: zhangqh6
 * Date: 2016/3/18 15:34:34
 */
public class DefaultMqReceiver implements MqReceiver{

    private static final Logger logger = Logger.getLogger(DefaultMqReceiver.class);

    private MqConfig mqConfig;
    /**
     * 消息接收
     * @param mqMessage
     * @return
     */
    public void receive(MqMessage mqMessage) {
        logger.debug(LogHelper.begin("MQ消息接收",mqMessage));

        String exchangeName = mqMessage.getExchangeName();
        String queueName = mqMessage.getQueueName();
        Object messageObject = mqMessage.getMessageObject();

        if(StringUtils.isBlank(exchangeName) || StringUtils.isBlank(queueName) || messageObject == null) {
            logger.info(LogHelper.check("MQ消息接收",exchangeName,queueName,messageObject));
            return ;
        }

        MqProcessor process = mqConfig.getProcess(exchangeName, queueName);
        process.process(messageObject);

        logger.debug(LogHelper.end("MQ消息接收", mqMessage));
    }
}
