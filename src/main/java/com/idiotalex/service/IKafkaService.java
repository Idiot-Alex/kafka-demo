package com.idiotalex.service;

public interface IKafkaService {

    // 发送消息
    void sendMessage(String topic, String message);
}
