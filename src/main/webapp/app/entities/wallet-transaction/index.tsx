import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import WalletTransaction from './wallet-transaction';
import WalletTransactionDetail from './wallet-transaction-detail';
import WalletTransactionUpdate from './wallet-transaction-update';
import WalletTransactionDeleteDialog from './wallet-transaction-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={WalletTransactionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={WalletTransactionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={WalletTransactionDetail} />
      <ErrorBoundaryRoute path={match.url} component={WalletTransaction} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={WalletTransactionDeleteDialog} />
  </>
);

export default Routes;
