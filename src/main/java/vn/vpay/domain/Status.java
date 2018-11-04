package vn.vpay.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Status.
 */
@Entity
@Table(name = "status")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Status extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "status_code", nullable = false, unique = true)
    private String statusCode;

    @Column(name = "status_value")
    private Integer statusValue;

    @Column(name = "status_name")
    private String statusName;

    @Column(name = "status_desc")
    private String statusDesc;

    @Column(name = "status_udf_1")
    private String statusUDF1;

    @Column(name = "status_udf_2")
    private String statusUDF2;

    @Column(name = "status_udf_3")
    private String statusUDF3;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public Status statusCode(String statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getStatusValue() {
        return statusValue;
    }

    public Status statusValue(Integer statusValue) {
        this.statusValue = statusValue;
        return this;
    }

    public void setStatusValue(Integer statusValue) {
        this.statusValue = statusValue;
    }

    public String getStatusName() {
        return statusName;
    }

    public Status statusName(String statusName) {
        this.statusName = statusName;
        return this;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public Status statusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
        return this;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getStatusUDF1() {
        return statusUDF1;
    }

    public Status statusUDF1(String statusUDF1) {
        this.statusUDF1 = statusUDF1;
        return this;
    }

    public void setStatusUDF1(String statusUDF1) {
        this.statusUDF1 = statusUDF1;
    }

    public String getStatusUDF2() {
        return statusUDF2;
    }

    public Status statusUDF2(String statusUDF2) {
        this.statusUDF2 = statusUDF2;
        return this;
    }

    public void setStatusUDF2(String statusUDF2) {
        this.statusUDF2 = statusUDF2;
    }

    public String getStatusUDF3() {
        return statusUDF3;
    }

    public Status statusUDF3(String statusUDF3) {
        this.statusUDF3 = statusUDF3;
        return this;
    }

    public void setStatusUDF3(String statusUDF3) {
        this.statusUDF3 = statusUDF3;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public Status isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
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
        Status status = (Status) o;
        if (status.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), status.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Status{" +
            "id=" + getId() +
            ", statusCode='" + getStatusCode() + "'" +
            ", statusValue=" + getStatusValue() +
            ", statusName='" + getStatusName() + "'" +
            ", statusDesc='" + getStatusDesc() + "'" +
            ", statusUDF1='" + getStatusUDF1() + "'" +
            ", statusUDF2='" + getStatusUDF2() + "'" +
            ", statusUDF3='" + getStatusUDF3() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
