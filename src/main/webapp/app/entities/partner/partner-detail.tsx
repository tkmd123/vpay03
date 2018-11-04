import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './partner.reducer';
import { IPartner } from 'app/shared/model/partner.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPartnerDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PartnerDetail extends React.Component<IPartnerDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { partnerEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="vpay03App.partner.detail.title">Partner</Translate> [<b>{partnerEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="partnerCode">
                <Translate contentKey="vpay03App.partner.partnerCode">Partner Code</Translate>
              </span>
            </dt>
            <dd>{partnerEntity.partnerCode}</dd>
            <dt>
              <span id="partnerName">
                <Translate contentKey="vpay03App.partner.partnerName">Partner Name</Translate>
              </span>
            </dt>
            <dd>{partnerEntity.partnerName}</dd>
            <dt>
              <span id="partnerDesc">
                <Translate contentKey="vpay03App.partner.partnerDesc">Partner Desc</Translate>
              </span>
            </dt>
            <dd>{partnerEntity.partnerDesc}</dd>
            <dt>
              <span id="partnerOrder">
                <Translate contentKey="vpay03App.partner.partnerOrder">Partner Order</Translate>
              </span>
            </dt>
            <dd>{partnerEntity.partnerOrder}</dd>
            <dt>
              <span id="partnerAPIUsername">
                <Translate contentKey="vpay03App.partner.partnerAPIUsername">Partner API Username</Translate>
              </span>
            </dt>
            <dd>{partnerEntity.partnerAPIUsername}</dd>
            <dt>
              <span id="partnerAPIPassword">
                <Translate contentKey="vpay03App.partner.partnerAPIPassword">Partner API Password</Translate>
              </span>
            </dt>
            <dd>{partnerEntity.partnerAPIPassword}</dd>
            <dt>
              <span id="partnerUrl">
                <Translate contentKey="vpay03App.partner.partnerUrl">Partner Url</Translate>
              </span>
            </dt>
            <dd>{partnerEntity.partnerUrl}</dd>
            <dt>
              <span id="partnerStatus">
                <Translate contentKey="vpay03App.partner.partnerStatus">Partner Status</Translate>
              </span>
            </dt>
            <dd>{partnerEntity.partnerStatus}</dd>
            <dt>
              <span id="partnerUDF1">
                <Translate contentKey="vpay03App.partner.partnerUDF1">Partner UDF 1</Translate>
              </span>
            </dt>
            <dd>{partnerEntity.partnerUDF1}</dd>
            <dt>
              <span id="partnerUDF2">
                <Translate contentKey="vpay03App.partner.partnerUDF2">Partner UDF 2</Translate>
              </span>
            </dt>
            <dd>{partnerEntity.partnerUDF2}</dd>
            <dt>
              <span id="partnerUDF3">
                <Translate contentKey="vpay03App.partner.partnerUDF3">Partner UDF 3</Translate>
              </span>
            </dt>
            <dd>{partnerEntity.partnerUDF3}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="vpay03App.partner.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{partnerEntity.isDeleted ? 'true' : 'false'}</dd>
          </dl>
          <Button tag={Link} to="/entity/partner" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/partner/${partnerEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ partner }: IRootState) => ({
  partnerEntity: partner.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PartnerDetail);
