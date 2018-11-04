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
 * ProductType: MOBILE, GAME
 */
@ApiModel(description = "ProductType: MOBILE, GAME")
@Entity
@Table(name = "product_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProductType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "product_type_code", nullable = false, unique = true)
    private String productTypeCode;

    @NotNull
    @Column(name = "product_type_name", nullable = false)
    private String productTypeName;

    @Column(name = "product_type_desc")
    private String productTypeDesc;

    @NotNull
    @Column(name = "product_type_category", nullable = false)
    private String productTypeCategory;

    @Column(name = "product_udf_1")
    private String productUDF1;

    @Column(name = "product_udf_2")
    private String productUDF2;

    @Column(name = "product_udf_3")
    private String productUDF3;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "productType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Product> productProductTypes = new HashSet<>();
    @OneToMany(mappedBy = "productType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Wallet> walletProductTypes = new HashSet<>();
    @OneToMany(mappedBy = "productType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WalletRule> walletRuleProductTypes = new HashSet<>();
    @OneToMany(mappedBy = "productType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PartnerTransaction> partnerTransProductTypes = new HashSet<>();
    @OneToMany(mappedBy = "productType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ProductTypeWallet> productTypeWallets = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductTypeCode() {
        return productTypeCode;
    }

    public ProductType productTypeCode(String productTypeCode) {
        this.productTypeCode = productTypeCode;
        return this;
    }

    public void setProductTypeCode(String productTypeCode) {
        this.productTypeCode = productTypeCode;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public ProductType productTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
        return this;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getProductTypeDesc() {
        return productTypeDesc;
    }

    public ProductType productTypeDesc(String productTypeDesc) {
        this.productTypeDesc = productTypeDesc;
        return this;
    }

    public void setProductTypeDesc(String productTypeDesc) {
        this.productTypeDesc = productTypeDesc;
    }

    public String getProductTypeCategory() {
        return productTypeCategory;
    }

    public ProductType productTypeCategory(String productTypeCategory) {
        this.productTypeCategory = productTypeCategory;
        return this;
    }

    public void setProductTypeCategory(String productTypeCategory) {
        this.productTypeCategory = productTypeCategory;
    }

    public String getProductUDF1() {
        return productUDF1;
    }

    public ProductType productUDF1(String productUDF1) {
        this.productUDF1 = productUDF1;
        return this;
    }

    public void setProductUDF1(String productUDF1) {
        this.productUDF1 = productUDF1;
    }

    public String getProductUDF2() {
        return productUDF2;
    }

    public ProductType productUDF2(String productUDF2) {
        this.productUDF2 = productUDF2;
        return this;
    }

    public void setProductUDF2(String productUDF2) {
        this.productUDF2 = productUDF2;
    }

    public String getProductUDF3() {
        return productUDF3;
    }

    public ProductType productUDF3(String productUDF3) {
        this.productUDF3 = productUDF3;
        return this;
    }

    public void setProductUDF3(String productUDF3) {
        this.productUDF3 = productUDF3;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public ProductType isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Set<Product> getProductProductTypes() {
        return productProductTypes;
    }

    public ProductType productProductTypes(Set<Product> products) {
        this.productProductTypes = products;
        return this;
    }

    public ProductType addProductProductType(Product product) {
        this.productProductTypes.add(product);
        product.setProductType(this);
        return this;
    }

    public ProductType removeProductProductType(Product product) {
        this.productProductTypes.remove(product);
        product.setProductType(null);
        return this;
    }

    public void setProductProductTypes(Set<Product> products) {
        this.productProductTypes = products;
    }

    public Set<Wallet> getWalletProductTypes() {
        return walletProductTypes;
    }

    public ProductType walletProductTypes(Set<Wallet> wallets) {
        this.walletProductTypes = wallets;
        return this;
    }

    public ProductType addWalletProductType(Wallet wallet) {
        this.walletProductTypes.add(wallet);
        wallet.setProductType(this);
        return this;
    }

    public ProductType removeWalletProductType(Wallet wallet) {
        this.walletProductTypes.remove(wallet);
        wallet.setProductType(null);
        return this;
    }

    public void setWalletProductTypes(Set<Wallet> wallets) {
        this.walletProductTypes = wallets;
    }

    public Set<WalletRule> getWalletRuleProductTypes() {
        return walletRuleProductTypes;
    }

    public ProductType walletRuleProductTypes(Set<WalletRule> walletRules) {
        this.walletRuleProductTypes = walletRules;
        return this;
    }

    public ProductType addWalletRuleProductType(WalletRule walletRule) {
        this.walletRuleProductTypes.add(walletRule);
        walletRule.setProductType(this);
        return this;
    }

    public ProductType removeWalletRuleProductType(WalletRule walletRule) {
        this.walletRuleProductTypes.remove(walletRule);
        walletRule.setProductType(null);
        return this;
    }

    public void setWalletRuleProductTypes(Set<WalletRule> walletRules) {
        this.walletRuleProductTypes = walletRules;
    }

    public Set<PartnerTransaction> getPartnerTransProductTypes() {
        return partnerTransProductTypes;
    }

    public ProductType partnerTransProductTypes(Set<PartnerTransaction> partnerTransactions) {
        this.partnerTransProductTypes = partnerTransactions;
        return this;
    }

    public ProductType addPartnerTransProductType(PartnerTransaction partnerTransaction) {
        this.partnerTransProductTypes.add(partnerTransaction);
        partnerTransaction.setProductType(this);
        return this;
    }

    public ProductType removePartnerTransProductType(PartnerTransaction partnerTransaction) {
        this.partnerTransProductTypes.remove(partnerTransaction);
        partnerTransaction.setProductType(null);
        return this;
    }

    public void setPartnerTransProductTypes(Set<PartnerTransaction> partnerTransactions) {
        this.partnerTransProductTypes = partnerTransactions;
    }

    public Set<ProductTypeWallet> getProductTypeWallets() {
        return productTypeWallets;
    }

    public ProductType productTypeWallets(Set<ProductTypeWallet> productTypeWallets) {
        this.productTypeWallets = productTypeWallets;
        return this;
    }

    public ProductType addProductTypeWallet(ProductTypeWallet productTypeWallet) {
        this.productTypeWallets.add(productTypeWallet);
        productTypeWallet.setProductType(this);
        return this;
    }

    public ProductType removeProductTypeWallet(ProductTypeWallet productTypeWallet) {
        this.productTypeWallets.remove(productTypeWallet);
        productTypeWallet.setProductType(null);
        return this;
    }

    public void setProductTypeWallets(Set<ProductTypeWallet> productTypeWallets) {
        this.productTypeWallets = productTypeWallets;
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
        ProductType productType = (ProductType) o;
        if (productType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductType{" +
            "id=" + getId() +
            ", productTypeCode='" + getProductTypeCode() + "'" +
            ", productTypeName='" + getProductTypeName() + "'" +
            ", productTypeDesc='" + getProductTypeDesc() + "'" +
            ", productTypeCategory='" + getProductTypeCategory() + "'" +
            ", productUDF1='" + getProductUDF1() + "'" +
            ", productUDF2='" + getProductUDF2() + "'" +
            ", productUDF3='" + getProductUDF3() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
