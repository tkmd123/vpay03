import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ProductTypeWallet from './product-type-wallet';
import ProductTypeWalletDetail from './product-type-wallet-detail';
import ProductTypeWalletUpdate from './product-type-wallet-update';
import ProductTypeWalletDeleteDialog from './product-type-wallet-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ProductTypeWalletUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ProductTypeWalletUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ProductTypeWalletDetail} />
      <ErrorBoundaryRoute path={match.url} component={ProductTypeWallet} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ProductTypeWalletDeleteDialog} />
  </>
);

export default Routes;
