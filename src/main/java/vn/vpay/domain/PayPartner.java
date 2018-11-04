package vn.vpay.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A PayPartner.
 */
@Entity
@Table(name = "pay_partner")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PayPartner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "pay_partner_code", nullable = false, unique = true)
    private String payPartnerCode;

    @NotNull
    @Column(name = "pay_partner_name", nullable = false)
    private String payPartnerName;

    @Column(name = "pay_partner_desc")
    private String payPartnerDesc;

    @Column(name = "pay_partner_username")
    private String payPartnerUsername;

    @Column(name = "pay_partner_secret_key")
    private String payPartnerSecretKey;

    @Column(name = "pay_partner_status")
    private String payPartnerStatus;

    @Column(name = "pay_partner_udf_1")
    private String payPartnerUDF1;

    @Column(name = "pay_partner_udf_2")
    private String payPartnerUDF2;

    @Column(name = "pay_partner_udf_3")
    private String payPartnerUDF3;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "payPartner")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Wallet> walletPayPartners = new HashSet<>();
    @OneToMany(mappedBy = "payPartner")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WalletRule> walletRulePayPartners = new HashSet<>();
    @OneToMany(mappedBy = "payPartner")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PayPartnerLog> partnerLogPayPartners = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayPartnerCode() {
        return payPartnerCode;
    }

    public PayPartner payPartnerCode(String payPartnerCode) {
        this.payPartnerCode = payPartnerCode;
        return this;
    }

    public void setPayPartnerCode(String payPartnerCode) {
        this.payPartnerCode = payPartnerCode;
    }

    public String getPayPartnerName() {
        return payPartnerName;
    }

    public PayPartner payPartnerName(String payPartnerName) {
        this.payPartnerName = payPartnerName;
        return this;
    }

    public void setPayPartnerName(String payPartnerName) {
        this.payPartnerName = payPartnerName;
    }

    public String getPayPartnerDesc() {
        return payPartnerDesc;
    }

    public PayPartner payPartnerDesc(String payPartnerDesc) {
        this.payPartnerDesc = payPartnerDesc;
        return this;
    }

    public void setPayPartnerDesc(String payPartnerDesc) {
        this.payPartnerDesc = payPartnerDesc;
    }

    public String getPayPartnerUsername() {
        return payPartnerUsername;
    }

    public PayPartner payPartnerUsername(String payPartnerUsername) {
        this.payPartnerUsername = payPartnerUsername;
        return this;
    }

    public void setPayPartnerUsername(String payPartnerUsername) {
        this.payPartnerUsername = payPartnerUsername;
    }

    public String getPayPartnerSecretKey() {
        return payPartnerSecretKey;
    }

    public PayPartner payPartnerSecretKey(String payPartnerSecretKey) {
        this.payPartnerSecretKey = payPartnerSecretKey;
        return this;
    }

    public void setPayPartnerSecretKey(String payPartnerSecretKey) {
        this.payPartnerSecretKey = payPartnerSecretKey;
    }

    public String getPayPartnerStatus() {
        return payPartnerStatus;
    }

    public PayPartner payPartnerStatus(String payPartnerStatus) {
        this.payPartnerStatus = payPartnerStatus;
        return this;
    }

    public void setPayPartnerStatus(String payPartnerStatus) {
        this.payPartnerStatus = payPartnerStatus;
    }

    public String getPayPartnerUDF1() {
        return payPartnerUDF1;
    }

    public PayPartner payPartnerUDF1(String payPartnerUDF1) {
        this.payPartnerUDF1 = payPartnerUDF1;
        return this;
    }

    public void setPayPartnerUDF1(String payPartnerUDF1) {
        this.payPartnerUDF1 = payPartnerUDF1;
    }

    public String getPayPartnerUDF2() {
        return payPartnerUDF2;
    }

    public PayPartner payPartnerUDF2(String payPartnerUDF2) {
        this.payPartnerUDF2 = payPartnerUDF2;
        return this;
    }

    public void setPayPartnerUDF2(String payPartnerUDF2) {
        this.payPartnerUDF2 = payPartnerUDF2;
    }

    public String getPayPartnerUDF3() {
        return payPartnerUDF3;
    }

    public PayPartner payPartnerUDF3(String payPartnerUDF3) {
        this.payPartnerUDF3 = payPartnerUDF3;
        return this;
    }

    public void setPayPartnerUDF3(String payPartnerUDF3) {
        this.payPartnerUDF3 = payPartnerUDF3;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public PayPartner isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Set<Wallet> getWalletPayPartners() {
        return walletPayPartners;
    }

    public PayPartner walletPayPartners(Set<Wallet> wallets) {
        this.walletPayPartners = wallets;
        return this;
    }

    public PayPartner addWalletPayPartner(Wallet wallet) {
        this.walletPayPartners.add(wallet);
        wallet.setPayPartner(this);
        return this;
    }

    public PayPartner removeWalletPayPartner(Wallet wallet) {
        this.walletPayPartners.remove(wallet);
        wallet.setPayPartner(null);
        return this;
    }

    public void setWalletPayPartners(Set<Wallet> wallets) {
        this.walletPayPartners = wallets;
    }

    public Set<WalletRule> getWalletRulePayPartners() {
        return walletRulePayPartners;
    }

    public PayPartner walletRulePayPartners(Set<WalletRule> walletRules) {
        this.walletRulePayPartners = walletRules;
        return this;
    }

    public PayPartner addWalletRulePayPartner(WalletRule walletRule) {
        this.walletRulePayPartners.add(walletRule);
        walletRule.setPayPartner(this);
        return this;
    }

    public PayPartner removeWalletRulePayPartner(WalletRule walletRule) {
        this.walletRulePayPartners.remove(walletRule);
        walletRule.setPayPartner(null);
        return this;
    }

    public void setWalletRulePayPartners(Set<WalletRule> walletRules) {
        this.walletRulePayPartners = walletRules;
    }

    public Set<PayPartnerLog> getPartnerLogPayPartners() {
        return partnerLogPayPartners;
    }

    public PayPartner partnerLogPayPartners(Set<PayPartnerLog> payPartnerLogs) {
        this.partnerLogPayPartners = payPartnerLogs;
        return this;
    }

    public PayPartner addPartnerLogPayPartner(PayPartnerLog payPartnerLog) {
        this.partnerLogPayPartners.add(payPartnerLog);
        payPartnerLog.setPayPartner(this);
        return this;
    }

    public PayPartner removePartnerLogPayPartner(PayPartnerLog payPartnerLog) {
        this.partnerLogPayPartners.remove(payPartnerLog);
        payPartnerLog.setPayPartner(null);
        return this;
    }

    public void setPartnerLogPayPartners(Set<PayPartnerLog> payPartnerLogs) {
        this.partnerLogPayPartners = payPartnerLogs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PayPartner payPartner = (PayPartner) o;
        if (payPartner.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), payPartner.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PayPartner{" +
            "id=" + getId() +
            ", payPartnerCode='" + getPayPartnerCode() + "'" +
            ", payPartnerName='" + getPayPartnerName() + "'" +
            ", payPartnerDesc='" + getPayPartnerDesc() + "'" +
            ", payPartnerUsername='" + getPayPartnerUsername() + "'" +
            ", payPartnerSecretKey='" + getPayPartnerSecretKey() + "'" +
            ", payPartnerStatus='" + getPayPartnerStatus() + "'" +
            ", payPartnerUDF1='" + getPayPartnerUDF1() + "'" +
            ", payPartnerUDF2='" + getPayPartnerUDF2() + "'" +
            ", payPartnerUDF3='" + getPayPartnerUDF3() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
