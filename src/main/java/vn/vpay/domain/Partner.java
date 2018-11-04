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
 * Partner: VTC, Viettel, Media
 */
@ApiModel(description = "Partner: VTC, Viettel, Media")
@Entity
@Table(name = "partner")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Partner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "partner_code", nullable = false, unique = true)
    private String partnerCode;

    @NotNull
    @Column(name = "partner_name", nullable = false)
    private String partnerName;

    @Column(name = "partner_desc")
    private String partnerDesc;

    @NotNull
    @Column(name = "partner_order", nullable = false)
    private Integer partnerOrder;

    @Column(name = "partner_api_username")
    private String partnerAPIUsername;

    @Column(name = "partner_api_password")
    private String partnerAPIPassword;

    @Column(name = "partner_url")
    private String partnerUrl;

    @Column(name = "partner_status")
    private String partnerStatus;

    @Column(name = "partner_udf_1")
    private String partnerUDF1;

    @Column(name = "partner_udf_2")
    private String partnerUDF2;

    @Column(name = "partner_udf_3")
    private String partnerUDF3;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "partner")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Wallet> walletPartners = new HashSet<>();
    @OneToMany(mappedBy = "partner")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PartnerLog> partnerLogPartners = new HashSet<>();
    @OneToMany(mappedBy = "partner")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PartnerTransaction> partnerTransPartners = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public Partner partnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
        return this;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public Partner partnerName(String partnerName) {
        this.partnerName = partnerName;
        return this;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerDesc() {
        return partnerDesc;
    }

    public Partner partnerDesc(String partnerDesc) {
        this.partnerDesc = partnerDesc;
        return this;
    }

    public void setPartnerDesc(String partnerDesc) {
        this.partnerDesc = partnerDesc;
    }

    public Integer getPartnerOrder() {
        return partnerOrder;
    }

    public Partner partnerOrder(Integer partnerOrder) {
        this.partnerOrder = partnerOrder;
        return this;
    }

    public void setPartnerOrder(Integer partnerOrder) {
        this.partnerOrder = partnerOrder;
    }

    public String getPartnerAPIUsername() {
        return partnerAPIUsername;
    }

    public Partner partnerAPIUsername(String partnerAPIUsername) {
        this.partnerAPIUsername = partnerAPIUsername;
        return this;
    }

    public void setPartnerAPIUsername(String partnerAPIUsername) {
        this.partnerAPIUsername = partnerAPIUsername;
    }

    public String getPartnerAPIPassword() {
        return partnerAPIPassword;
    }

    public Partner partnerAPIPassword(String partnerAPIPassword) {
        this.partnerAPIPassword = partnerAPIPassword;
        return this;
    }

    public void setPartnerAPIPassword(String partnerAPIPassword) {
        this.partnerAPIPassword = partnerAPIPassword;
    }

    public String getPartnerUrl() {
        return partnerUrl;
    }

    public Partner partnerUrl(String partnerUrl) {
        this.partnerUrl = partnerUrl;
        return this;
    }

    public void setPartnerUrl(String partnerUrl) {
        this.partnerUrl = partnerUrl;
    }

    public String getPartnerStatus() {
        return partnerStatus;
    }

    public Partner partnerStatus(String partnerStatus) {
        this.partnerStatus = partnerStatus;
        return this;
    }

    public void setPartnerStatus(String partnerStatus) {
        this.partnerStatus = partnerStatus;
    }

    public String getPartnerUDF1() {
        return partnerUDF1;
    }

    public Partner partnerUDF1(String partnerUDF1) {
        this.partnerUDF1 = partnerUDF1;
        return this;
    }

    public void setPartnerUDF1(String partnerUDF1) {
        this.partnerUDF1 = partnerUDF1;
    }

    public String getPartnerUDF2() {
        return partnerUDF2;
    }

    public Partner partnerUDF2(String partnerUDF2) {
        this.partnerUDF2 = partnerUDF2;
        return this;
    }

    public void setPartnerUDF2(String partnerUDF2) {
        this.partnerUDF2 = partnerUDF2;
    }

    public String getPartnerUDF3() {
        return partnerUDF3;
    }

    public Partner partnerUDF3(String partnerUDF3) {
        this.partnerUDF3 = partnerUDF3;
        return this;
    }

    public void setPartnerUDF3(String partnerUDF3) {
        this.partnerUDF3 = partnerUDF3;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public Partner isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Set<Wallet> getWalletPartners() {
        return walletPartners;
    }

    public Partner walletPartners(Set<Wallet> wallets) {
        this.walletPartners = wallets;
        return this;
    }

    public Partner addWalletPartner(Wallet wallet) {
        this.walletPartners.add(wallet);
        wallet.setPartner(this);
        return this;
    }

    public Partner removeWalletPartner(Wallet wallet) {
        this.walletPartners.remove(wallet);
        wallet.setPartner(null);
        return this;
    }

    public void setWalletPartners(Set<Wallet> wallets) {
        this.walletPartners = wallets;
    }

    public Set<PartnerLog> getPartnerLogPartners() {
        return partnerLogPartners;
    }

    public Partner partnerLogPartners(Set<PartnerLog> partnerLogs) {
        this.partnerLogPartners = partnerLogs;
        return this;
    }

    public Partner addPartnerLogPartner(PartnerLog partnerLog) {
        this.partnerLogPartners.add(partnerLog);
        partnerLog.setPartner(this);
        return this;
    }

    public Partner removePartnerLogPartner(PartnerLog partnerLog) {
        this.partnerLogPartners.remove(partnerLog);
        partnerLog.setPartner(null);
        return this;
    }

    public void setPartnerLogPartners(Set<PartnerLog> partnerLogs) {
        this.partnerLogPartners = partnerLogs;
    }

    public Set<PartnerTransaction> getPartnerTransPartners() {
        return partnerTransPartners;
    }

    public Partner partnerTransPartners(Set<PartnerTransaction> partnerTransactions) {
        this.partnerTransPartners = partnerTransactions;
        return this;
    }

    public Partner addPartnerTransPartner(PartnerTransaction partnerTransaction) {
        this.partnerTransPartners.add(partnerTransaction);
        partnerTransaction.setPartner(this);
        return this;
    }

    public Partner removePartnerTransPartner(PartnerTransaction partnerTransaction) {
        this.partnerTransPartners.remove(partnerTransaction);
        partnerTransaction.setPartner(null);
        return this;
    }

    public void setPartnerTransPartners(Set<PartnerTransaction> partnerTransactions) {
        this.partnerTransPartners = partnerTransactions;
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
        Partner partner = (Partner) o;
        if (partner.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), partner.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Partner{" +
            "id=" + getId() +
            ", partnerCode='" + getPartnerCode() + "'" +
            ", partnerName='" + getPartnerName() + "'" +
            ", partnerDesc='" + getPartnerDesc() + "'" +
            ", partnerOrder=" + getPartnerOrder() +
            ", partnerAPIUsername='" + getPartnerAPIUsername() + "'" +
            ", partnerAPIPassword='" + getPartnerAPIPassword() + "'" +
            ", partnerUrl='" + getPartnerUrl() + "'" +
            ", partnerStatus='" + getPartnerStatus() + "'" +
            ", partnerUDF1='" + getPartnerUDF1() + "'" +
            ", partnerUDF2='" + getPartnerUDF2() + "'" +
            ", partnerUDF3='" + getPartnerUDF3() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
