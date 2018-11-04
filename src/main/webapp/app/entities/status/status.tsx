import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './status.reducer';
import { IStatus } from 'app/shared/model/status.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStatusProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Status extends React.Component<IStatusProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { statusList, match } = this.props;
    return (
      <div>
        <h2 id="status-heading">
          <Translate contentKey="vpay03App.status.home.title">Statuses</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="vpay03App.status.home.createLabel">Create new Status</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="vpay03App.status.statusCode">Status Code</Translate>
                </th>
                <th>
                  <Translate contentKey="vpay03App.status.statusValue">Status Value</Translate>
                </th>
                <th>
                  <Translate contentKey="vpay03App.status.statusName">Status Name</Translate>
                </th>
                <th>
                  <Translate contentKey="vpay03App.status.statusDesc">Status Desc</Translate>
                </th>
                <th>
                  <Translate contentKey="vpay03App.status.statusUDF1">Status UDF 1</Translate>
                </th>
                <th>
                  <Translate contentKey="vpay03App.status.statusUDF2">Status UDF 2</Translate>
                </th>
                <th>
                  <Translate contentKey="vpay03App.status.statusUDF3">Status UDF 3</Translate>
                </th>
                <th>
                  <Translate contentKey="vpay03App.status.isDeleted">Is Deleted</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {statusList.map((status, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${status.id}`} color="link" size="sm">
                      {status.id}
                    </Button>
                  </td>
                  <td>{status.statusCode}</td>
                  <td>{status.statusValue}</td>
                  <td>{status.statusName}</td>
                  <td>{status.statusDesc}</td>
                  <td>{status.statusUDF1}</td>
                  <td>{status.statusUDF2}</td>
                  <td>{status.statusUDF3}</td>
                  <td>{status.isDeleted ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${status.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${status.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${status.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ status }: IRootState) => ({
  statusList: status.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Status);
