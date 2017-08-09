package com.player.poc.playerpoc.config;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SQSConfig {

    @Value("${sqs.queue.endpoint}")
    private String queueEndpoint;

    @Value("${sqs.queue.name}")
    private String queueName;

    @Bean("amazonSQSAsync")
    public AmazonSQSAsync amazonSQSAsync() {
        final AmazonSQSAsyncClient amazonSQSAsyncClient = new AmazonSQSAsyncClient(new BasicAWSCredentials("x", "x"));
        amazonSQSAsyncClient.setEndpoint(queueEndpoint);

        return amazonSQSAsyncClient;
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(@Qualifier("amazonSQSAsync") AmazonSQSAsync amazonSQS) {
        amazonSQS.createQueue(queueName);
        amazonSQS.listQueues();
        final QueueMessagingTemplate queueMessagingTemplate = new QueueMessagingTemplate(amazonSQS);
        queueMessagingTemplate.setDefaultDestinationName(queueName);


        return queueMessagingTemplate;
    }
}
