import { IWalletTransaction } from 'app/shared/model//wallet-transaction.model';

export interface IWalletTransactionType {
  id?: number;
  walletTransTypeCode?: string;
  walletTransTypeName?: string;
  walletTransTypeDesc?: string;
  walletTransTypeFlag?: number;
  walletTransTypeUDF1?: string;
  walletTransTypeUDF2?: string;
  walletTransTypeUDF3?: string;
  walletTransTypeUDF4?: string;
  walletTransTypeUDF5?: string;
  isDeleted?: boolean;
  walletTransWalletTransTypes?: IWalletTransaction[];
}

export const defaultValue: Readonly<IWalletTransactionType> = {
  isDeleted: false
};
