package com.github.t.kafka_begin;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerDemo {
	public static void main(String args[]) {
		final Logger logger=LoggerFactory.getLogger(ConsumerDemo.class.getName());
		String bss="127.0.0.1:9092";
		String gid="m_a4";
		Properties properties=new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bss);
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,gid);
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
		//create consumer
		KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(properties);
		
		//subscribe consumer to our topic 
		consumer.subscribe(Collections.singleton("f_n"));
		
		//pool for new data
		while(true)
		{
			ConsumerRecords<String, String> records=consumer.poll(Duration.ofMillis(100));
			for(ConsumerRecord<String, String> record :records)
			{
				logger.info("key:"+record.key()+"value:"+record.value()+"partition:"+record.partition());
			}
		}
		
	}

}
