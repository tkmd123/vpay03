package vn.vpay.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(vn.vpay.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(vn.vpay.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(vn.vpay.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(vn.vpay.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(vn.vpay.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(vn.vpay.domain.ProductType.class.getName(), jcacheConfiguration);
            cm.createCache(vn.vpay.domain.ProductType.class.getName() + ".productProductTypes", jcacheConfiguration);
            cm.createCache(vn.vpay.domain.ProductType.class.getName() + ".walletProductTypes", jcacheConfiguration);
            cm.createCache(vn.vpay.domain.ProductType.class.getName() + ".walletRuleProductTypes", jcacheConfiguration);
            cm.createCache(vn.vpay.domain.ProductType.class.getName() + ".partnerTransProductTypes", jcacheConfiguration);
            cm.createCache(vn.vpay.domain.ProductType.class.getName() + ".productTypeWallets", jcacheConfiguration);
            cm.createCache(vn.vpay.domain.Product.class.getName(), jcacheConfiguration);
            cm.createCache(vn.vpay.domain.ProductTypeWallet.class.getName(), jcacheConfiguration);
            cm.createCache(vn.vpay.domain.Partner.class.getName(), jcacheConfiguration);
            cm.createCache(vn.vpay.domain.Partner.class.getName() + ".walletPartners", jcacheConfiguration);
            cm.createCache(vn.vpay.domain.Partner.class.getName() + ".partnerLogPartners", jcacheConfiguration);
            cm.createCache(vn.vpay.domain.Partner.class.getName() + ".partnerTransPartners", jcacheConfiguration);
            cm.createCache(vn.vpay.domain.PayPartner.class.getName(), jcacheConfiguration);
            cm.createCache(vn.vpay.domain.PayPartner.class.getName() + ".walletPayPartners", jcacheConfiguration);
            cm.createCache(vn.vpay.domain.PayPartner.class.getName() + ".walletRulePayPartners", jcacheConfiguration);
            cm.createCache(vn.vpay.domain.PayPartner.class.getName() + ".partnerLogPayPartners", jcacheConfiguration);
            cm.createCache(vn.vpay.domain.WalletTransactionType.class.getName(), jcacheConfiguration);
            cm.createCache(vn.vpay.domain.WalletTransactionType.class.getName() + ".walletTransWalletTransTypes", jcacheConfiguration);
            cm.createCache(vn.vpay.domain.Wallet.class.getName(), jcacheConfiguration);
            cm.createCache(vn.vpay.domain.Wallet.class.getName() + ".walletTransWallets", jcacheConfiguration);
            cm.createCache(vn.vpay.domain.Wallet.class.getName() + ".walletProductTypes", jcacheConfiguration);
            cm.createCache(vn.vpay.domain.WalletRule.class.getName(), jcacheConfiguration);
            cm.createCache(vn.vpay.domain.WalletRule.class.getName() + ".walletRuleRateWalletRules", jcacheConfiguration);
            cm.createCache(vn.vpay.domain.WalletRuleRate.class.getName(), jcacheConfiguration);
            cm.createCache(vn.vpay.domain.WalletTransaction.class.getName(), jcacheConfiguration);
            cm.createCache(vn.vpay.domain.WalletTransaction.class.getName() + ".partnerTransWalletTransactions", jcacheConfiguration);
            cm.createCache(vn.vpay.domain.PartnerTransaction.class.getName(), jcacheConfiguration);
            cm.createCache(vn.vpay.domain.PayPartnerLog.class.getName(), jcacheConfiguration);
            cm.createCache(vn.vpay.domain.PartnerLog.class.getName(), jcacheConfiguration);
            cm.createCache(vn.vpay.domain.Status.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
