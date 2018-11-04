import React from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities, reset } from './wallet-rule.reducer';
import { IWalletRule } from 'app/shared/model/wallet-rule.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IWalletRuleProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IWalletRuleState = IPaginationBaseState;

export class WalletRule extends React.Component<IWalletRuleProps, IWalletRuleState> {
  state: IWalletRuleState = {
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
    const { walletRuleList, match } = this.props;
    return (
      <div>
        <h2 id="wallet-rule-heading">
          <Translate contentKey="vpay03App.walletRule.home.title">Wallet Rules</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="vpay03App.walletRule.home.createLabel">Create new Wallet Rule</Translate>
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
                  <th className="hand" onClick={this.sort('walletRuleCode')}>
                    <Translate contentKey="vpay03App.walletRule.walletRuleCode">Wallet Rule Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('walletRuleName')}>
                    <Translate contentKey="vpay03App.walletRule.walletRuleName">Wallet Rule Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('walletRuleDesc')}>
                    <Translate contentKey="vpay03App.walletRule.walletRuleDesc">Wallet Rule Desc</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('walletRuleFromDate')}>
                    <Translate contentKey="vpay03App.walletRule.walletRuleFromDate">Wallet Rule From Date</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('walletRuleToDate')}>
                    <Translate contentKey="vpay03App.walletRule.walletRuleToDate">Wallet Rule To Date</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('walletRuleUDF1')}>
                    <Translate contentKey="vpay03App.walletRule.walletRuleUDF1">Wallet Rule UDF 1</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('walletRuleUDF2')}>
                    <Translate contentKey="vpay03App.walletRule.walletRuleUDF2">Wallet Rule UDF 2</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('walletRuleUDF3')}>
                    <Translate contentKey="vpay03App.walletRule.walletRuleUDF3">Wallet Rule UDF 3</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('walletRuleUDF4')}>
                    <Translate contentKey="vpay03App.walletRule.walletRuleUDF4">Wallet Rule UDF 4</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('walletRuleUDF5')}>
                    <Translate contentKey="vpay03App.walletRule.walletRuleUDF5">Wallet Rule UDF 5</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('isDeleted')}>
                    <Translate contentKey="vpay03App.walletRule.isDeleted">Is Deleted</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="vpay03App.walletRule.productType">Product Type</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="vpay03App.walletRule.payPartner">Pay Partner</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {walletRuleList.map((walletRule, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${walletRule.id}`} color="link" size="sm">
                        {walletRule.id}
                      </Button>
                    </td>
                    <td>{walletRule.walletRuleCode}</td>
                    <td>{walletRule.walletRuleName}</td>
                    <td>{walletRule.walletRuleDesc}</td>
                    <td>
                      <TextFormat type="date" value={walletRule.walletRuleFromDate} format={APP_DATE_FORMAT} />
                    </td>
                    <td>
                      <TextFormat type="date" value={walletRule.walletRuleToDate} format={APP_DATE_FORMAT} />
                    </td>
                    <td>{walletRule.walletRuleUDF1}</td>
                    <td>{walletRule.walletRuleUDF2}</td>
                    <td>{walletRule.walletRuleUDF3}</td>
                    <td>{walletRule.walletRuleUDF4}</td>
                    <td>{walletRule.walletRuleUDF5}</td>
                    <td>{walletRule.isDeleted ? 'true' : 'false'}</td>
                    <td>
                      {walletRule.productType ? (
                        <Link to={`product-type/${walletRule.productType.id}`}>{walletRule.productType.id}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {walletRule.payPartner ? <Link to={`pay-partner/${walletRule.payPartner.id}`}>{walletRule.payPartner.id}</Link> : ''}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${walletRule.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${walletRule.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${walletRule.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ walletRule }: IRootState) => ({
  walletRuleList: walletRule.entities,
  totalItems: walletRule.totalItems,
  links: walletRule.links,
  entity: walletRule.entity,
  updateSuccess: walletRule.updateSuccess
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
)(WalletRule);
