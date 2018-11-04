package vn.vpay.repository;

import vn.vpay.domain.ProductTypeWallet;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProductTypeWallet entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductTypeWalletRepository extends JpaRepository<ProductTypeWallet, Long> {

}
