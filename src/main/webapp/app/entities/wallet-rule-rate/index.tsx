import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import WalletRuleRate from './wallet-rule-rate';
import WalletRuleRateDetail from './wallet-rule-rate-detail';
import WalletRuleRateUpdate from './wallet-rule-rate-update';
import WalletRuleRateDeleteDialog from './wallet-rule-rate-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={WalletRuleRateUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={WalletRuleRateUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={WalletRuleRateDetail} />
      <ErrorBoundaryRoute path={match.url} component={WalletRuleRate} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={WalletRuleRateDeleteDialog} />
  </>
);

export default Routes;
