import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PartnerLog from './partner-log';
import PartnerLogDetail from './partner-log-detail';
import PartnerLogUpdate from './partner-log-update';
import PartnerLogDeleteDialog from './partner-log-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PartnerLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PartnerLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PartnerLogDetail} />
      <ErrorBoundaryRoute path={match.url} component={PartnerLog} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PartnerLogDeleteDialog} />
  </>
);

export default Routes;
