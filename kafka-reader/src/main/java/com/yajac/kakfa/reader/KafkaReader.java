package com.yajac.kakfa.reader;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.*;

/**
 * Reads from kafka
 *
 * Created by ian.mcewan on 7/1/17.
 */
public class KafkaReader {

    private Properties consumerProps= new Properties();

    public KafkaReader(){
        consumerProps.put("bootstrap.servers", "kafka-balancer-283745539.us-east-1.elb.amazonaws.com:9092");
//        props.put("enable.auto.commit", "true");
//        props.put("auto.commit.interval.ms", "1000");
        consumerProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProps.put("auto.offset.reset", "earliest");
        consumerProps.put("group.id", UUID.randomUUID().toString());
    }

    public void read() {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProps);
        subscribeTopics(consumer);
        getRecords(consumer);
    }

    private void subscribeTopics(KafkaConsumer<String, String> consumer) {
        Collection<String> topics = new ArrayList<String>();
        topics.add("testInt");
        consumer.subscribe(topics);
    }

    private void getRecords(KafkaConsumer<String, String> consumer) {
        ConsumerRecords<String, String> records = consumer.poll(1000);
        System.out.println("Records " + records.count());
        handleRecords(records);
    }

    private void handleRecords(ConsumerRecords<String, String> records) {
        for (ConsumerRecord<String, String> record : records)
            System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
    }
}
