package com.idiotalex;

import com.alibaba.fastjson.JSON;
import com.idiotalex.model.Message;
import org.junit.Test;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.Resource;
import java.util.Date;

public class KafkaTest extends AppTest {

    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @Test
    public void testSendKafkaMessage() {
        Message message = new Message();
        message.setId(1L);
        message.setMsg("hello world");
        message.setCreateTime(new Date());
        kafkaTemplate.send("test", JSON.toJSONString(message));
        logger.info("发送消息完成...");
    }
}
