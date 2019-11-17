package com.idiotalex;

import org.junit.Test;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.Resource;

public class KafkaTest extends AppTest {

    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @Test
    public void testSendKafkaMessage() {
        kafkaTemplate.send("test", "hello world");
        logger.info("发送消息完成...");
    }
}
