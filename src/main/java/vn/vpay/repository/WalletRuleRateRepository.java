package vn.vpay.repository;

import vn.vpay.domain.WalletRuleRate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WalletRuleRate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WalletRuleRateRepository extends JpaRepository<WalletRuleRate, Long> {

}
