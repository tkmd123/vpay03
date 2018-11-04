package vn.vpay.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A WalletRule.
 */
@Entity
@Table(name = "wallet_rule")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WalletRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "wallet_rule_code", nullable = false, unique = true)
    private String walletRuleCode;

    @NotNull
    @Column(name = "wallet_rule_name", nullable = false)
    private String walletRuleName;

    @Column(name = "wallet_rule_desc")
    private String walletRuleDesc;

    @NotNull
    @Column(name = "wallet_rule_from_date", nullable = false)
    private Instant walletRuleFromDate;

    @NotNull
    @Column(name = "wallet_rule_to_date", nullable = false)
    private Instant walletRuleToDate;

    @Column(name = "wallet_rule_udf_1")
    private String walletRuleUDF1;

    @Column(name = "wallet_rule_udf_2")
    private String walletRuleUDF2;

    @Column(name = "wallet_rule_udf_3")
    private String walletRuleUDF3;

    @Column(name = "wallet_rule_udf_4")
    private String walletRuleUDF4;

    @Column(name = "wallet_rule_udf_5")
    private String walletRuleUDF5;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JsonIgnoreProperties("walletRuleProductTypes")
    private ProductType productType;

    @ManyToOne
    @JsonIgnoreProperties("walletRulePayPartners")
    private PayPartner payPartner;

    @OneToMany(mappedBy = "walletRule")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WalletRuleRate> walletRuleRateWalletRules = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWalletRuleCode() {
        return walletRuleCode;
    }

    public WalletRule walletRuleCode(String walletRuleCode) {
        this.walletRuleCode = walletRuleCode;
        return this;
    }

    public void setWalletRuleCode(String walletRuleCode) {
        this.walletRuleCode = walletRuleCode;
    }

    public String getWalletRuleName() {
        return walletRuleName;
    }

    public WalletRule walletRuleName(String walletRuleName) {
        this.walletRuleName = walletRuleName;
        return this;
    }

    public void setWalletRuleName(String walletRuleName) {
        this.walletRuleName = walletRuleName;
    }

    public String getWalletRuleDesc() {
        return walletRuleDesc;
    }

    public WalletRule walletRuleDesc(String walletRuleDesc) {
        this.walletRuleDesc = walletRuleDesc;
        return this;
    }

    public void setWalletRuleDesc(String walletRuleDesc) {
        this.walletRuleDesc = walletRuleDesc;
    }

    public Instant getWalletRuleFromDate() {
        return walletRuleFromDate;
    }

    public WalletRule walletRuleFromDate(Instant walletRuleFromDate) {
        this.walletRuleFromDate = walletRuleFromDate;
        return this;
    }

    public void setWalletRuleFromDate(Instant walletRuleFromDate) {
        this.walletRuleFromDate = walletRuleFromDate;
    }

    public Instant getWalletRuleToDate() {
        return walletRuleToDate;
    }

    public WalletRule walletRuleToDate(Instant walletRuleToDate) {
        this.walletRuleToDate = walletRuleToDate;
        return this;
    }

    public void setWalletRuleToDate(Instant walletRuleToDate) {
        this.walletRuleToDate = walletRuleToDate;
    }

    public String getWalletRuleUDF1() {
        return walletRuleUDF1;
    }

    public WalletRule walletRuleUDF1(String walletRuleUDF1) {
        this.walletRuleUDF1 = walletRuleUDF1;
        return this;
    }

    public void setWalletRuleUDF1(String walletRuleUDF1) {
        this.walletRuleUDF1 = walletRuleUDF1;
    }

    public String getWalletRuleUDF2() {
        return walletRuleUDF2;
    }

    public WalletRule walletRuleUDF2(String walletRuleUDF2) {
        this.walletRuleUDF2 = walletRuleUDF2;
        return this;
    }

    public void setWalletRuleUDF2(String walletRuleUDF2) {
        this.walletRuleUDF2 = walletRuleUDF2;
    }

    public String getWalletRuleUDF3() {
        return walletRuleUDF3;
    }

    public WalletRule walletRuleUDF3(String walletRuleUDF3) {
        this.walletRuleUDF3 = walletRuleUDF3;
        return this;
    }

    public void setWalletRuleUDF3(String walletRuleUDF3) {
        this.walletRuleUDF3 = walletRuleUDF3;
    }

    public String getWalletRuleUDF4() {
        return walletRuleUDF4;
    }

    public WalletRule walletRuleUDF4(String walletRuleUDF4) {
        this.walletRuleUDF4 = walletRuleUDF4;
        return this;
    }

    public void setWalletRuleUDF4(String walletRuleUDF4) {
        this.walletRuleUDF4 = walletRuleUDF4;
    }

    public String getWalletRuleUDF5() {
        return walletRuleUDF5;
    }

    public WalletRule walletRuleUDF5(String walletRuleUDF5) {
        this.walletRuleUDF5 = walletRuleUDF5;
        return this;
    }

    public void setWalletRuleUDF5(String walletRuleUDF5) {
        this.walletRuleUDF5 = walletRuleUDF5;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public WalletRule isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public ProductType getProductType() {
        return productType;
    }

    public WalletRule productType(ProductType productType) {
        this.productType = productType;
        return this;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public PayPartner getPayPartner() {
        return payPartner;
    }

    public WalletRule payPartner(PayPartner payPartner) {
        this.payPartner = payPartner;
        return this;
    }

    public void setPayPartner(PayPartner payPartner) {
        this.payPartner = payPartner;
    }

    public Set<WalletRuleRate> getWalletRuleRateWalletRules() {
        return walletRuleRateWalletRules;
    }

    public WalletRule walletRuleRateWalletRules(Set<WalletRuleRate> walletRuleRates) {
        this.walletRuleRateWalletRules = walletRuleRates;
        return this;
    }

    public WalletRule addWalletRuleRateWalletRule(WalletRuleRate walletRuleRate) {
        this.walletRuleRateWalletRules.add(walletRuleRate);
        walletRuleRate.setWalletRule(this);
        return this;
    }

    public WalletRule removeWalletRuleRateWalletRule(WalletRuleRate walletRuleRate) {
        this.walletRuleRateWalletRules.remove(walletRuleRate);
        walletRuleRate.setWalletRule(null);
        return this;
    }

    public void setWalletRuleRateWalletRules(Set<WalletRuleRate> walletRuleRates) {
        this.walletRuleRateWalletRules = walletRuleRates;
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
        WalletRule walletRule = (WalletRule) o;
        if (walletRule.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), walletRule.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WalletRule{" +
            "id=" + getId() +
            ", walletRuleCode='" + getWalletRuleCode() + "'" +
            ", walletRuleName='" + getWalletRuleName() + "'" +
            ", walletRuleDesc='" + getWalletRuleDesc() + "'" +
            ", walletRuleFromDate='" + getWalletRuleFromDate() + "'" +
            ", walletRuleToDate='" + getWalletRuleToDate() + "'" +
            ", walletRuleUDF1='" + getWalletRuleUDF1() + "'" +
            ", walletRuleUDF2='" + getWalletRuleUDF2() + "'" +
            ", walletRuleUDF3='" + getWalletRuleUDF3() + "'" +
            ", walletRuleUDF4='" + getWalletRuleUDF4() + "'" +
            ", walletRuleUDF5='" + getWalletRuleUDF5() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
