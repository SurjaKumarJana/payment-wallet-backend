package org.surja.repo;

import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TxnRepo extends JpaRepository {

    Long findByTxnId(String txnId);
}
