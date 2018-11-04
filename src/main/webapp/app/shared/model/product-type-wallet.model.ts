import { IProductType } from 'app/shared/model//product-type.model';
import { IWallet } from 'app/shared/model//wallet.model';

export interface IProductTypeWallet {
  id?: number;
  productTypeWalletUDF1?: string;
  productTypeWalletUDF2?: string;
  productTypeWalletUDF3?: string;
  isDeleted?: boolean;
  productType?: IProductType;
  wallet?: IWallet;
}

export const defaultValue: Readonly<IProductTypeWallet> = {
  isDeleted: false
};
