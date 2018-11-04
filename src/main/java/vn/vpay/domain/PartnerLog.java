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
 * A PartnerLog.
 */
@Entity
@Table(name = "partner_log")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PartnerLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "partner_log_amount")
    private String partnerLogAmount;

    @Column(name = "partner_log_trans_ref")
    private String partnerLogTransRef;

    @Column(name = "partner_log_username")
    private String partnerLogUsername;

    @Column(name = "partner_log_product_type_code")
    private String partnerLogProductTypeCode;

    @Column(name = "partner_log_result_code")
    private String partnerLogResultCode;

    @Column(name = "partner_log_result_desc")
    private String partnerLogResultDesc;

    @Column(name = "partner_log_date")
    private Instant partnerLogDate;

    @Column(name = "partner_log_udf_1")
    private String partnerLogUDF1;

    @Column(name = "partner_log_udf_2")
    private String partnerLogUDF2;

    @Column(name = "partner_log_udf_3")
    private String partnerLogUDF3;

    @Column(name = "partner_log_udf_4")
    private String partnerLogUDF4;

    @Column(name = "partner_log_udf_5")
    private String partnerLogUDF5;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JsonIgnoreProperties("partnerLogPartners")
    private Partner partner;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartnerLogAmount() {
        return partnerLogAmount;
    }

    public PartnerLog partnerLogAmount(String partnerLogAmount) {
        this.partnerLogAmount = partnerLogAmount;
        return this;
    }

    public void setPartnerLogAmount(String partnerLogAmount) {
        this.partnerLogAmount = partnerLogAmount;
    }

    public String getPartnerLogTransRef() {
        return partnerLogTransRef;
    }

    public PartnerLog partnerLogTransRef(String partnerLogTransRef) {
        this.partnerLogTransRef = partnerLogTransRef;
        return this;
    }

    public void setPartnerLogTransRef(String partnerLogTransRef) {
        this.partnerLogTransRef = partnerLogTransRef;
    }

    public String getPartnerLogUsername() {
        return partnerLogUsername;
    }

    public PartnerLog partnerLogUsername(String partnerLogUsername) {
        this.partnerLogUsername = partnerLogUsername;
        return this;
    }

    public void setPartnerLogUsername(String partnerLogUsername) {
        this.partnerLogUsername = partnerLogUsername;
    }

    public String getPartnerLogProductTypeCode() {
        return partnerLogProductTypeCode;
    }

    public PartnerLog partnerLogProductTypeCode(String partnerLogProductTypeCode) {
        this.partnerLogProductTypeCode = partnerLogProductTypeCode;
        return this;
    }

    public void setPartnerLogProductTypeCode(String partnerLogProductTypeCode) {
        this.partnerLogProductTypeCode = partnerLogProductTypeCode;
    }

    public String getPartnerLogResultCode() {
        return partnerLogResultCode;
    }

    public PartnerLog partnerLogResultCode(String partnerLogResultCode) {
        this.partnerLogResultCode = partnerLogResultCode;
        return this;
    }

    public void setPartnerLogResultCode(String partnerLogResultCode) {
        this.partnerLogResultCode = partnerLogResultCode;
    }

    public String getPartnerLogResultDesc() {
        return partnerLogResultDesc;
    }

    public PartnerLog partnerLogResultDesc(String partnerLogResultDesc) {
        this.partnerLogResultDesc = partnerLogResultDesc;
        return this;
    }

    public void setPartnerLogResultDesc(String partnerLogResultDesc) {
        this.partnerLogResultDesc = partnerLogResultDesc;
    }

    public Instant getPartnerLogDate() {
        return partnerLogDate;
    }

    public PartnerLog partnerLogDate(Instant partnerLogDate) {
        this.partnerLogDate = partnerLogDate;
        return this;
    }

    public void setPartnerLogDate(Instant partnerLogDate) {
        this.partnerLogDate = partnerLogDate;
    }

    public String getPartnerLogUDF1() {
        return partnerLogUDF1;
    }

    public PartnerLog partnerLogUDF1(String partnerLogUDF1) {
        this.partnerLogUDF1 = partnerLogUDF1;
        return this;
    }

    public void setPartnerLogUDF1(String partnerLogUDF1) {
        this.partnerLogUDF1 = partnerLogUDF1;
    }

    public String getPartnerLogUDF2() {
        return partnerLogUDF2;
    }

    public PartnerLog partnerLogUDF2(String partnerLogUDF2) {
        this.partnerLogUDF2 = partnerLogUDF2;
        return this;
    }

    public void setPartnerLogUDF2(String partnerLogUDF2) {
        this.partnerLogUDF2 = partnerLogUDF2;
    }

    public String getPartnerLogUDF3() {
        return partnerLogUDF3;
    }

    public PartnerLog partnerLogUDF3(String partnerLogUDF3) {
        this.partnerLogUDF3 = partnerLogUDF3;
        return this;
    }

    public void setPartnerLogUDF3(String partnerLogUDF3) {
        this.partnerLogUDF3 = partnerLogUDF3;
    }

    public String getPartnerLogUDF4() {
        return partnerLogUDF4;
    }

    public PartnerLog partnerLogUDF4(String partnerLogUDF4) {
        this.partnerLogUDF4 = partnerLogUDF4;
        return this;
    }

    public void setPartnerLogUDF4(String partnerLogUDF4) {
        this.partnerLogUDF4 = partnerLogUDF4;
    }

    public String getPartnerLogUDF5() {
        return partnerLogUDF5;
    }

    public PartnerLog partnerLogUDF5(String partnerLogUDF5) {
        this.partnerLogUDF5 = partnerLogUDF5;
        return this;
    }

    public void setPartnerLogUDF5(String partnerLogUDF5) {
        this.partnerLogUDF5 = partnerLogUDF5;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public PartnerLog isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Partner getPartner() {
        return partner;
    }

    public PartnerLog partner(Partner partner) {
        this.partner = partner;
        return this;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
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
        PartnerLog partnerLog = (PartnerLog) o;
        if (partnerLog.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), partnerLog.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PartnerLog{" +
            "id=" + getId() +
            ", partnerLogAmount='" + getPartnerLogAmount() + "'" +
            ", partnerLogTransRef='" + getPartnerLogTransRef() + "'" +
            ", partnerLogUsername='" + getPartnerLogUsername() + "'" +
            ", partnerLogProductTypeCode='" + getPartnerLogProductTypeCode() + "'" +
            ", partnerLogResultCode='" + getPartnerLogResultCode() + "'" +
            ", partnerLogResultDesc='" + getPartnerLogResultDesc() + "'" +
            ", partnerLogDate='" + getPartnerLogDate() + "'" +
            ", partnerLogUDF1='" + getPartnerLogUDF1() + "'" +
            ", partnerLogUDF2='" + getPartnerLogUDF2() + "'" +
            ", partnerLogUDF3='" + getPartnerLogUDF3() + "'" +
            ", partnerLogUDF4='" + getPartnerLogUDF4() + "'" +
            ", partnerLogUDF5='" + getPartnerLogUDF5() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
