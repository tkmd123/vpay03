import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ProductType from './product-type';
import Product from './product';
import ProductTypeWallet from './product-type-wallet';
import Partner from './partner';
import PayPartner from './pay-partner';
import WalletTransactionType from './wallet-transaction-type';
import Wallet from './wallet';
import WalletRule from './wallet-rule';
import WalletRuleRate from './wallet-rule-rate';
import WalletTransaction from './wallet-transaction';
import PartnerTransaction from './partner-transaction';
import PayPartnerLog from './pay-partner-log';
import PartnerLog from './partner-log';
import Status from './status';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/product-type`} component={ProductType} />
      <ErrorBoundaryRoute path={`${match.url}/product`} component={Product} />
      <ErrorBoundaryRoute path={`${match.url}/product-type-wallet`} component={ProductTypeWallet} />
      <ErrorBoundaryRoute path={`${match.url}/partner`} component={Partner} />
      <ErrorBoundaryRoute path={`${match.url}/pay-partner`} component={PayPartner} />
      <ErrorBoundaryRoute path={`${match.url}/wallet-transaction-type`} component={WalletTransactionType} />
      <ErrorBoundaryRoute path={`${match.url}/wallet`} component={Wallet} />
      <ErrorBoundaryRoute path={`${match.url}/wallet-rule`} component={WalletRule} />
      <ErrorBoundaryRoute path={`${match.url}/wallet-rule-rate`} component={WalletRuleRate} />
      <ErrorBoundaryRoute path={`${match.url}/wallet-transaction`} component={WalletTransaction} />
      <ErrorBoundaryRoute path={`${match.url}/partner-transaction`} component={PartnerTransaction} />
      <ErrorBoundaryRoute path={`${match.url}/pay-partner-log`} component={PayPartnerLog} />
      <ErrorBoundaryRoute path={`${match.url}/partner-log`} component={PartnerLog} />
      <ErrorBoundaryRoute path={`${match.url}/status`} component={Status} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
