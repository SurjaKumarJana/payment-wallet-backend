package org.surja.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.surja.dto.InitTxnPayload;
import org.surja.dto.TxnCompletedPayload;
import org.surja.entity.Wallet;
import org.surja.repo.WalletRepo;



@Service
public class WalletService {

    private static Logger LOGGER = LoggerFactory.getLogger(WalletService.class);

    @Autowired
    private WalletRepo walletRepo;

    @Transactional
    public void walletTxn(InitTxnPayload payload){
        Wallet fromWallet = walletRepo.findByUserId(payload.getFormUserId());
        TxnCompletedPayload completedPayload = new TxnCompletedPayload();
        completedPayload.setId(payload.getId());
        completedPayload.setRequestId(payload.getRequestId());
        if(fromWallet.getBalance() < payload.getAmount()){
            completedPayload.setSuccess(false);
            completedPayload.setReason("LOW BALANCE");
        }
        else{
            Wallet toWallet = walletRepo.findByUserId(payload.getToUserId());
            fromWallet.setBalance(fromWallet.getBalance() - payload.getAmount());
            toWallet.setBalance(toWallet.getBalance() + payload.getAmount());

            completedPayload.setSuccess(true);
            completedPayload.setReason("TRANSACTION SUCCESS");


        }
        LOGGER.info("txn done {}",completedPayload);

        
    }
}
