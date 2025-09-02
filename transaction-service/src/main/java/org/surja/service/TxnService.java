package org.surja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.surja.dto.TxnRequestDto;
import org.surja.entity.Transaction;
import org.surja.entity.TransactionStatus;
import org.surja.repo.TxnRepo;

import java.util.UUID;

@Service
public class TxnService {

    @Autowired
    private TxnRepo txnRepo;

    public String initTransaction(TxnRequestDto txnDto){

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


        return transaction.getTxnId();
    }



}
