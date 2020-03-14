package com.df.springboot.kafka.consumer;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.zookeeper.ZooKeeper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;

import com.df.springboot.kafka.GlobalProperties;

@Configuration
@EnableKafka
@PropertySource("classpath:custom.properties")
public class KafkaConsumerConfig {
	
	@Value("${kafka.bootstrap.server}")
	private String kafkaServerURL;
	
	@Value("${kafka.group.id}")
	private String kafkaGroupId;
	
	@Value("${zookeeper.server}")
	private String zookeeperServerURL;
	
	@Value("${zookeeper.topics.path}")
	private String getAllTopicsURL;
	
	@Value("${name.property}")
	private String nameProperty;
	
	@Value("${city.property}")
	private String cityProperty;
	
	@Autowired
	private Environment env;
	
	@Autowired
	GlobalProperties prop;
	
	public static final org.slf4j.Logger logger = LoggerFactory.getLogger(KafkaConsumerConfig.class);
	
	@PostConstruct
	public String[] getAllKafkaTopics() {
		List<String> topics = new ArrayList<>();
		System.out.println("$$$$$$$$$$$$$$$$" + env.getProperty("kafka.bootstrap.server"));
		System.out.println("$$$$$$$$$$$$$$$$" + kafkaServerURL);
		System.out.println("$$$$$$$$$$$$$$$$" + nameProperty);
		System.out.println("$$$$$$$$$$$$$$$$" + cityProperty);
		System.out.println("$$$$$$$$$$$$$$$$" + prop.getName());
		System.out.println("$$$$$$$$$$$$$$$$" + prop.getCity());
		System.out.println("$$$$$$$$$$$$$$$$" + prop.toString());
		try {
			ZooKeeper zk = new ZooKeeper(zookeeperServerURL, 10000, null);
			topics = zk.getChildren(getAllTopicsURL, false);
			topics.remove("__consumer_offsets");
			topics.remove("topics");
			logger.info("Topics obtained from kafka cluster : ");
			topics.stream().forEach(topic -> logger.info(topic));
		} catch (Exception e) {
			logger.error("Exception while getting list of all topics from Zookeeper : {}", e);
		}
        return topics.stream().toArray(String[]::new);
	}
	
}
