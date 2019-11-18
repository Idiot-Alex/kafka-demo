package com.idiotalex.web;

import com.alibaba.fastjson.JSON;
import com.idiotalex.model.Message;
import com.idiotalex.model.R;
import com.idiotalex.service.IKafkaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Administrator on 2019/11/18.
 */
@RestController
public class MessageController {
    private static Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Resource
    private IKafkaService kafkaService;

    // 发送消息
    @PostMapping(value = "/message/send")
    public Object sendMessage(String message){
        if (!StringUtils.isEmpty(message)) {
            Message info = new Message();
            info.setId(System.currentTimeMillis());
            info.setMsg(message);
            info.setCreateTime(new Date());
            kafkaService.sendMessage("test", JSON.toJSONString(info));
        }
        return R.ok("发送成功");
    }
}
