package org.flink.connector;


import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.Properties;

public class KafkaConnector {


    public FlinkKafkaConsumer<String> consumer(String topicName) {
        return new FlinkKafkaConsumer<>(topicName, new SimpleStringSchema(), configuration());
    }

    public FlinkKafkaProducer<String> producer(String topicName) {
        return new FlinkKafkaProducer<>(topicName, new SimpleStringSchema(), configuration());

    }

    public Properties configuration() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        return props;
    }

}
