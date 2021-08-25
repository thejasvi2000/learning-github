package com.github.t.kafka_begin;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.LoggerFactory;

public class ProducerwithKeys {
	public static void main(String args[]) throws InterruptedException, ExecutionException {
	 Properties properties= new Properties();
	 String bootstrapservers="127.0.0.1:9092";
	 final org.slf4j.Logger logger = LoggerFactory.getLogger(ProducerwithKeys.class.getName());
  	//Create Producer Properties
	properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapservers);
	properties.setProperty("key.serializer",StringSerializer.class.getName());
	properties.setProperty("value.serializer",StringSerializer.class.getName());
	//Create a Producer
	KafkaProducer<String,String> producer=new KafkaProducer<String,String>(properties);
	
	for(int i=0;i<10;i++)
	{
	String key="id_"+Integer.toString(i);
	// Create a producer record
	ProducerRecord<String,String> record=new ProducerRecord<String,String>("f_n",key,"Hello!"+Integer.toString(i));
	logger.info("key:"+key+"\t");
	//send data
	producer.send(record,new Callback() {
		
		@Override
		public void onCompletion(RecordMetadata recordmetadata, Exception e) {
			// TODO Auto-generated method stub
			if(e==null)
			{
				logger.info("Topic:"+recordmetadata.topic()+"Partition:"+recordmetadata.partition());
			}
			else
			{
				logger.error("Error while producing");
			}
			
			
		}
	}).get();
	}
	producer.flush();
	producer.close();
	
	
}
}
