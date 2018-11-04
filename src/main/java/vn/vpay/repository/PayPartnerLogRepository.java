package vn.vpay.repository;

import vn.vpay.domain.PayPartnerLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PayPartnerLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PayPartnerLogRepository extends JpaRepository<PayPartnerLog, Long> {

}
