import { IProductType } from 'app/shared/model//product-type.model';
import { IPartner } from 'app/shared/model//partner.model';
import { IPayPartner } from 'app/shared/model//pay-partner.model';
import { IWalletTransaction } from 'app/shared/model//wallet-transaction.model';
import { IProductTypeWallet } from 'app/shared/model//product-type-wallet.model';

export interface IWallet {
  id?: number;
  walletNumber?: string;
  walletIsActive?: boolean;
  walletDesc?: string;
  walletUDF1?: string;
  walletUDF2?: string;
  walletUDF3?: string;
  walletUDF4?: string;
  walletUDF5?: string;
  isDeleted?: boolean;
  productType?: IProductType;
  partner?: IPartner;
  payPartner?: IPayPartner;
  walletTransWallets?: IWalletTransaction[];
  walletProductTypes?: IProductTypeWallet[];
}

export const defaultValue: Readonly<IWallet> = {
  walletIsActive: false,
  isDeleted: false
};
