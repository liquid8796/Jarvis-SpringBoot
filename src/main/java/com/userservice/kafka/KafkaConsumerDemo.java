package com.userservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Component
public class KafkaConsumerDemo {

    @Value("${kafka.bootstrap-servers}")
    private String BOOTSTRAP_SERVERS_CONFIG;

    @Value("${kafka.consumer.group-id}")
    private String GROUP_ID_CONFIG;

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerDemo.class.getSimpleName());

    private Properties consumerConfigs() {
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_CONFIG);
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_CONFIG);
        props.setProperty(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }

    public void consumeMessage(String topic){
        log.info(">> KafkaConsumerDemo:");
        log.info(">> Polling...");
        KafkaConsumer<String, Object> consumer = new KafkaConsumer<>(consumerConfigs());

        consumer.subscribe(Arrays.asList(topic));

        while(true){
            ConsumerRecords<String, Object> records = consumer.poll(Duration.ofMillis(1000));
            for(ConsumerRecord<String, Object> record: records){
                log.info("Key: " + record.key() + ", Value: " + record.value());
                log.info("Partition: " + record.partition() + ", Offset: " + record.offset());
            }
        }
    }
}
