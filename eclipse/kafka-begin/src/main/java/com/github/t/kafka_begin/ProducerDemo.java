package com.github.t.kafka_begin;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;


public class ProducerDemo {
	public static void main(String args[]) {
		
		Properties properties= new Properties();
		String bootstrapservers="127.0.0.1:9092";
		
		//Create Producer Properties
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapservers);
		properties.setProperty("key.serializer",StringSerializer.class.getName());
		properties.setProperty("value.serializer",StringSerializer.class.getName());
		//Create a Producer
		KafkaProducer<String,String> producer=new KafkaProducer<String,String>(properties);
		// Create a producer record
		ProducerRecord<String,String> record=new ProducerRecord<String,String>("f_t","Hello!");
		//send data
		producer.send(record);
		producer.flush();
		producer.close();
		
	}

}

