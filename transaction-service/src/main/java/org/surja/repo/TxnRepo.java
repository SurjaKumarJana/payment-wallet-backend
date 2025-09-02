package org.surja.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.surja.entity.Transaction;

@Repository
public interface TxnRepo extends JpaRepository<Transaction, Long> {

    Transaction findByTxnId(String txnId);
}
