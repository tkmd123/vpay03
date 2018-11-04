import { IWalletRule } from 'app/shared/model//wallet-rule.model';

export interface IWalletRuleRate {
  id?: number;
  walletRuleRateCode?: string;
  walletRuleRateName?: string;
  walletRuleRateDesc?: string;
  walletRuleRateFromValue?: number;
  walletRuleRateToValue?: number;
  walletRuleRateDiscount?: number;
  walletRuleRateUDF1?: string;
  walletRuleRateUDF2?: string;
  walletRuleRateUDF3?: string;
  walletRuleRateUDF4?: string;
  walletRuleRateUDF5?: string;
  isDeleted?: boolean;
  walletRule?: IWalletRule;
}

export const defaultValue: Readonly<IWalletRuleRate> = {
  isDeleted: false
};
