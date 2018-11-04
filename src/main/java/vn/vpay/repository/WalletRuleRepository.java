package vn.vpay.repository;

import vn.vpay.domain.WalletRule;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WalletRule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WalletRuleRepository extends JpaRepository<WalletRule, Long> {

}
