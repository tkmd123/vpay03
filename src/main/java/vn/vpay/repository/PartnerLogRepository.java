package vn.vpay.repository;

import vn.vpay.domain.PartnerLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PartnerLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PartnerLogRepository extends JpaRepository<PartnerLog, Long> {

}
