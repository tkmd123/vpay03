import { IWallet } from 'app/shared/model//wallet.model';
import { IPartnerLog } from 'app/shared/model//partner-log.model';
import { IPartnerTransaction } from 'app/shared/model//partner-transaction.model';

export interface IPartner {
  id?: number;
  partnerCode?: string;
  partnerName?: string;
  partnerDesc?: string;
  partnerOrder?: number;
  partnerAPIUsername?: string;
  partnerAPIPassword?: string;
  partnerUrl?: string;
  partnerStatus?: string;
  partnerUDF1?: string;
  partnerUDF2?: string;
  partnerUDF3?: string;
  isDeleted?: boolean;
  walletPartners?: IWallet[];
  partnerLogPartners?: IPartnerLog[];
  partnerTransPartners?: IPartnerTransaction[];
}

export const defaultValue: Readonly<IPartner> = {
  isDeleted: false
};
