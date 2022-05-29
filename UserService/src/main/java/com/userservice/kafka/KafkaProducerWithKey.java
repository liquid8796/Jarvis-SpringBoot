package com.userservice.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KafkaProducerWithKey {

    @Value("${kafka.bootstrap-servers}")
    private String BOOTSTRAP_SERVERS_CONFIG;

    private static final Logger log = LoggerFactory.getLogger(KafkaProducerWithKey.class.getSimpleName());
    
    private Properties producerConfigs() {
        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_CONFIG);
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
        return props;
    }

    public void sendMessage(String topic, Object key, Object message){
        log.info(">> KafkaProducerWithKey:");
        KafkaProducer<String, Object> producer = new KafkaProducer<>(producerConfigs());

        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topic, key.toString(), message);
        //send the data - async
        producer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception e) {
                //execute everytime a record is successfully sent or exception is thrown
                if(e == null){
                    //the record was successfully sent
                    log.info(">> Produce new metadata/ \n" +
                            "Topic: " + metadata.topic() + "\n" +
                            "Key: " + producerRecord.key() + "\n" +
                            "Partition " + metadata.partition() + "\n" +
                            "Offset" + metadata.offset() + "\n" +
                            "Timestamp" + metadata.timestamp());
                }else{
                    log.error("Error while producing", e);
                }
            }
        });
        //flush data - sync
        producer.flush();
        //flush and close producer
        producer.close();
    }
}
