package com.df.springboot.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.df.springboot.kafka.User;

@Service
public class KafkaConsumerListener {
	
//	@KafkaListener(topics = "kafkaTopic1", groupId = "kafkaGroupId123")
//	public void consumer(String message) {
//		System.out.println("Consumed message from Kafka topic : " + message);
//	}
	
	@KafkaListener(topics = "kafkaTopic1", groupId = "kafkaGroupId123", 
			containerFactory = "userKafkaListenerContainerFactory")
	public void jsonConsumer(User user) {
		System.out.println("Consumed json message from Kafka topic :" + user);
	}
}
