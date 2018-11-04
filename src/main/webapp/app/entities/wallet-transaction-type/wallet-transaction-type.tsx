import React from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities, reset } from './wallet-transaction-type.reducer';
import { IWalletTransactionType } from 'app/shared/model/wallet-transaction-type.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IWalletTransactionTypeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IWalletTransactionTypeState = IPaginationBaseState;

export class WalletTransactionType extends React.Component<IWalletTransactionTypeProps, IWalletTransactionTypeState> {
  state: IWalletTransactionTypeState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.reset();
  }

  componentDidUpdate() {
    if (this.props.updateSuccess) {
      this.reset();
    }
  }

  reset = () => {
    this.props.reset();
    this.setState({ activePage: 1 }, () => {
      this.getEntities();
    });
  };

  handleLoadMore = () => {
    if (window.pageYOffset > 0) {
      this.setState({ activePage: this.state.activePage + 1 }, () => this.getEntities());
    }
  };

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => {
        this.reset();
      }
    );
  };

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { walletTransactionTypeList, match } = this.props;
    return (
      <div>
        <h2 id="wallet-transaction-type-heading">
          <Translate contentKey="vpay03App.walletTransactionType.home.title">Wallet Transaction Types</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="vpay03App.walletTransactionType.home.createLabel">Create new Wallet Transaction Type</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <InfiniteScroll
            pageStart={this.state.activePage}
            loadMore={this.handleLoadMore}
            hasMore={this.state.activePage - 1 < this.props.links.next}
            loader={<div className="loader">Loading ...</div>}
            threshold={0}
            initialLoad={false}
          >
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={this.sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('walletTransTypeCode')}>
                    <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeCode">Wallet Trans Type Code</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('walletTransTypeName')}>
                    <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeName">Wallet Trans Type Name</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('walletTransTypeDesc')}>
                    <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeDesc">Wallet Trans Type Desc</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('walletTransTypeFlag')}>
                    <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeFlag">Wallet Trans Type Flag</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('walletTransTypeUDF1')}>
                    <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeUDF1">Wallet Trans Type UDF 1</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('walletTransTypeUDF2')}>
                    <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeUDF2">Wallet Trans Type UDF 2</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('walletTransTypeUDF3')}>
                    <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeUDF3">Wallet Trans Type UDF 3</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('walletTransTypeUDF4')}>
                    <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeUDF4">Wallet Trans Type UDF 4</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('walletTransTypeUDF5')}>
                    <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeUDF5">Wallet Trans Type UDF 5</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('isDeleted')}>
                    <Translate contentKey="vpay03App.walletTransactionType.isDeleted">Is Deleted</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {walletTransactionTypeList.map((walletTransactionType, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${walletTransactionType.id}`} color="link" size="sm">
                        {walletTransactionType.id}
                      </Button>
                    </td>
                    <td>{walletTransactionType.walletTransTypeCode}</td>
                    <td>{walletTransactionType.walletTransTypeName}</td>
                    <td>{walletTransactionType.walletTransTypeDesc}</td>
                    <td>{walletTransactionType.walletTransTypeFlag}</td>
                    <td>{walletTransactionType.walletTransTypeUDF1}</td>
                    <td>{walletTransactionType.walletTransTypeUDF2}</td>
                    <td>{walletTransactionType.walletTransTypeUDF3}</td>
                    <td>{walletTransactionType.walletTransTypeUDF4}</td>
                    <td>{walletTransactionType.walletTransTypeUDF5}</td>
                    <td>{walletTransactionType.isDeleted ? 'true' : 'false'}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${walletTransactionType.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${walletTransactionType.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${walletTransactionType.id}/delete`} color="danger" size="sm">
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
          </InfiniteScroll>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ walletTransactionType }: IRootState) => ({
  walletTransactionTypeList: walletTransactionType.entities,
  totalItems: walletTransactionType.totalItems,
  links: walletTransactionType.links,
  entity: walletTransactionType.entity,
  updateSuccess: walletTransactionType.updateSuccess
});

const mapDispatchToProps = {
  getEntities,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(WalletTransactionType);
