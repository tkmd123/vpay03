package vn.vpay.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A PartnerTransaction.
 */
@Entity
@Table(name = "partner_transaction")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PartnerTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "partner_trans_amount", nullable = false)
    private Long partnerTransAmount;

    @NotNull
    @Column(name = "partner_trans_date", nullable = false)
    private Instant partnerTransDate;

    @Column(name = "partner_trans_ref")
    private String partnerTransRef;

    @Column(name = "partner_trans_username")
    private String partnerTransUsername;

    @Column(name = "partner_trans_status")
    private String partnerTransStatus;

    @Column(name = "partner_trans_udf_1")
    private String partnerTransUDF1;

    @Column(name = "partner_trans_udf_2")
    private String partnerTransUDF2;

    @Column(name = "partner_trans_udf_3")
    private String partnerTransUDF3;

    @Column(name = "partner_trans_udf_4")
    private String partnerTransUDF4;

    @Column(name = "partner_trans_udf_5")
    private String partnerTransUDF5;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JsonIgnoreProperties("partnerTransProductTypes")
    private ProductType productType;

    @ManyToOne
    @JsonIgnoreProperties("partnerTransPartners")
    private Partner partner;

    @ManyToOne
    @JsonIgnoreProperties("partnerTransWalletTransactions")
    private WalletTransaction walletTransaction;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPartnerTransAmount() {
        return partnerTransAmount;
    }

    public PartnerTransaction partnerTransAmount(Long partnerTransAmount) {
        this.partnerTransAmount = partnerTransAmount;
        return this;
    }

    public void setPartnerTransAmount(Long partnerTransAmount) {
        this.partnerTransAmount = partnerTransAmount;
    }

    public Instant getPartnerTransDate() {
        return partnerTransDate;
    }

    public PartnerTransaction partnerTransDate(Instant partnerTransDate) {
        this.partnerTransDate = partnerTransDate;
        return this;
    }

    public void setPartnerTransDate(Instant partnerTransDate) {
        this.partnerTransDate = partnerTransDate;
    }

    public String getPartnerTransRef() {
        return partnerTransRef;
    }

    public PartnerTransaction partnerTransRef(String partnerTransRef) {
        this.partnerTransRef = partnerTransRef;
        return this;
    }

    public void setPartnerTransRef(String partnerTransRef) {
        this.partnerTransRef = partnerTransRef;
    }

    public String getPartnerTransUsername() {
        return partnerTransUsername;
    }

    public PartnerTransaction partnerTransUsername(String partnerTransUsername) {
        this.partnerTransUsername = partnerTransUsername;
        return this;
    }

    public void setPartnerTransUsername(String partnerTransUsername) {
        this.partnerTransUsername = partnerTransUsername;
    }

    public String getPartnerTransStatus() {
        return partnerTransStatus;
    }

    public PartnerTransaction partnerTransStatus(String partnerTransStatus) {
        this.partnerTransStatus = partnerTransStatus;
        return this;
    }

    public void setPartnerTransStatus(String partnerTransStatus) {
        this.partnerTransStatus = partnerTransStatus;
    }

    public String getPartnerTransUDF1() {
        return partnerTransUDF1;
    }

    public PartnerTransaction partnerTransUDF1(String partnerTransUDF1) {
        this.partnerTransUDF1 = partnerTransUDF1;
        return this;
    }

    public void setPartnerTransUDF1(String partnerTransUDF1) {
        this.partnerTransUDF1 = partnerTransUDF1;
    }

    public String getPartnerTransUDF2() {
        return partnerTransUDF2;
    }

    public PartnerTransaction partnerTransUDF2(String partnerTransUDF2) {
        this.partnerTransUDF2 = partnerTransUDF2;
        return this;
    }

    public void setPartnerTransUDF2(String partnerTransUDF2) {
        this.partnerTransUDF2 = partnerTransUDF2;
    }

    public String getPartnerTransUDF3() {
        return partnerTransUDF3;
    }

    public PartnerTransaction partnerTransUDF3(String partnerTransUDF3) {
        this.partnerTransUDF3 = partnerTransUDF3;
        return this;
    }

    public void setPartnerTransUDF3(String partnerTransUDF3) {
        this.partnerTransUDF3 = partnerTransUDF3;
    }

    public String getPartnerTransUDF4() {
        return partnerTransUDF4;
    }

    public PartnerTransaction partnerTransUDF4(String partnerTransUDF4) {
        this.partnerTransUDF4 = partnerTransUDF4;
        return this;
    }

    public void setPartnerTransUDF4(String partnerTransUDF4) {
        this.partnerTransUDF4 = partnerTransUDF4;
    }

    public String getPartnerTransUDF5() {
        return partnerTransUDF5;
    }

    public PartnerTransaction partnerTransUDF5(String partnerTransUDF5) {
        this.partnerTransUDF5 = partnerTransUDF5;
        return this;
    }

    public void setPartnerTransUDF5(String partnerTransUDF5) {
        this.partnerTransUDF5 = partnerTransUDF5;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public PartnerTransaction isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public ProductType getProductType() {
        return productType;
    }

    public PartnerTransaction productType(ProductType productType) {
        this.productType = productType;
        return this;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Partner getPartner() {
        return partner;
    }

    public PartnerTransaction partner(Partner partner) {
        this.partner = partner;
        return this;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public WalletTransaction getWalletTransaction() {
        return walletTransaction;
    }

    public PartnerTransaction walletTransaction(WalletTransaction walletTransaction) {
        this.walletTransaction = walletTransaction;
        return this;
    }

    public void setWalletTransaction(WalletTransaction walletTransaction) {
        this.walletTransaction = walletTransaction;
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
        PartnerTransaction partnerTransaction = (PartnerTransaction) o;
        if (partnerTransaction.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), partnerTransaction.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PartnerTransaction{" +
            "id=" + getId() +
            ", partnerTransAmount=" + getPartnerTransAmount() +
            ", partnerTransDate='" + getPartnerTransDate() + "'" +
            ", partnerTransRef='" + getPartnerTransRef() + "'" +
            ", partnerTransUsername='" + getPartnerTransUsername() + "'" +
            ", partnerTransStatus='" + getPartnerTransStatus() + "'" +
            ", partnerTransUDF1='" + getPartnerTransUDF1() + "'" +
            ", partnerTransUDF2='" + getPartnerTransUDF2() + "'" +
            ", partnerTransUDF3='" + getPartnerTransUDF3() + "'" +
            ", partnerTransUDF4='" + getPartnerTransUDF4() + "'" +
            ", partnerTransUDF5='" + getPartnerTransUDF5() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
