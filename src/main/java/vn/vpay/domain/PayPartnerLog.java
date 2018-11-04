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
 * A PayPartnerLog.
 */
@Entity
@Table(name = "pay_partner_log")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PayPartnerLog extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pay_log_amount")
    private String payLogAmount;

    @Column(name = "pay_log_trans_ref")
    private String payLogTransRef;

    @Column(name = "pay_log_username")
    private String payLogUsername;

    @Column(name = "pay_log_product_type_code")
    private String payLogProductTypeCode;

    @Column(name = "pay_log_resule_code")
    private String payLogResuleCode;

    @Column(name = "pay_log_resule_desc")
    private String payLogResuleDesc;

    @Column(name = "pay_log_date")
    private Instant payLogDate;

    @Column(name = "pay_log_udf_1")
    private String payLogUDF1;

    @Column(name = "pay_log_udf_2")
    private String payLogUDF2;

    @Column(name = "pay_log_udf_3")
    private String payLogUDF3;

    @Column(name = "pay_log_udf_4")
    private String payLogUDF4;

    @Column(name = "pay_log_udf_5")
    private String payLogUDF5;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JsonIgnoreProperties("partnerLogPayPartners")
    private PayPartner payPartner;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayLogAmount() {
        return payLogAmount;
    }

    public PayPartnerLog payLogAmount(String payLogAmount) {
        this.payLogAmount = payLogAmount;
        return this;
    }

    public void setPayLogAmount(String payLogAmount) {
        this.payLogAmount = payLogAmount;
    }

    public String getPayLogTransRef() {
        return payLogTransRef;
    }

    public PayPartnerLog payLogTransRef(String payLogTransRef) {
        this.payLogTransRef = payLogTransRef;
        return this;
    }

    public void setPayLogTransRef(String payLogTransRef) {
        this.payLogTransRef = payLogTransRef;
    }

    public String getPayLogUsername() {
        return payLogUsername;
    }

    public PayPartnerLog payLogUsername(String payLogUsername) {
        this.payLogUsername = payLogUsername;
        return this;
    }

    public void setPayLogUsername(String payLogUsername) {
        this.payLogUsername = payLogUsername;
    }

    public String getPayLogProductTypeCode() {
        return payLogProductTypeCode;
    }

    public PayPartnerLog payLogProductTypeCode(String payLogProductTypeCode) {
        this.payLogProductTypeCode = payLogProductTypeCode;
        return this;
    }

    public void setPayLogProductTypeCode(String payLogProductTypeCode) {
        this.payLogProductTypeCode = payLogProductTypeCode;
    }

    public String getPayLogResuleCode() {
        return payLogResuleCode;
    }

    public PayPartnerLog payLogResuleCode(String payLogResuleCode) {
        this.payLogResuleCode = payLogResuleCode;
        return this;
    }

    public void setPayLogResuleCode(String payLogResuleCode) {
        this.payLogResuleCode = payLogResuleCode;
    }

    public String getPayLogResuleDesc() {
        return payLogResuleDesc;
    }

    public PayPartnerLog payLogResuleDesc(String payLogResuleDesc) {
        this.payLogResuleDesc = payLogResuleDesc;
        return this;
    }

    public void setPayLogResuleDesc(String payLogResuleDesc) {
        this.payLogResuleDesc = payLogResuleDesc;
    }

    public Instant getPayLogDate() {
        return payLogDate;
    }

    public PayPartnerLog payLogDate(Instant payLogDate) {
        this.payLogDate = payLogDate;
        return this;
    }

    public void setPayLogDate(Instant payLogDate) {
        this.payLogDate = payLogDate;
    }

    public String getPayLogUDF1() {
        return payLogUDF1;
    }

    public PayPartnerLog payLogUDF1(String payLogUDF1) {
        this.payLogUDF1 = payLogUDF1;
        return this;
    }

    public void setPayLogUDF1(String payLogUDF1) {
        this.payLogUDF1 = payLogUDF1;
    }

    public String getPayLogUDF2() {
        return payLogUDF2;
    }

    public PayPartnerLog payLogUDF2(String payLogUDF2) {
        this.payLogUDF2 = payLogUDF2;
        return this;
    }

    public void setPayLogUDF2(String payLogUDF2) {
        this.payLogUDF2 = payLogUDF2;
    }

    public String getPayLogUDF3() {
        return payLogUDF3;
    }

    public PayPartnerLog payLogUDF3(String payLogUDF3) {
        this.payLogUDF3 = payLogUDF3;
        return this;
    }

    public void setPayLogUDF3(String payLogUDF3) {
        this.payLogUDF3 = payLogUDF3;
    }

    public String getPayLogUDF4() {
        return payLogUDF4;
    }

    public PayPartnerLog payLogUDF4(String payLogUDF4) {
        this.payLogUDF4 = payLogUDF4;
        return this;
    }

    public void setPayLogUDF4(String payLogUDF4) {
        this.payLogUDF4 = payLogUDF4;
    }

    public String getPayLogUDF5() {
        return payLogUDF5;
    }

    public PayPartnerLog payLogUDF5(String payLogUDF5) {
        this.payLogUDF5 = payLogUDF5;
        return this;
    }

    public void setPayLogUDF5(String payLogUDF5) {
        this.payLogUDF5 = payLogUDF5;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public PayPartnerLog isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public PayPartner getPayPartner() {
        return payPartner;
    }

    public PayPartnerLog payPartner(PayPartner payPartner) {
        this.payPartner = payPartner;
        return this;
    }

    public void setPayPartner(PayPartner payPartner) {
        this.payPartner = payPartner;
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
        PayPartnerLog payPartnerLog = (PayPartnerLog) o;
        if (payPartnerLog.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), payPartnerLog.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PayPartnerLog{" +
            "id=" + getId() +
            ", payLogAmount='" + getPayLogAmount() + "'" +
            ", payLogTransRef='" + getPayLogTransRef() + "'" +
            ", payLogUsername='" + getPayLogUsername() + "'" +
            ", payLogProductTypeCode='" + getPayLogProductTypeCode() + "'" +
            ", payLogResuleCode='" + getPayLogResuleCode() + "'" +
            ", payLogResuleDesc='" + getPayLogResuleDesc() + "'" +
            ", payLogDate='" + getPayLogDate() + "'" +
            ", payLogUDF1='" + getPayLogUDF1() + "'" +
            ", payLogUDF2='" + getPayLogUDF2() + "'" +
            ", payLogUDF3='" + getPayLogUDF3() + "'" +
            ", payLogUDF4='" + getPayLogUDF4() + "'" +
            ", payLogUDF5='" + getPayLogUDF5() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
