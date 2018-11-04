package vn.vpay.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Product: VN: 50k, VN: 100k
 * VT: 50k, VT: 100k
 */
@ApiModel(description = "Product: VN: 50k, VN: 100k VT: 50k, VT: 100k")
@Entity
@Table(name = "product_type_wallet")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProductTypeWallet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_type_wallet_udf_1")
    private String productTypeWalletUDF1;

    @Column(name = "product_type_wallet_udf_2")
    private String productTypeWalletUDF2;

    @Column(name = "product_type_wallet_udf_3")
    private String productTypeWalletUDF3;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JsonIgnoreProperties("productTypeWallets")
    private ProductType productType;

    @ManyToOne
    @JsonIgnoreProperties("walletProductTypes")
    private Wallet wallet;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductTypeWalletUDF1() {
        return productTypeWalletUDF1;
    }

    public ProductTypeWallet productTypeWalletUDF1(String productTypeWalletUDF1) {
        this.productTypeWalletUDF1 = productTypeWalletUDF1;
        return this;
    }

    public void setProductTypeWalletUDF1(String productTypeWalletUDF1) {
        this.productTypeWalletUDF1 = productTypeWalletUDF1;
    }

    public String getProductTypeWalletUDF2() {
        return productTypeWalletUDF2;
    }

    public ProductTypeWallet productTypeWalletUDF2(String productTypeWalletUDF2) {
        this.productTypeWalletUDF2 = productTypeWalletUDF2;
        return this;
    }

    public void setProductTypeWalletUDF2(String productTypeWalletUDF2) {
        this.productTypeWalletUDF2 = productTypeWalletUDF2;
    }

    public String getProductTypeWalletUDF3() {
        return productTypeWalletUDF3;
    }

    public ProductTypeWallet productTypeWalletUDF3(String productTypeWalletUDF3) {
        this.productTypeWalletUDF3 = productTypeWalletUDF3;
        return this;
    }

    public void setProductTypeWalletUDF3(String productTypeWalletUDF3) {
        this.productTypeWalletUDF3 = productTypeWalletUDF3;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public ProductTypeWallet isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public ProductType getProductType() {
        return productType;
    }

    public ProductTypeWallet productType(ProductType productType) {
        this.productType = productType;
        return this;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public ProductTypeWallet wallet(Wallet wallet) {
        this.wallet = wallet;
        return this;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
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
        ProductTypeWallet productTypeWallet = (ProductTypeWallet) o;
        if (productTypeWallet.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productTypeWallet.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductTypeWallet{" +
            "id=" + getId() +
            ", productTypeWalletUDF1='" + getProductTypeWalletUDF1() + "'" +
            ", productTypeWalletUDF2='" + getProductTypeWalletUDF2() + "'" +
            ", productTypeWalletUDF3='" + getProductTypeWalletUDF3() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
