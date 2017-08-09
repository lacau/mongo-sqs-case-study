package com.player.poc.playerpoc.service;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.player.poc.playerpoc.model.Player;
import com.player.poc.playerpoc.repository.PlayerMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class PlayerServiceMongoImpl implements PlayerService {

    @Autowired
    private PlayerMongoRepository playerMongoRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${sqs.queue.endpoint}")
    private String queueEndpoint;

    @Value("${sqs.queue.name}")
    private String queueName;

    @Autowired
    @Qualifier("amazonSQSAsync")
    private AmazonSQSAsync amazonSQSAsync;

    private Update update;

    @PostConstruct
    private void init() {
        update = new Update().inc("count", 1);
    }

    @Override
    public Player save(Player player) {
        return playerMongoRepository.save(player);
    }

    @Override
    public void incrementOne(String id) {
        final Query query = new Query(Criteria.where("id").is(id));
        mongoOperations.updateFirst(query, update, Player.class);
    }

    @Override
    public Player findById(String id) {
        return playerMongoRepository.findOne(id);
    }

    @Override
    public List<Player> findAll() {
        return playerMongoRepository.findAll();
    }

    @Override
    public void removeAll() {
        playerMongoRepository.deleteAll();
    }

    @Override
    public void sendMessage(Player player) {
        queueMessagingTemplate.send(queueName, MessageBuilder.withPayload(player).build());
    }

    public String getMessagesCount() {
        final GetQueueUrlResult queueUrl = amazonSQSAsync.getQueueUrl(queueName);
        return amazonSQSAsync.getQueueAttributes(queueUrl.getQueueUrl(), Arrays.asList("All")).getAttributes().get("ApproximateNumberOfMessages");
    }
}
