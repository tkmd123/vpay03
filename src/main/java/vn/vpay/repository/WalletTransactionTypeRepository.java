package vn.vpay.repository;

import vn.vpay.domain.WalletTransactionType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WalletTransactionType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WalletTransactionTypeRepository extends JpaRepository<WalletTransactionType, Long> {

}
