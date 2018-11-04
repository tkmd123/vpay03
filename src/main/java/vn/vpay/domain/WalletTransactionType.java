package vn.vpay.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * DEBIT, CREDIT, CANCEL
 */
@ApiModel(description = "DEBIT, CREDIT, CANCEL")
@Entity
@Table(name = "wallet_transaction_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WalletTransactionType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "wallet_trans_type_code", nullable = false, unique = true)
    private String walletTransTypeCode;

    @NotNull
    @Column(name = "wallet_trans_type_name", nullable = false)
    private String walletTransTypeName;

    @Column(name = "wallet_trans_type_desc")
    private String walletTransTypeDesc;

    @NotNull
    @Column(name = "wallet_trans_type_flag", nullable = false)
    private Integer walletTransTypeFlag;

    @Column(name = "wallet_trans_type_udf_1")
    private String walletTransTypeUDF1;

    @Column(name = "wallet_trans_type_udf_2")
    private String walletTransTypeUDF2;

    @Column(name = "wallet_trans_type_udf_3")
    private String walletTransTypeUDF3;

    @Column(name = "wallet_trans_type_udf_4")
    private String walletTransTypeUDF4;

    @Column(name = "wallet_trans_type_udf_5")
    private String walletTransTypeUDF5;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "walletTransactionType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WalletTransaction> walletTransWalletTransTypes = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWalletTransTypeCode() {
        return walletTransTypeCode;
    }

    public WalletTransactionType walletTransTypeCode(String walletTransTypeCode) {
        this.walletTransTypeCode = walletTransTypeCode;
        return this;
    }

    public void setWalletTransTypeCode(String walletTransTypeCode) {
        this.walletTransTypeCode = walletTransTypeCode;
    }

    public String getWalletTransTypeName() {
        return walletTransTypeName;
    }

    public WalletTransactionType walletTransTypeName(String walletTransTypeName) {
        this.walletTransTypeName = walletTransTypeName;
        return this;
    }

    public void setWalletTransTypeName(String walletTransTypeName) {
        this.walletTransTypeName = walletTransTypeName;
    }

    public String getWalletTransTypeDesc() {
        return walletTransTypeDesc;
    }

    public WalletTransactionType walletTransTypeDesc(String walletTransTypeDesc) {
        this.walletTransTypeDesc = walletTransTypeDesc;
        return this;
    }

    public void setWalletTransTypeDesc(String walletTransTypeDesc) {
        this.walletTransTypeDesc = walletTransTypeDesc;
    }

    public Integer getWalletTransTypeFlag() {
        return walletTransTypeFlag;
    }

    public WalletTransactionType walletTransTypeFlag(Integer walletTransTypeFlag) {
        this.walletTransTypeFlag = walletTransTypeFlag;
        return this;
    }

    public void setWalletTransTypeFlag(Integer walletTransTypeFlag) {
        this.walletTransTypeFlag = walletTransTypeFlag;
    }

    public String getWalletTransTypeUDF1() {
        return walletTransTypeUDF1;
    }

    public WalletTransactionType walletTransTypeUDF1(String walletTransTypeUDF1) {
        this.walletTransTypeUDF1 = walletTransTypeUDF1;
        return this;
    }

    public void setWalletTransTypeUDF1(String walletTransTypeUDF1) {
        this.walletTransTypeUDF1 = walletTransTypeUDF1;
    }

    public String getWalletTransTypeUDF2() {
        return walletTransTypeUDF2;
    }

    public WalletTransactionType walletTransTypeUDF2(String walletTransTypeUDF2) {
        this.walletTransTypeUDF2 = walletTransTypeUDF2;
        return this;
    }

    public void setWalletTransTypeUDF2(String walletTransTypeUDF2) {
        this.walletTransTypeUDF2 = walletTransTypeUDF2;
    }

    public String getWalletTransTypeUDF3() {
        return walletTransTypeUDF3;
    }

    public WalletTransactionType walletTransTypeUDF3(String walletTransTypeUDF3) {
        this.walletTransTypeUDF3 = walletTransTypeUDF3;
        return this;
    }

    public void setWalletTransTypeUDF3(String walletTransTypeUDF3) {
        this.walletTransTypeUDF3 = walletTransTypeUDF3;
    }

    public String getWalletTransTypeUDF4() {
        return walletTransTypeUDF4;
    }

    public WalletTransactionType walletTransTypeUDF4(String walletTransTypeUDF4) {
        this.walletTransTypeUDF4 = walletTransTypeUDF4;
        return this;
    }

    public void setWalletTransTypeUDF4(String walletTransTypeUDF4) {
        this.walletTransTypeUDF4 = walletTransTypeUDF4;
    }

    public String getWalletTransTypeUDF5() {
        return walletTransTypeUDF5;
    }

    public WalletTransactionType walletTransTypeUDF5(String walletTransTypeUDF5) {
        this.walletTransTypeUDF5 = walletTransTypeUDF5;
        return this;
    }

    public void setWalletTransTypeUDF5(String walletTransTypeUDF5) {
        this.walletTransTypeUDF5 = walletTransTypeUDF5;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public WalletTransactionType isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Set<WalletTransaction> getWalletTransWalletTransTypes() {
        return walletTransWalletTransTypes;
    }

    public WalletTransactionType walletTransWalletTransTypes(Set<WalletTransaction> walletTransactions) {
        this.walletTransWalletTransTypes = walletTransactions;
        return this;
    }

    public WalletTransactionType addWalletTransWalletTransType(WalletTransaction walletTransaction) {
        this.walletTransWalletTransTypes.add(walletTransaction);
        walletTransaction.setWalletTransactionType(this);
        return this;
    }

    public WalletTransactionType removeWalletTransWalletTransType(WalletTransaction walletTransaction) {
        this.walletTransWalletTransTypes.remove(walletTransaction);
        walletTransaction.setWalletTransactionType(null);
        return this;
    }

    public void setWalletTransWalletTransTypes(Set<WalletTransaction> walletTransactions) {
        this.walletTransWalletTransTypes = walletTransactions;
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
        WalletTransactionType walletTransactionType = (WalletTransactionType) o;
        if (walletTransactionType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), walletTransactionType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WalletTransactionType{" +
            "id=" + getId() +
            ", walletTransTypeCode='" + getWalletTransTypeCode() + "'" +
            ", walletTransTypeName='" + getWalletTransTypeName() + "'" +
            ", walletTransTypeDesc='" + getWalletTransTypeDesc() + "'" +
            ", walletTransTypeFlag=" + getWalletTransTypeFlag() +
            ", walletTransTypeUDF1='" + getWalletTransTypeUDF1() + "'" +
            ", walletTransTypeUDF2='" + getWalletTransTypeUDF2() + "'" +
            ", walletTransTypeUDF3='" + getWalletTransTypeUDF3() + "'" +
            ", walletTransTypeUDF4='" + getWalletTransTypeUDF4() + "'" +
            ", walletTransTypeUDF5='" + getWalletTransTypeUDF5() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
