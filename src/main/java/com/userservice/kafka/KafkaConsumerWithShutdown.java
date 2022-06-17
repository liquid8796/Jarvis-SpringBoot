package com.userservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
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
public class KafkaConsumerWithShutdown {

    @Value("${kafka.bootstrap-servers}")
    private String BOOTSTRAP_SERVERS_CONFIG;

    @Value("${kafka.consumer.group-id}")
    private String GROUP_ID_CONFIG;

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerWithShutdown.class.getSimpleName());

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
        log.info(">> KafkaConsumerWithShutdown:");

        KafkaConsumer<String, Object> consumer = new KafkaConsumer<>(consumerConfigs());

        //get a reference to the current thread
        final Thread mainThread = Thread.currentThread();

        //adding the shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run() {
                log.info("Detected a shutdown, let's exit by calling consumer.wakeup()...");
                consumer.wakeup();

                //join the main thread to allow the execution of the code in the main thread
                try{
                    mainThread.join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        try{
            log.info(">> Polling...");
            consumer.subscribe(Arrays.asList(topic));

            while(true){
                ConsumerRecords<String, Object> records = consumer.poll(Duration.ofMillis(1000));
                for(ConsumerRecord<String, Object> record: records){
                    log.info("Key: " + record.key() + ", Value: " + record.value());
                    log.info("Partition: " + record.partition() + ", Offset: " + record.offset());
                }
            }
        }catch (WakeupException e){
            log.info("Wake up exception!");
            //we ignore this as this is an expected exception when closing a consumer
        }catch (Exception e){
            log.error("Unexpected exception");
        }finally{
            consumer.close(); //this will also commit the offsets if nned be
            log.info("The consumer is now gracefully closed");
        }

    }
}
