package com.idiotalex.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/11/15.
 */
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private String consumerEnableAutoCommit;

    @Value("${spring.kafka.consumer.max-poll-records}")
    private String consumerMaxPollRecords;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String consumerAutoOffsetReset;

    @Bean
    public ConsumerFactory<Object, Object> consumerFactory(){
        Map<String, Object> configs = new HashMap<>(); //参数
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, consumerEnableAutoCommit);
        configs.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, consumerMaxPollRecords); //批量消费数量
        configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumerAutoOffsetReset);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(configs);
    }

    @Bean
    public KafkaListenerContainerFactory<?> batchContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<Object, Object> containerFactory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
        containerFactory.setConsumerFactory(consumerFactory());
        containerFactory.setConcurrency(4);
        containerFactory.setBatchListener(true); //批量消费
        containerFactory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL_IMMEDIATE);

        return containerFactory;
    }
}
