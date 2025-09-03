package org.surja.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.surja.entity.Wallet;

@Repository
public interface WalletRepo extends JpaRepository<Wallet,Long> {

    Wallet findByUserId(Long userId);
}
