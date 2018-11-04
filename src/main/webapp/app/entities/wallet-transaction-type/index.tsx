import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import WalletTransactionType from './wallet-transaction-type';
import WalletTransactionTypeDetail from './wallet-transaction-type-detail';
import WalletTransactionTypeUpdate from './wallet-transaction-type-update';
import WalletTransactionTypeDeleteDialog from './wallet-transaction-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={WalletTransactionTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={WalletTransactionTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={WalletTransactionTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={WalletTransactionType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={WalletTransactionTypeDeleteDialog} />
  </>
);

export default Routes;
