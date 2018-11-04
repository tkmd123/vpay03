import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import productType, {
  ProductTypeState
} from 'app/entities/product-type/product-type.reducer';
// prettier-ignore
import product, {
  ProductState
} from 'app/entities/product/product.reducer';
// prettier-ignore
import productTypeWallet, {
  ProductTypeWalletState
} from 'app/entities/product-type-wallet/product-type-wallet.reducer';
// prettier-ignore
import partner, {
  PartnerState
} from 'app/entities/partner/partner.reducer';
// prettier-ignore
import payPartner, {
  PayPartnerState
} from 'app/entities/pay-partner/pay-partner.reducer';
// prettier-ignore
import walletTransactionType, {
  WalletTransactionTypeState
} from 'app/entities/wallet-transaction-type/wallet-transaction-type.reducer';
// prettier-ignore
import wallet, {
  WalletState
} from 'app/entities/wallet/wallet.reducer';
// prettier-ignore
import walletRule, {
  WalletRuleState
} from 'app/entities/wallet-rule/wallet-rule.reducer';
// prettier-ignore
import walletRuleRate, {
  WalletRuleRateState
} from 'app/entities/wallet-rule-rate/wallet-rule-rate.reducer';
// prettier-ignore
import walletTransaction, {
  WalletTransactionState
} from 'app/entities/wallet-transaction/wallet-transaction.reducer';
// prettier-ignore
import partnerTransaction, {
  PartnerTransactionState
} from 'app/entities/partner-transaction/partner-transaction.reducer';
// prettier-ignore
import payPartnerLog, {
  PayPartnerLogState
} from 'app/entities/pay-partner-log/pay-partner-log.reducer';
// prettier-ignore
import partnerLog, {
  PartnerLogState
} from 'app/entities/partner-log/partner-log.reducer';
// prettier-ignore
import status, {
  StatusState
} from 'app/entities/status/status.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly productType: ProductTypeState;
  readonly product: ProductState;
  readonly productTypeWallet: ProductTypeWalletState;
  readonly partner: PartnerState;
  readonly payPartner: PayPartnerState;
  readonly walletTransactionType: WalletTransactionTypeState;
  readonly wallet: WalletState;
  readonly walletRule: WalletRuleState;
  readonly walletRuleRate: WalletRuleRateState;
  readonly walletTransaction: WalletTransactionState;
  readonly partnerTransaction: PartnerTransactionState;
  readonly payPartnerLog: PayPartnerLogState;
  readonly partnerLog: PartnerLogState;
  readonly status: StatusState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  productType,
  product,
  productTypeWallet,
  partner,
  payPartner,
  walletTransactionType,
  wallet,
  walletRule,
  walletRuleRate,
  walletTransaction,
  partnerTransaction,
  payPartnerLog,
  partnerLog,
  status,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
