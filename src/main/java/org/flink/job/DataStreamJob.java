package org.flink.job;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.flink.connector.KafkaConnector;
import org.flink.utils.JsonUtils;

import java.util.Map;


public class DataStreamJob {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        KafkaConnector connector = new KafkaConnector();
        DataStream<String> messageStream = env.addSource(connector.consumer("inputkafka"));

        messageStream.rebalance().map((MapFunction<String,String>) value -> {
            Map<String,String> responseMap = JsonUtils.deserialize(value, Map.class);
            responseMap.put("status", "Active");
            return responseMap.toString();

        }).addSink(connector.producer("outputkafka"));

        env.execute();
    }
}




