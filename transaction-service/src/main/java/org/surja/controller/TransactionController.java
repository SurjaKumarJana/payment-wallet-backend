package org.surja.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.surja.dto.TxnRequestDto;
import org.surja.service.TxnService;

import javax.xml.crypto.dsig.TransformService;

@RestController
@RequestMapping("/txn-service")
public class TransactionController {

    private static Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TxnService txnService;

    @PostMapping("/init-txn")
    public ResponseEntity<String> initTransaction(@RequestBody TxnRequestDto txnRequestDto){
        LOGGER.info("request for init txn requestId : {} ",txnRequestDto.getFromUserId());
        return ResponseEntity.accepted().body(txnService.initTransaction(txnRequestDto));
    }
}
