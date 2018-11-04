import { Moment } from 'moment';
import { IWalletTransactionType } from 'app/shared/model//wallet-transaction-type.model';
import { IWallet } from 'app/shared/model//wallet.model';
import { IPartnerTransaction } from 'app/shared/model//partner-transaction.model';

export interface IWalletTransaction {
  id?: number;
  walletTransAmount?: number;
  walletTransDate?: Moment;
  walletTransRef?: string;
  walletTransUsername?: string;
  walletTransStatus?: string;
  walletTransUDF1?: string;
  walletTransUDF2?: string;
  walletTransUDF3?: string;
  walletTransUDF4?: string;
  walletTransUDF5?: string;
  isDeleted?: boolean;
  walletTransactionType?: IWalletTransactionType;
  wallet?: IWallet;
  partnerTransWalletTransactions?: IPartnerTransaction[];
}

export const defaultValue: Readonly<IWalletTransaction> = {
  isDeleted: false
};
