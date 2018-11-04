import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './wallet-rule-rate.reducer';
import { IWalletRuleRate } from 'app/shared/model/wallet-rule-rate.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IWalletRuleRateProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IWalletRuleRateState = IPaginationBaseState;

export class WalletRuleRate extends React.Component<IWalletRuleRateProps, IWalletRuleRateState> {
  state: IWalletRuleRateState = {
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
    const { walletRuleRateList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="wallet-rule-rate-heading">
          <Translate contentKey="vpay03App.walletRuleRate.home.title">Wallet Rule Rates</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="vpay03App.walletRuleRate.home.createLabel">Create new Wallet Rule Rate</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletRuleRateCode')}>
                  <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateCode">Wallet Rule Rate Code</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletRuleRateName')}>
                  <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateName">Wallet Rule Rate Name</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletRuleRateDesc')}>
                  <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateDesc">Wallet Rule Rate Desc</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletRuleRateFromValue')}>
                  <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateFromValue">Wallet Rule Rate From Value</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletRuleRateToValue')}>
                  <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateToValue">Wallet Rule Rate To Value</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletRuleRateDiscount')}>
                  <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateDiscount">Wallet Rule Rate Discount</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletRuleRateUDF1')}>
                  <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateUDF1">Wallet Rule Rate UDF 1</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletRuleRateUDF2')}>
                  <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateUDF2">Wallet Rule Rate UDF 2</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletRuleRateUDF3')}>
                  <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateUDF3">Wallet Rule Rate UDF 3</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletRuleRateUDF4')}>
                  <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateUDF4">Wallet Rule Rate UDF 4</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('walletRuleRateUDF5')}>
                  <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateUDF5">Wallet Rule Rate UDF 5</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isDeleted')}>
                  <Translate contentKey="vpay03App.walletRuleRate.isDeleted">Is Deleted</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="vpay03App.walletRuleRate.walletRule">Wallet Rule</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {walletRuleRateList.map((walletRuleRate, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${walletRuleRate.id}`} color="link" size="sm">
                      {walletRuleRate.id}
                    </Button>
                  </td>
                  <td>{walletRuleRate.walletRuleRateCode}</td>
                  <td>{walletRuleRate.walletRuleRateName}</td>
                  <td>{walletRuleRate.walletRuleRateDesc}</td>
                  <td>{walletRuleRate.walletRuleRateFromValue}</td>
                  <td>{walletRuleRate.walletRuleRateToValue}</td>
                  <td>{walletRuleRate.walletRuleRateDiscount}</td>
                  <td>{walletRuleRate.walletRuleRateUDF1}</td>
                  <td>{walletRuleRate.walletRuleRateUDF2}</td>
                  <td>{walletRuleRate.walletRuleRateUDF3}</td>
                  <td>{walletRuleRate.walletRuleRateUDF4}</td>
                  <td>{walletRuleRate.walletRuleRateUDF5}</td>
                  <td>{walletRuleRate.isDeleted ? 'true' : 'false'}</td>
                  <td>
                    {walletRuleRate.walletRule ? (
                      <Link to={`wallet-rule/${walletRuleRate.walletRule.id}`}>{walletRuleRate.walletRule.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${walletRuleRate.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${walletRuleRate.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${walletRuleRate.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ walletRuleRate }: IRootState) => ({
  walletRuleRateList: walletRuleRate.entities,
  totalItems: walletRuleRate.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(WalletRuleRate);
