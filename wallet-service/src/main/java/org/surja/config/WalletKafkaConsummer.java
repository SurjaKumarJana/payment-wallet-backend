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
import org.surja.dto.InitTxnPayload;
import org.surja.dto.UserCreatedPayload;
import org.surja.entity.Wallet;
import org.surja.repo.WalletRepo;
import org.surja.service.WalletService;

@Configuration
public class WalletKafkaConsummer {


    private static Logger LOGGER = LoggerFactory.getLogger(WalletKafkaConsummer.class);

    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private WalletRepo walletRepo;

    @Autowired
    private WalletService walletService;

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

    @KafkaListener(topics = "${txn.init.topic}" , groupId = "wallet")
    public void consumeTxnInitTopic (ConsumerRecord payload) throws JsonProcessingException {
        InitTxnPayload initTxnPayload = (InitTxnPayload) OBJECT_MAPPER.readValue(payload.value().toString(), InitTxnPayload.class);
        MDC.put("requestId",initTxnPayload.getRequestId());
        LOGGER.info("reading from kafka  {}",initTxnPayload);
        walletService.walletTxn(initTxnPayload);
        MDC.clear();

    }

}
