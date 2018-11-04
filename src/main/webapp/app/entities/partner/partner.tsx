import React from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities, reset } from './partner.reducer';
import { IPartner } from 'app/shared/model/partner.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IPartnerProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IPartnerState = IPaginationBaseState;

export class Partner extends React.Component<IPartnerProps, IPartnerState> {
  state: IPartnerState = {
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
    const { partnerList, match } = this.props;
    return (
      <div>
        <h2 id="partner-heading">
          <Translate contentKey="vpay03App.partner.home.title">Partners</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="vpay03App.partner.home.createLabel">Create new Partner</Translate>
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
                  <th className="hand" onClick={this.sort('partnerCode')}>
                    <Translate contentKey="vpay03App.partner.partnerCode">Partner Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('partnerName')}>
                    <Translate contentKey="vpay03App.partner.partnerName">Partner Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('partnerDesc')}>
                    <Translate contentKey="vpay03App.partner.partnerDesc">Partner Desc</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('partnerOrder')}>
                    <Translate contentKey="vpay03App.partner.partnerOrder">Partner Order</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('partnerAPIUsername')}>
                    <Translate contentKey="vpay03App.partner.partnerAPIUsername">Partner API Username</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('partnerAPIPassword')}>
                    <Translate contentKey="vpay03App.partner.partnerAPIPassword">Partner API Password</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('partnerUrl')}>
                    <Translate contentKey="vpay03App.partner.partnerUrl">Partner Url</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('partnerStatus')}>
                    <Translate contentKey="vpay03App.partner.partnerStatus">Partner Status</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('partnerUDF1')}>
                    <Translate contentKey="vpay03App.partner.partnerUDF1">Partner UDF 1</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('partnerUDF2')}>
                    <Translate contentKey="vpay03App.partner.partnerUDF2">Partner UDF 2</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('partnerUDF3')}>
                    <Translate contentKey="vpay03App.partner.partnerUDF3">Partner UDF 3</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('isDeleted')}>
                    <Translate contentKey="vpay03App.partner.isDeleted">Is Deleted</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {partnerList.map((partner, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${partner.id}`} color="link" size="sm">
                        {partner.id}
                      </Button>
                    </td>
                    <td>{partner.partnerCode}</td>
                    <td>{partner.partnerName}</td>
                    <td>{partner.partnerDesc}</td>
                    <td>{partner.partnerOrder}</td>
                    <td>{partner.partnerAPIUsername}</td>
                    <td>{partner.partnerAPIPassword}</td>
                    <td>{partner.partnerUrl}</td>
                    <td>{partner.partnerStatus}</td>
                    <td>{partner.partnerUDF1}</td>
                    <td>{partner.partnerUDF2}</td>
                    <td>{partner.partnerUDF3}</td>
                    <td>{partner.isDeleted ? 'true' : 'false'}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${partner.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${partner.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${partner.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ partner }: IRootState) => ({
  partnerList: partner.entities,
  totalItems: partner.totalItems,
  links: partner.links,
  entity: partner.entity,
  updateSuccess: partner.updateSuccess
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
)(Partner);
