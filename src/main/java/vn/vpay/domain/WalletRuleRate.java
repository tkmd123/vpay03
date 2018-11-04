package vn.vpay.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A WalletRuleRate.
 */
@Entity
@Table(name = "wallet_rule_rate")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WalletRuleRate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "wallet_rule_rate_code", nullable = false, unique = true)
    private String walletRuleRateCode;

    @NotNull
    @Column(name = "wallet_rule_rate_name", nullable = false)
    private String walletRuleRateName;

    @Column(name = "wallet_rule_rate_desc")
    private String walletRuleRateDesc;

    @NotNull
    @Column(name = "wallet_rule_rate_from_value", nullable = false)
    private Long walletRuleRateFromValue;

    @NotNull
    @Column(name = "wallet_rule_rate_to_value", nullable = false)
    private Long walletRuleRateToValue;

    @NotNull
    @Column(name = "wallet_rule_rate_discount", nullable = false)
    private Long walletRuleRateDiscount;

    @Column(name = "wallet_rule_rate_udf_1")
    private String walletRuleRateUDF1;

    @Column(name = "wallet_rule_rate_udf_2")
    private String walletRuleRateUDF2;

    @Column(name = "wallet_rule_rate_udf_3")
    private String walletRuleRateUDF3;

    @Column(name = "wallet_rule_rate_udf_4")
    private String walletRuleRateUDF4;

    @Column(name = "wallet_rule_rate_udf_5")
    private String walletRuleRateUDF5;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JsonIgnoreProperties("walletRuleRateWalletRules")
    private WalletRule walletRule;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWalletRuleRateCode() {
        return walletRuleRateCode;
    }

    public WalletRuleRate walletRuleRateCode(String walletRuleRateCode) {
        this.walletRuleRateCode = walletRuleRateCode;
        return this;
    }

    public void setWalletRuleRateCode(String walletRuleRateCode) {
        this.walletRuleRateCode = walletRuleRateCode;
    }

    public String getWalletRuleRateName() {
        return walletRuleRateName;
    }

    public WalletRuleRate walletRuleRateName(String walletRuleRateName) {
        this.walletRuleRateName = walletRuleRateName;
        return this;
    }

    public void setWalletRuleRateName(String walletRuleRateName) {
        this.walletRuleRateName = walletRuleRateName;
    }

    public String getWalletRuleRateDesc() {
        return walletRuleRateDesc;
    }

    public WalletRuleRate walletRuleRateDesc(String walletRuleRateDesc) {
        this.walletRuleRateDesc = walletRuleRateDesc;
        return this;
    }

    public void setWalletRuleRateDesc(String walletRuleRateDesc) {
        this.walletRuleRateDesc = walletRuleRateDesc;
    }

    public Long getWalletRuleRateFromValue() {
        return walletRuleRateFromValue;
    }

    public WalletRuleRate walletRuleRateFromValue(Long walletRuleRateFromValue) {
        this.walletRuleRateFromValue = walletRuleRateFromValue;
        return this;
    }

    public void setWalletRuleRateFromValue(Long walletRuleRateFromValue) {
        this.walletRuleRateFromValue = walletRuleRateFromValue;
    }

    public Long getWalletRuleRateToValue() {
        return walletRuleRateToValue;
    }

    public WalletRuleRate walletRuleRateToValue(Long walletRuleRateToValue) {
        this.walletRuleRateToValue = walletRuleRateToValue;
        return this;
    }

    public void setWalletRuleRateToValue(Long walletRuleRateToValue) {
        this.walletRuleRateToValue = walletRuleRateToValue;
    }

    public Long getWalletRuleRateDiscount() {
        return walletRuleRateDiscount;
    }

    public WalletRuleRate walletRuleRateDiscount(Long walletRuleRateDiscount) {
        this.walletRuleRateDiscount = walletRuleRateDiscount;
        return this;
    }

    public void setWalletRuleRateDiscount(Long walletRuleRateDiscount) {
        this.walletRuleRateDiscount = walletRuleRateDiscount;
    }

    public String getWalletRuleRateUDF1() {
        return walletRuleRateUDF1;
    }

    public WalletRuleRate walletRuleRateUDF1(String walletRuleRateUDF1) {
        this.walletRuleRateUDF1 = walletRuleRateUDF1;
        return this;
    }

    public void setWalletRuleRateUDF1(String walletRuleRateUDF1) {
        this.walletRuleRateUDF1 = walletRuleRateUDF1;
    }

    public String getWalletRuleRateUDF2() {
        return walletRuleRateUDF2;
    }

    public WalletRuleRate walletRuleRateUDF2(String walletRuleRateUDF2) {
        this.walletRuleRateUDF2 = walletRuleRateUDF2;
        return this;
    }

    public void setWalletRuleRateUDF2(String walletRuleRateUDF2) {
        this.walletRuleRateUDF2 = walletRuleRateUDF2;
    }

    public String getWalletRuleRateUDF3() {
        return walletRuleRateUDF3;
    }

    public WalletRuleRate walletRuleRateUDF3(String walletRuleRateUDF3) {
        this.walletRuleRateUDF3 = walletRuleRateUDF3;
        return this;
    }

    public void setWalletRuleRateUDF3(String walletRuleRateUDF3) {
        this.walletRuleRateUDF3 = walletRuleRateUDF3;
    }

    public String getWalletRuleRateUDF4() {
        return walletRuleRateUDF4;
    }

    public WalletRuleRate walletRuleRateUDF4(String walletRuleRateUDF4) {
        this.walletRuleRateUDF4 = walletRuleRateUDF4;
        return this;
    }

    public void setWalletRuleRateUDF4(String walletRuleRateUDF4) {
        this.walletRuleRateUDF4 = walletRuleRateUDF4;
    }

    public String getWalletRuleRateUDF5() {
        return walletRuleRateUDF5;
    }

    public WalletRuleRate walletRuleRateUDF5(String walletRuleRateUDF5) {
        this.walletRuleRateUDF5 = walletRuleRateUDF5;
        return this;
    }

    public void setWalletRuleRateUDF5(String walletRuleRateUDF5) {
        this.walletRuleRateUDF5 = walletRuleRateUDF5;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public WalletRuleRate isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public WalletRule getWalletRule() {
        return walletRule;
    }

    public WalletRuleRate walletRule(WalletRule walletRule) {
        this.walletRule = walletRule;
        return this;
    }

    public void setWalletRule(WalletRule walletRule) {
        this.walletRule = walletRule;
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
        WalletRuleRate walletRuleRate = (WalletRuleRate) o;
        if (walletRuleRate.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), walletRuleRate.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WalletRuleRate{" +
            "id=" + getId() +
            ", walletRuleRateCode='" + getWalletRuleRateCode() + "'" +
            ", walletRuleRateName='" + getWalletRuleRateName() + "'" +
            ", walletRuleRateDesc='" + getWalletRuleRateDesc() + "'" +
            ", walletRuleRateFromValue=" + getWalletRuleRateFromValue() +
            ", walletRuleRateToValue=" + getWalletRuleRateToValue() +
            ", walletRuleRateDiscount=" + getWalletRuleRateDiscount() +
            ", walletRuleRateUDF1='" + getWalletRuleRateUDF1() + "'" +
            ", walletRuleRateUDF2='" + getWalletRuleRateUDF2() + "'" +
            ", walletRuleRateUDF3='" + getWalletRuleRateUDF3() + "'" +
            ", walletRuleRateUDF4='" + getWalletRuleRateUDF4() + "'" +
            ", walletRuleRateUDF5='" + getWalletRuleRateUDF5() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
