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
 * A WalletTransaction.
 */
@Entity
@Table(name = "wallet_transaction")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WalletTransaction extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "wallet_trans_amount", nullable = false)
    private Long walletTransAmount;

    @NotNull
    @Column(name = "wallet_trans_date", nullable = false)
    private Instant walletTransDate;

    @Column(name = "wallet_trans_ref")
    private String walletTransRef;

    @Column(name = "wallet_trans_username")
    private String walletTransUsername;

    @Column(name = "wallet_trans_status")
    private String walletTransStatus;

    @Column(name = "wallet_trans_udf_1")
    private String walletTransUDF1;

    @Column(name = "wallet_trans_udf_2")
    private String walletTransUDF2;

    @Column(name = "wallet_trans_udf_3")
    private String walletTransUDF3;

    @Column(name = "wallet_trans_udf_4")
    private String walletTransUDF4;

    @Column(name = "wallet_trans_udf_5")
    private String walletTransUDF5;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JsonIgnoreProperties("walletTransWalletTransTypes")
    private WalletTransactionType walletTransactionType;

    @ManyToOne
    @JsonIgnoreProperties("walletTransWallets")
    private Wallet wallet;

    @OneToMany(mappedBy = "walletTransaction")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PartnerTransaction> partnerTransWalletTransactions = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWalletTransAmount() {
        return walletTransAmount;
    }

    public WalletTransaction walletTransAmount(Long walletTransAmount) {
        this.walletTransAmount = walletTransAmount;
        return this;
    }

    public void setWalletTransAmount(Long walletTransAmount) {
        this.walletTransAmount = walletTransAmount;
    }

    public Instant getWalletTransDate() {
        return walletTransDate;
    }

    public WalletTransaction walletTransDate(Instant walletTransDate) {
        this.walletTransDate = walletTransDate;
        return this;
    }

    public void setWalletTransDate(Instant walletTransDate) {
        this.walletTransDate = walletTransDate;
    }

    public String getWalletTransRef() {
        return walletTransRef;
    }

    public WalletTransaction walletTransRef(String walletTransRef) {
        this.walletTransRef = walletTransRef;
        return this;
    }

    public void setWalletTransRef(String walletTransRef) {
        this.walletTransRef = walletTransRef;
    }

    public String getWalletTransUsername() {
        return walletTransUsername;
    }

    public WalletTransaction walletTransUsername(String walletTransUsername) {
        this.walletTransUsername = walletTransUsername;
        return this;
    }

    public void setWalletTransUsername(String walletTransUsername) {
        this.walletTransUsername = walletTransUsername;
    }

    public String getWalletTransStatus() {
        return walletTransStatus;
    }

    public WalletTransaction walletTransStatus(String walletTransStatus) {
        this.walletTransStatus = walletTransStatus;
        return this;
    }

    public void setWalletTransStatus(String walletTransStatus) {
        this.walletTransStatus = walletTransStatus;
    }

    public String getWalletTransUDF1() {
        return walletTransUDF1;
    }

    public WalletTransaction walletTransUDF1(String walletTransUDF1) {
        this.walletTransUDF1 = walletTransUDF1;
        return this;
    }

    public void setWalletTransUDF1(String walletTransUDF1) {
        this.walletTransUDF1 = walletTransUDF1;
    }

    public String getWalletTransUDF2() {
        return walletTransUDF2;
    }

    public WalletTransaction walletTransUDF2(String walletTransUDF2) {
        this.walletTransUDF2 = walletTransUDF2;
        return this;
    }

    public void setWalletTransUDF2(String walletTransUDF2) {
        this.walletTransUDF2 = walletTransUDF2;
    }

    public String getWalletTransUDF3() {
        return walletTransUDF3;
    }

    public WalletTransaction walletTransUDF3(String walletTransUDF3) {
        this.walletTransUDF3 = walletTransUDF3;
        return this;
    }

    public void setWalletTransUDF3(String walletTransUDF3) {
        this.walletTransUDF3 = walletTransUDF3;
    }

    public String getWalletTransUDF4() {
        return walletTransUDF4;
    }

    public WalletTransaction walletTransUDF4(String walletTransUDF4) {
        this.walletTransUDF4 = walletTransUDF4;
        return this;
    }

    public void setWalletTransUDF4(String walletTransUDF4) {
        this.walletTransUDF4 = walletTransUDF4;
    }

    public String getWalletTransUDF5() {
        return walletTransUDF5;
    }

    public WalletTransaction walletTransUDF5(String walletTransUDF5) {
        this.walletTransUDF5 = walletTransUDF5;
        return this;
    }

    public void setWalletTransUDF5(String walletTransUDF5) {
        this.walletTransUDF5 = walletTransUDF5;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public WalletTransaction isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public WalletTransactionType getWalletTransactionType() {
        return walletTransactionType;
    }

    public WalletTransaction walletTransactionType(WalletTransactionType walletTransactionType) {
        this.walletTransactionType = walletTransactionType;
        return this;
    }

    public void setWalletTransactionType(WalletTransactionType walletTransactionType) {
        this.walletTransactionType = walletTransactionType;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public WalletTransaction wallet(Wallet wallet) {
        this.wallet = wallet;
        return this;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Set<PartnerTransaction> getPartnerTransWalletTransactions() {
        return partnerTransWalletTransactions;
    }

    public WalletTransaction partnerTransWalletTransactions(Set<PartnerTransaction> partnerTransactions) {
        this.partnerTransWalletTransactions = partnerTransactions;
        return this;
    }

    public WalletTransaction addPartnerTransWalletTransaction(PartnerTransaction partnerTransaction) {
        this.partnerTransWalletTransactions.add(partnerTransaction);
        partnerTransaction.setWalletTransaction(this);
        return this;
    }

    public WalletTransaction removePartnerTransWalletTransaction(PartnerTransaction partnerTransaction) {
        this.partnerTransWalletTransactions.remove(partnerTransaction);
        partnerTransaction.setWalletTransaction(null);
        return this;
    }

    public void setPartnerTransWalletTransactions(Set<PartnerTransaction> partnerTransactions) {
        this.partnerTransWalletTransactions = partnerTransactions;
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
        WalletTransaction walletTransaction = (WalletTransaction) o;
        if (walletTransaction.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), walletTransaction.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WalletTransaction{" +
            "id=" + getId() +
            ", walletTransAmount=" + getWalletTransAmount() +
            ", walletTransDate='" + getWalletTransDate() + "'" +
            ", walletTransRef='" + getWalletTransRef() + "'" +
            ", walletTransUsername='" + getWalletTransUsername() + "'" +
            ", walletTransStatus='" + getWalletTransStatus() + "'" +
            ", walletTransUDF1='" + getWalletTransUDF1() + "'" +
            ", walletTransUDF2='" + getWalletTransUDF2() + "'" +
            ", walletTransUDF3='" + getWalletTransUDF3() + "'" +
            ", walletTransUDF4='" + getWalletTransUDF4() + "'" +
            ", walletTransUDF5='" + getWalletTransUDF5() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
