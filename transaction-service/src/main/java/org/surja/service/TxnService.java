package org.surja.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.surja.dto.InitTxnPayload;
import org.surja.dto.TxnRequestDto;
import org.surja.entity.Transaction;
import org.surja.entity.TransactionStatus;
import org.surja.repo.TxnRepo;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class TxnService {

    private static Logger LOGGER = LoggerFactory.getLogger(TxnService.class);

    @Autowired
    private TxnRepo txnRepo;

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @Value("${txn.init.topic}")
    private String txnInitTopic;


    public String initTransaction(TxnRequestDto txnDto) throws ExecutionException, InterruptedException {

        //saving the transaction in db
        Transaction transaction = Transaction.builder()
                .txnId(UUID.randomUUID().toString())
                .fromUserId(txnDto.getFromUserId())
                .toUserId(txnDto.getToUserId())
                .amount(txnDto.getAmount())
                .comment(txnDto.getComment())
                .status(TransactionStatus.PENDING)
                .build();
        txnRepo.save(transaction);

        LOGGER.info("transaction initialized Id : {}",transaction.getTxnId());

        // pushing to kafka

        InitTxnPayload payload = InitTxnPayload.builder()
                .id(transaction.getId())
                .formUserId(txnDto.getFromUserId())
                .toUserId(txnDto.getToUserId())
                .amount(transaction.getAmount())
                .requestId(MDC.get("requestId"))
                .build();
        Future<SendResult<String,Object>> future  = kafkaTemplate.send(txnInitTopic,transaction.getFromUserId().toString(),payload);
        LOGGER.info("Pushed txnInitPayload to kafka: {}",future.get());


        return transaction.getTxnId();
    }



}
