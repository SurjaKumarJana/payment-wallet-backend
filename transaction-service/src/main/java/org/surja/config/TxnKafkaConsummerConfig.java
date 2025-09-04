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
import org.surja.dto.TxnCompletedPayload;
import org.surja.entity.Transaction;
import org.surja.entity.TransactionStatus;
import org.surja.repo.TxnRepo;

@Configuration
public class TxnKafkaConsummerConfig {

    private static Logger LOGGER = LoggerFactory.getLogger(TxnKafkaConsummerConfig.class);

    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private TxnRepo txnRepo;

    @KafkaListener(topics = "${txn.completed.topic}", groupId = "txn")
    public void consumeTxnKafkaPayload(ConsumerRecord payload) throws JsonProcessingException {
        TxnCompletedPayload txnCompletedPayload = OBJECT_MAPPER.readValue(payload.value().toString(), TxnCompletedPayload.class);
        MDC.put("requestId", txnCompletedPayload.getRequestId());
        LOGGER.info("Read from kafka : {}", txnCompletedPayload);

        Transaction transaction = txnRepo.findById(txnCompletedPayload.getId()).get();
        if(!txnCompletedPayload.getSuccess()){
            transaction.setStatus(TransactionStatus.FAILED);
            transaction.setReason(txnCompletedPayload.getReason());
        }
        else{
            transaction.setStatus(TransactionStatus.SUCCESS);
            transaction.setReason(txnCompletedPayload.getReason());
        }
        txnRepo.save(transaction);
        LOGGER.info("txn status updated : {}", txnCompletedPayload.getId());
        MDC.clear();;

    }
}
