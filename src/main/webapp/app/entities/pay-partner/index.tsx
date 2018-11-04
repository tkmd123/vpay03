import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PayPartner from './pay-partner';
import PayPartnerDetail from './pay-partner-detail';
import PayPartnerUpdate from './pay-partner-update';
import PayPartnerDeleteDialog from './pay-partner-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PayPartnerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PayPartnerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PayPartnerDetail} />
      <ErrorBoundaryRoute path={match.url} component={PayPartner} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PayPartnerDeleteDialog} />
  </>
);

export default Routes;
