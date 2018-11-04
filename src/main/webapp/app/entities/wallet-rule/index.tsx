import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import WalletRule from './wallet-rule';
import WalletRuleDetail from './wallet-rule-detail';
import WalletRuleUpdate from './wallet-rule-update';
import WalletRuleDeleteDialog from './wallet-rule-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={WalletRuleUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={WalletRuleUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={WalletRuleDetail} />
      <ErrorBoundaryRoute path={match.url} component={WalletRule} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={WalletRuleDeleteDialog} />
  </>
);

export default Routes;
