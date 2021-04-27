package com.example.kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.errors.RetriableException;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ProducerSample {

    private final static String TOPIC_NAME="test-topic";
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Producer异步发送演示
//        producerSend();
        // Producer异步阻塞发送演示
//        producerSyncSend();
        // Producer异步发送带回调函数
//        producerSendWithCallback();
        // Producer异步发送带回调函数和Partition负载均衡
        producerSendWithCallbackAndPartition();
    }


    /*
        Producer异步发送带回调函数和Partition负载均衡
     */
    public static void producerSendWithCallbackAndPartition(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.220.128:9092");
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        properties.put(ProducerConfig.RETRIES_CONFIG,"0");
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,"16384");
        properties.put(ProducerConfig.LINGER_MS_CONFIG,"1");
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,"33554432");

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.imooc.jiangzh.kafka.producer.SamplePartition");

        // Producer的主对象
        Producer<String,String> producer = new KafkaProducer<>(properties);

        // 消息对象 - ProducerRecoder
        for(int i=0;i<10;i++){
            ProducerRecord<String,String> record =
                    new ProducerRecord<>(TOPIC_NAME,"key-"+i,"value-"+i);

            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.println(
                            "partition : "+recordMetadata.partition()+" , offset : "+recordMetadata.offset());
                }
            });
        }

        // 所有的通道打开都需要关闭
        producer.close();
    }



    /*
        Producer异步发送带回调函数
     */
    public static void producerSendWithCallback(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.220.128:9092");
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        properties.put(ProducerConfig.RETRIES_CONFIG,"0");
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,"16384");
        properties.put(ProducerConfig.LINGER_MS_CONFIG,"1");
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,"33554432");

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        // Producer的主对象
        Producer<String,String> producer = new KafkaProducer<>(properties);

        // 消息对象 - ProducerRecoder
        for(int i=0;i<10;i++){
            ProducerRecord<String,String> record =
                    new ProducerRecord<>(TOPIC_NAME,"key-"+i,"value-"+i);
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e==null){
                        System.out.println("消息发送成功");
                        System.out.println(
                                "partition : "+recordMetadata.partition()+" , offset : "+recordMetadata.offset());

                    }else {
                        if (e instanceof RetriableException){
                            System.out.println("可重试错误");
                        }else {
                            System.out.println("不可重试错误");
                        }
                        System.out.println("消息发送失败，执行失败处理，一般存库或者重发");
                    }
                }
            });
        }

        // 所有的通道打开都需要关闭
        producer.close();
    }

    /*
        Producer异步发送演示
     */
    public static void producerSend(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.220.128:9092");
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        properties.put(ProducerConfig.RETRIES_CONFIG,"0");
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,"16384");
        properties.put(ProducerConfig.LINGER_MS_CONFIG,"1");
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,"33554432");

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        // Producer的主对象
        Producer<String,String> producer = new KafkaProducer<>(properties);

        // 消息对象 - ProducerRecoder
        for(int i=0;i<10;i++){
            ProducerRecord<String,String> record =
                    new ProducerRecord<>(TOPIC_NAME,"key-"+i,"value-"+i);

            producer.send(record);
        }

        // 所有的通道打开都需要关闭
        producer.close();
    }

    /*
        Producer异步阻塞发送
     */
    public static void producerSyncSend() throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.220.128:9092");
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        properties.put(ProducerConfig.RETRIES_CONFIG,"0");
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,"16384");
        properties.put(ProducerConfig.LINGER_MS_CONFIG,"1");
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,"33554432");

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");


        // Producer的主对象
        Producer<String,String> producer = new KafkaProducer<>(properties);

        // 消息对象 - ProducerRecoder
        for(int i=0;i<10;i++){
            String key = "key-"+i;
            ProducerRecord<String,String> record =
                    new ProducerRecord<>(TOPIC_NAME,key,"value-"+i);

            Future<RecordMetadata> send = producer.send(record);
            RecordMetadata recordMetadata = send.get();
            System.out.println(key + "partition : "+recordMetadata.partition()+" , offset : "+recordMetadata.offset());
        }

        // 所有的通道打开都需要关闭
        producer.close();
    }

}
