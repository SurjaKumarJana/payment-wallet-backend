package org.surja.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.surja.dto.UserCreatedPayload;
import org.surja.entity.Wallet;
import org.surja.repo.WalletRepo;

@Configuration
public class WalletKafkaConsummer {


    private static Logger LOGGER = LoggerFactory.getLogger(WalletKafkaConsummer.class);

    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private WalletRepo walletRepo;

    @KafkaListener(topics = "${user.created.topic}", groupId = "wallet")
    public void consumeUserCreateTopic(ConsumerRecord payload) throws JsonProcessingException {
        UserCreatedPayload userCreatedPayload = OBJECT_MAPPER.readValue(payload.value().toString(), UserCreatedPayload.class);
        MDC.put("requestId", userCreatedPayload.getRequestId());
        LOGGER.info("Read usercreated payload from kafka : {}", userCreatedPayload);
        Wallet wallet = new Wallet();
        wallet.setBalance(100.00);
        wallet.setUserId(userCreatedPayload.getUserId());
        wallet.setUserEmail(userCreatedPayload.getUserEmail());
        walletRepo.save(wallet);
        MDC.clear();;
    }
}
