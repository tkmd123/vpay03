import React from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities, reset } from './pay-partner.reducer';
import { IPayPartner } from 'app/shared/model/pay-partner.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IPayPartnerProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IPayPartnerState = IPaginationBaseState;

export class PayPartner extends React.Component<IPayPartnerProps, IPayPartnerState> {
  state: IPayPartnerState = {
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
    const { payPartnerList, match } = this.props;
    return (
      <div>
        <h2 id="pay-partner-heading">
          <Translate contentKey="vpay03App.payPartner.home.title">Pay Partners</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="vpay03App.payPartner.home.createLabel">Create new Pay Partner</Translate>
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
                  <th className="hand" onClick={this.sort('payPartnerCode')}>
                    <Translate contentKey="vpay03App.payPartner.payPartnerCode">Pay Partner Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('payPartnerName')}>
                    <Translate contentKey="vpay03App.payPartner.payPartnerName">Pay Partner Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('payPartnerDesc')}>
                    <Translate contentKey="vpay03App.payPartner.payPartnerDesc">Pay Partner Desc</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('payPartnerUsername')}>
                    <Translate contentKey="vpay03App.payPartner.payPartnerUsername">Pay Partner Username</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('payPartnerSecretKey')}>
                    <Translate contentKey="vpay03App.payPartner.payPartnerSecretKey">Pay Partner Secret Key</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('payPartnerStatus')}>
                    <Translate contentKey="vpay03App.payPartner.payPartnerStatus">Pay Partner Status</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('payPartnerUDF1')}>
                    <Translate contentKey="vpay03App.payPartner.payPartnerUDF1">Pay Partner UDF 1</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('payPartnerUDF2')}>
                    <Translate contentKey="vpay03App.payPartner.payPartnerUDF2">Pay Partner UDF 2</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('payPartnerUDF3')}>
                    <Translate contentKey="vpay03App.payPartner.payPartnerUDF3">Pay Partner UDF 3</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('isDeleted')}>
                    <Translate contentKey="vpay03App.payPartner.isDeleted">Is Deleted</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {payPartnerList.map((payPartner, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${payPartner.id}`} color="link" size="sm">
                        {payPartner.id}
                      </Button>
                    </td>
                    <td>{payPartner.payPartnerCode}</td>
                    <td>{payPartner.payPartnerName}</td>
                    <td>{payPartner.payPartnerDesc}</td>
                    <td>{payPartner.payPartnerUsername}</td>
                    <td>{payPartner.payPartnerSecretKey}</td>
                    <td>{payPartner.payPartnerStatus}</td>
                    <td>{payPartner.payPartnerUDF1}</td>
                    <td>{payPartner.payPartnerUDF2}</td>
                    <td>{payPartner.payPartnerUDF3}</td>
                    <td>{payPartner.isDeleted ? 'true' : 'false'}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${payPartner.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${payPartner.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${payPartner.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ payPartner }: IRootState) => ({
  payPartnerList: payPartner.entities,
  totalItems: payPartner.totalItems,
  links: payPartner.links,
  entity: payPartner.entity,
  updateSuccess: payPartner.updateSuccess
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
)(PayPartner);
