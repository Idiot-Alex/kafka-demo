package com.idiotalex.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class KafkaServiceImpl implements IKafkaService {
    private static Logger logger = LoggerFactory.getLogger(KafkaServiceImpl.class);

    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @Override
    public void sendMessage(String topic, String message) {
        if (StringUtils.isEmpty(message)) {
            logger.error("message is empty...");
            return;
        }
        kafkaTemplate.send(topic, message);
    }
}
