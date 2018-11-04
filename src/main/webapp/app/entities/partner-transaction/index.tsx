import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PartnerTransaction from './partner-transaction';
import PartnerTransactionDetail from './partner-transaction-detail';
import PartnerTransactionUpdate from './partner-transaction-update';
import PartnerTransactionDeleteDialog from './partner-transaction-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PartnerTransactionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PartnerTransactionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PartnerTransactionDetail} />
      <ErrorBoundaryRoute path={match.url} component={PartnerTransaction} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PartnerTransactionDeleteDialog} />
  </>
);

export default Routes;
