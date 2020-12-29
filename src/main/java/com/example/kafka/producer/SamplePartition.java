package com.example.kafka.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class SamplePartition implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        /*
            key-1
            key-2
            key-3
         */
        String keyStr = key + "";
        String keyInt = keyStr.substring(4);
        System.out.println("keyStr : "+keyStr + "keyInt : "+keyInt);

        int i = Integer.parseInt(keyInt);

        return i%2;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
