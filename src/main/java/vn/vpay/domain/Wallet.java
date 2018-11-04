package vn.vpay.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Wallet.
 */
@Entity
@Table(name = "wallet")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Wallet extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "wallet_number", nullable = false, unique = true)
    private String walletNumber;

    @NotNull
    @Column(name = "wallet_is_active", nullable = false)
    private Boolean walletIsActive;

    @Column(name = "wallet_desc")
    private String walletDesc;

    @Column(name = "wallet_udf_1")
    private String walletUDF1;

    @Column(name = "wallet_udf_2")
    private String walletUDF2;

    @Column(name = "wallet_udf_3")
    private String walletUDF3;

    @Column(name = "wallet_udf_4")
    private String walletUDF4;

    @Column(name = "wallet_udf_5")
    private String walletUDF5;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JsonIgnoreProperties("walletProductTypes")
    private ProductType productType;

    @ManyToOne
    @JsonIgnoreProperties("walletPartners")
    private Partner partner;

    @ManyToOne
    @JsonIgnoreProperties("walletPayPartners")
    private PayPartner payPartner;

    @OneToMany(mappedBy = "wallet")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WalletTransaction> walletTransWallets = new HashSet<>();
    @OneToMany(mappedBy = "wallet")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ProductTypeWallet> walletProductTypes = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWalletNumber() {
        return walletNumber;
    }

    public Wallet walletNumber(String walletNumber) {
        this.walletNumber = walletNumber;
        return this;
    }

    public void setWalletNumber(String walletNumber) {
        this.walletNumber = walletNumber;
    }

    public Boolean isWalletIsActive() {
        return walletIsActive;
    }

    public Wallet walletIsActive(Boolean walletIsActive) {
        this.walletIsActive = walletIsActive;
        return this;
    }

    public void setWalletIsActive(Boolean walletIsActive) {
        this.walletIsActive = walletIsActive;
    }

    public String getWalletDesc() {
        return walletDesc;
    }

    public Wallet walletDesc(String walletDesc) {
        this.walletDesc = walletDesc;
        return this;
    }

    public void setWalletDesc(String walletDesc) {
        this.walletDesc = walletDesc;
    }

    public String getWalletUDF1() {
        return walletUDF1;
    }

    public Wallet walletUDF1(String walletUDF1) {
        this.walletUDF1 = walletUDF1;
        return this;
    }

    public void setWalletUDF1(String walletUDF1) {
        this.walletUDF1 = walletUDF1;
    }

    public String getWalletUDF2() {
        return walletUDF2;
    }

    public Wallet walletUDF2(String walletUDF2) {
        this.walletUDF2 = walletUDF2;
        return this;
    }

    public void setWalletUDF2(String walletUDF2) {
        this.walletUDF2 = walletUDF2;
    }

    public String getWalletUDF3() {
        return walletUDF3;
    }

    public Wallet walletUDF3(String walletUDF3) {
        this.walletUDF3 = walletUDF3;
        return this;
    }

    public void setWalletUDF3(String walletUDF3) {
        this.walletUDF3 = walletUDF3;
    }

    public String getWalletUDF4() {
        return walletUDF4;
    }

    public Wallet walletUDF4(String walletUDF4) {
        this.walletUDF4 = walletUDF4;
        return this;
    }

    public void setWalletUDF4(String walletUDF4) {
        this.walletUDF4 = walletUDF4;
    }

    public String getWalletUDF5() {
        return walletUDF5;
    }

    public Wallet walletUDF5(String walletUDF5) {
        this.walletUDF5 = walletUDF5;
        return this;
    }

    public void setWalletUDF5(String walletUDF5) {
        this.walletUDF5 = walletUDF5;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public Wallet isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public ProductType getProductType() {
        return productType;
    }

    public Wallet productType(ProductType productType) {
        this.productType = productType;
        return this;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Partner getPartner() {
        return partner;
    }

    public Wallet partner(Partner partner) {
        this.partner = partner;
        return this;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public PayPartner getPayPartner() {
        return payPartner;
    }

    public Wallet payPartner(PayPartner payPartner) {
        this.payPartner = payPartner;
        return this;
    }

    public void setPayPartner(PayPartner payPartner) {
        this.payPartner = payPartner;
    }

    public Set<WalletTransaction> getWalletTransWallets() {
        return walletTransWallets;
    }

    public Wallet walletTransWallets(Set<WalletTransaction> walletTransactions) {
        this.walletTransWallets = walletTransactions;
        return this;
    }

    public Wallet addWalletTransWallet(WalletTransaction walletTransaction) {
        this.walletTransWallets.add(walletTransaction);
        walletTransaction.setWallet(this);
        return this;
    }

    public Wallet removeWalletTransWallet(WalletTransaction walletTransaction) {
        this.walletTransWallets.remove(walletTransaction);
        walletTransaction.setWallet(null);
        return this;
    }

    public void setWalletTransWallets(Set<WalletTransaction> walletTransactions) {
        this.walletTransWallets = walletTransactions;
    }

    public Set<ProductTypeWallet> getWalletProductTypes() {
        return walletProductTypes;
    }

    public Wallet walletProductTypes(Set<ProductTypeWallet> productTypeWallets) {
        this.walletProductTypes = productTypeWallets;
        return this;
    }

    public Wallet addWalletProductType(ProductTypeWallet productTypeWallet) {
        this.walletProductTypes.add(productTypeWallet);
        productTypeWallet.setWallet(this);
        return this;
    }

    public Wallet removeWalletProductType(ProductTypeWallet productTypeWallet) {
        this.walletProductTypes.remove(productTypeWallet);
        productTypeWallet.setWallet(null);
        return this;
    }

    public void setWalletProductTypes(Set<ProductTypeWallet> productTypeWallets) {
        this.walletProductTypes = productTypeWallets;
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
        Wallet wallet = (Wallet) o;
        if (wallet.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), wallet.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Wallet{" +
            "id=" + getId() +
            ", walletNumber='" + getWalletNumber() + "'" +
            ", walletIsActive='" + isWalletIsActive() + "'" +
            ", walletDesc='" + getWalletDesc() + "'" +
            ", walletUDF1='" + getWalletUDF1() + "'" +
            ", walletUDF2='" + getWalletUDF2() + "'" +
            ", walletUDF3='" + getWalletUDF3() + "'" +
            ", walletUDF4='" + getWalletUDF4() + "'" +
            ", walletUDF5='" + getWalletUDF5() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
