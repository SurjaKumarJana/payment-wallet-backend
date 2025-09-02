package org.surja.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.surja.entity.Wallet;

public interface WalletRepo extends JpaRepository<Wallet,Long> {
}
