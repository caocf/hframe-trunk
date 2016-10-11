package com.hframework.common.service;


import com.hframework.ext.template.DynamicKafkaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Created by zhangquanhong on 2016/10/10.
 */
public class KafkaService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaService.class);

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    public void sendMessage(String topic, String data) {
        logger.info("the message is to be send by kafka is : topic = {}, data = {}", topic, data);
//        kafkaTemplate.setDefaultTopic(topic);
        kafkaTemplate.send(topic, data);
    }

    public void sendMessage(String topic, int key, String data) {
        logger.info("the message is to be send by kafka is : topic = {}, key = {}, data = {}", topic, key, data);
        kafkaTemplate.send(topic, (Integer) null, key, data);
    }

    public void sendMessage(String server, String topic, String data) {
        logger.info("the message is to be send by kafka is : server = {}, topic = {}, data = {}", server, topic, data);
        if (kafkaTemplate instanceof DynamicKafkaTemplate) {
            DynamicKafkaTemplate template = (DynamicKafkaTemplate) kafkaTemplate;
            template.send(server,topic, data);
        }else {
            logger.warn("can't build [{}] 's producer : " +
                    "this kafkaTemplate must be the com.hframework.ext.template.DynamicKafkaTemplate instance " +
                    ",current is {} instance !",server, kafkaTemplate.getClass());
        }
    }

    public void sendMessage(String server, String topic, int key, String data) {
        logger.info("the message is to be send by kafka is : server = {}, topic = {}, key = {}, data = {}", server, topic, key, data);
        if (kafkaTemplate instanceof DynamicKafkaTemplate) {
            DynamicKafkaTemplate template = (DynamicKafkaTemplate) kafkaTemplate;
            template.send(server,topic, key, data);
        }else {
            logger.warn("can't build [{}] 's producer : " +
                    "this kafkaTemplate must be the com.hframework.ext.template.DynamicKafkaTemplate instance " +
                    ",current is {} instance !",server, kafkaTemplate.getClass());
        }
    }
}
