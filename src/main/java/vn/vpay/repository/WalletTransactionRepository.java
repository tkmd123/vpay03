package vn.vpay.repository;

import vn.vpay.domain.WalletTransaction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WalletTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {

}
