import { IProduct } from 'app/shared/model//product.model';
import { IWallet } from 'app/shared/model//wallet.model';
import { IWalletRule } from 'app/shared/model//wallet-rule.model';
import { IPartnerTransaction } from 'app/shared/model//partner-transaction.model';
import { IProductTypeWallet } from 'app/shared/model//product-type-wallet.model';

export interface IProductType {
  id?: number;
  productTypeCode?: string;
  productTypeName?: string;
  productTypeDesc?: string;
  productTypeCategory?: string;
  productUDF1?: string;
  productUDF2?: string;
  productUDF3?: string;
  isDeleted?: boolean;
  productProductTypes?: IProduct[];
  walletProductTypes?: IWallet[];
  walletRuleProductTypes?: IWalletRule[];
  partnerTransProductTypes?: IPartnerTransaction[];
  productTypeWallets?: IProductTypeWallet[];
}

export const defaultValue: Readonly<IProductType> = {
  isDeleted: false
};
