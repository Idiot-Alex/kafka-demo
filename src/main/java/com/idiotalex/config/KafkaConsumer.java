package com.idiotalex.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.idiotalex.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2019/11/15.
 * Kafka 消费
 */
@Component
public class KafkaConsumer {
    private static Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    /**
     * kafka 消息消费
     * @param data
     */
    @KafkaListener(topics = {"test"})
    public void consumerAgentTask(String data) {
        logger.info("receive message: {}", data);
        if (StringUtils.isEmpty(data)){
            logger.error("kafka consumer message is empty...data: {}", data);
            return;
        }
        try {
            Message message = JSON.parseObject(data, Message.class);
            if (message == null) {
                logger.error("kafka consumer message is invalid...data: {}", data);
            }
            // 消费消息
            logger.info("模拟消费消息...message: {}", JSON.toJSONString(message));
        } catch (JSONException e) {
            logger.error("kafka consumer message is invalid...data: {}", data);
        }
    }

}
