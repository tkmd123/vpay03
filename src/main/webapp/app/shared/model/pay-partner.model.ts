import { IWallet } from 'app/shared/model//wallet.model';
import { IWalletRule } from 'app/shared/model//wallet-rule.model';
import { IPayPartnerLog } from 'app/shared/model//pay-partner-log.model';

export interface IPayPartner {
  id?: number;
  payPartnerCode?: string;
  payPartnerName?: string;
  payPartnerDesc?: string;
  payPartnerUsername?: string;
  payPartnerSecretKey?: string;
  payPartnerStatus?: string;
  payPartnerUDF1?: string;
  payPartnerUDF2?: string;
  payPartnerUDF3?: string;
  isDeleted?: boolean;
  walletPayPartners?: IWallet[];
  walletRulePayPartners?: IWalletRule[];
  partnerLogPayPartners?: IPayPartnerLog[];
}

export const defaultValue: Readonly<IPayPartner> = {
  isDeleted: false
};
