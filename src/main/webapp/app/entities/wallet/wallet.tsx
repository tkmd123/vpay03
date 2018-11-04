import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './wallet.reducer';
import { IWallet } from 'app/shared/model/wallet.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IWalletProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IWalletState = IPaginationBaseState;

export class Wallet extends React.Component<IWalletProps, IWalletState> {
  state: IWalletState = {
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
    const { walletList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="wallet-heading">
          <Translate contentKey="vpay03App.wallet.home.title">Wallets</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="vpay03App.wallet.home.createLabel">Create new Wallet</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletNumber')}>
                  <Translate contentKey="vpay03App.wallet.walletNumber">Wallet Number</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletIsActive')}>
                  <Translate contentKey="vpay03App.wallet.walletIsActive">Wallet Is Active</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletDesc')}>
                  <Translate contentKey="vpay03App.wallet.walletDesc">Wallet Desc</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletUDF1')}>
                  <Translate contentKey="vpay03App.wallet.walletUDF1">Wallet UDF 1</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletUDF2')}>
                  <Translate contentKey="vpay03App.wallet.walletUDF2">Wallet UDF 2</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletUDF3')}>
                  <Translate contentKey="vpay03App.wallet.walletUDF3">Wallet UDF 3</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletUDF4')}>
                  <Translate contentKey="vpay03App.wallet.walletUDF4">Wallet UDF 4</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletUDF5')}>
                  <Translate contentKey="vpay03App.wallet.walletUDF5">Wallet UDF 5</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isDeleted')}>
                  <Translate contentKey="vpay03App.wallet.isDeleted">Is Deleted</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="vpay03App.wallet.productType">Product Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="vpay03App.wallet.partner">Partner</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="vpay03App.wallet.payPartner">Pay Partner</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {walletList.map((wallet, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${wallet.id}`} color="link" size="sm">
                      {wallet.id}
                    </Button>
                  </td>
                  <td>{wallet.walletNumber}</td>
                  <td>{wallet.walletIsActive ? 'true' : 'false'}</td>
                  <td>{wallet.walletDesc}</td>
                  <td>{wallet.walletUDF1}</td>
                  <td>{wallet.walletUDF2}</td>
                  <td>{wallet.walletUDF3}</td>
                  <td>{wallet.walletUDF4}</td>
                  <td>{wallet.walletUDF5}</td>
                  <td>{wallet.isDeleted ? 'true' : 'false'}</td>
                  <td>{wallet.productType ? <Link to={`product-type/${wallet.productType.id}`}>{wallet.productType.id}</Link> : ''}</td>
                  <td>{wallet.partner ? <Link to={`partner/${wallet.partner.id}`}>{wallet.partner.id}</Link> : ''}</td>
                  <td>{wallet.payPartner ? <Link to={`pay-partner/${wallet.payPartner.id}`}>{wallet.payPartner.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${wallet.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${wallet.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${wallet.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ wallet }: IRootState) => ({
  walletList: wallet.entities,
  totalItems: wallet.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Wallet);
