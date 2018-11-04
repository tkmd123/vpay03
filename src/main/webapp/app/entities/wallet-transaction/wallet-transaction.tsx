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
import { getEntities } from './wallet-transaction.reducer';
import { IWalletTransaction } from 'app/shared/model/wallet-transaction.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IWalletTransactionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IWalletTransactionState = IPaginationBaseState;

export class WalletTransaction extends React.Component<IWalletTransactionProps, IWalletTransactionState> {
  state: IWalletTransactionState = {
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
    const { walletTransactionList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="wallet-transaction-heading">
          <Translate contentKey="vpay03App.walletTransaction.home.title">Wallet Transactions</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="vpay03App.walletTransaction.home.createLabel">Create new Wallet Transaction</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletTransAmount')}>
                  <Translate contentKey="vpay03App.walletTransaction.walletTransAmount">Wallet Trans Amount</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletTransDate')}>
                  <Translate contentKey="vpay03App.walletTransaction.walletTransDate">Wallet Trans Date</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletTransRef')}>
                  <Translate contentKey="vpay03App.walletTransaction.walletTransRef">Wallet Trans Ref</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletTransUsername')}>
                  <Translate contentKey="vpay03App.walletTransaction.walletTransUsername">Wallet Trans Username</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletTransStatus')}>
                  <Translate contentKey="vpay03App.walletTransaction.walletTransStatus">Wallet Trans Status</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletTransUDF1')}>
                  <Translate contentKey="vpay03App.walletTransaction.walletTransUDF1">Wallet Trans UDF 1</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletTransUDF2')}>
                  <Translate contentKey="vpay03App.walletTransaction.walletTransUDF2">Wallet Trans UDF 2</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletTransUDF3')}>
                  <Translate contentKey="vpay03App.walletTransaction.walletTransUDF3">Wallet Trans UDF 3</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletTransUDF4')}>
                  <Translate contentKey="vpay03App.walletTransaction.walletTransUDF4">Wallet Trans UDF 4</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletTransUDF5')}>
                  <Translate contentKey="vpay03App.walletTransaction.walletTransUDF5">Wallet Trans UDF 5</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isDeleted')}>
                  <Translate contentKey="vpay03App.walletTransaction.isDeleted">Is Deleted</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="vpay03App.walletTransaction.walletTransactionType">Wallet Transaction Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="vpay03App.walletTransaction.wallet">Wallet</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {walletTransactionList.map((walletTransaction, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${walletTransaction.id}`} color="link" size="sm">
                      {walletTransaction.id}
                    </Button>
                  </td>
                  <td>{walletTransaction.walletTransAmount}</td>
                  <td>
                    <TextFormat type="date" value={walletTransaction.walletTransDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{walletTransaction.walletTransRef}</td>
                  <td>{walletTransaction.walletTransUsername}</td>
                  <td>{walletTransaction.walletTransStatus}</td>
                  <td>{walletTransaction.walletTransUDF1}</td>
                  <td>{walletTransaction.walletTransUDF2}</td>
                  <td>{walletTransaction.walletTransUDF3}</td>
                  <td>{walletTransaction.walletTransUDF4}</td>
                  <td>{walletTransaction.walletTransUDF5}</td>
                  <td>{walletTransaction.isDeleted ? 'true' : 'false'}</td>
                  <td>
                    {walletTransaction.walletTransactionType ? (
                      <Link to={`wallet-transaction-type/${walletTransaction.walletTransactionType.id}`}>
                        {walletTransaction.walletTransactionType.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {walletTransaction.wallet ? (
                      <Link to={`wallet/${walletTransaction.wallet.id}`}>{walletTransaction.wallet.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${walletTransaction.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${walletTransaction.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${walletTransaction.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ walletTransaction }: IRootState) => ({
  walletTransactionList: walletTransaction.entities,
  totalItems: walletTransaction.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(WalletTransaction);
