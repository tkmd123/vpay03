import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PayPartnerLog from './pay-partner-log';
import PayPartnerLogDetail from './pay-partner-log-detail';
import PayPartnerLogUpdate from './pay-partner-log-update';
import PayPartnerLogDeleteDialog from './pay-partner-log-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PayPartnerLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PayPartnerLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PayPartnerLogDetail} />
      <ErrorBoundaryRoute path={match.url} component={PayPartnerLog} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PayPartnerLogDeleteDialog} />
  </>
);

export default Routes;
