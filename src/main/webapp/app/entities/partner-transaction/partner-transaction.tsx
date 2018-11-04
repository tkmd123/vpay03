import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import {
  Translate,
  ICrudGetAllAction,
  TextFormat,
  getSortState,
  IPaginationBaseState,
  getPaginationItemsNumber,
  JhiPagination
} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './partner-transaction.reducer';
import { IPartnerTransaction } from 'app/shared/model/partner-transaction.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IPartnerTransactionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IPartnerTransactionState = IPaginationBaseState;

export class PartnerTransaction extends React.Component<IPartnerTransactionProps, IPartnerTransactionState> {
  state: IPartnerTransactionState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.getEntities();
  }

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.sortEntities()
    );
  };

  sortEntities() {
    this.getEntities();
    this.props.history.push(`${this.props.location.pathname}?page=${this.state.activePage}&sort=${this.state.sort},${this.state.order}`);
  }

  handlePagination = activePage => this.setState({ activePage }, () => this.sortEntities());

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { partnerTransactionList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="partner-transaction-heading">
          <Translate contentKey="vpay03App.partnerTransaction.home.title">Partner Transactions</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="vpay03App.partnerTransaction.home.createLabel">Create new Partner Transaction</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('partnerTransAmount')}>
                  <Translate contentKey="vpay03App.partnerTransaction.partnerTransAmount">Partner Trans Amount</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('partnerTransDate')}>
                  <Translate contentKey="vpay03App.partnerTransaction.partnerTransDate">Partner Trans Date</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('partnerTransRef')}>
                  <Translate contentKey="vpay03App.partnerTransaction.partnerTransRef">Partner Trans Ref</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('partnerTransUsername')}>
                  <Translate contentKey="vpay03App.partnerTransaction.partnerTransUsername">Partner Trans Username</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('partnerTransStatus')}>
                  <Translate contentKey="vpay03App.partnerTransaction.partnerTransStatus">Partner Trans Status</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('partnerTransUDF1')}>
                  <Translate contentKey="vpay03App.partnerTransaction.partnerTransUDF1">Partner Trans UDF 1</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('partnerTransUDF2')}>
                  <Translate contentKey="vpay03App.partnerTransaction.partnerTransUDF2">Partner Trans UDF 2</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('partnerTransUDF3')}>
                  <Translate contentKey="vpay03App.partnerTransaction.partnerTransUDF3">Partner Trans UDF 3</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('partnerTransUDF4')}>
                  <Translate contentKey="vpay03App.partnerTransaction.partnerTransUDF4">Partner Trans UDF 4</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('partnerTransUDF5')}>
                  <Translate contentKey="vpay03App.partnerTransaction.partnerTransUDF5">Partner Trans UDF 5</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isDeleted')}>
                  <Translate contentKey="vpay03App.partnerTransaction.isDeleted">Is Deleted</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="vpay03App.partnerTransaction.productType">Product Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="vpay03App.partnerTransaction.partner">Partner</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="vpay03App.partnerTransaction.walletTransaction">Wallet Transaction</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {partnerTransactionList.map((partnerTransaction, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${partnerTransaction.id}`} color="link" size="sm">
                      {partnerTransaction.id}
                    </Button>
                  </td>
                  <td>{partnerTransaction.partnerTransAmount}</td>
                  <td>
                    <TextFormat type="date" value={partnerTransaction.partnerTransDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{partnerTransaction.partnerTransRef}</td>
                  <td>{partnerTransaction.partnerTransUsername}</td>
                  <td>{partnerTransaction.partnerTransStatus}</td>
                  <td>{partnerTransaction.partnerTransUDF1}</td>
                  <td>{partnerTransaction.partnerTransUDF2}</td>
                  <td>{partnerTransaction.partnerTransUDF3}</td>
                  <td>{partnerTransaction.partnerTransUDF4}</td>
                  <td>{partnerTransaction.partnerTransUDF5}</td>
                  <td>{partnerTransaction.isDeleted ? 'true' : 'false'}</td>
                  <td>
                    {partnerTransaction.productType ? (
                      <Link to={`product-type/${partnerTransaction.productType.id}`}>{partnerTransaction.productType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {partnerTransaction.partner ? (
                      <Link to={`partner/${partnerTransaction.partner.id}`}>{partnerTransaction.partner.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {partnerTransaction.walletTransaction ? (
                      <Link to={`wallet-transaction/${partnerTransaction.walletTransaction.id}`}>
                        {partnerTransaction.walletTransaction.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${partnerTransaction.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${partnerTransaction.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${partnerTransaction.id}/delete`} color="danger" size="sm">
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
        <Row className="justify-content-center">
          <JhiPagination
            items={getPaginationItemsNumber(totalItems, this.state.itemsPerPage)}
            activePage={this.state.activePage}
            onSelect={this.handlePagination}
            maxButtons={5}
          />
        </Row>
      </div>
    );
  }
}

const mapStateToProps = ({ partnerTransaction }: IRootState) => ({
  partnerTransactionList: partnerTransaction.entities,
  totalItems: partnerTransaction.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PartnerTransaction);
