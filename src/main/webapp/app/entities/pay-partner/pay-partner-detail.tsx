import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pay-partner.reducer';
import { IPayPartner } from 'app/shared/model/pay-partner.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPayPartnerDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PayPartnerDetail extends React.Component<IPayPartnerDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { payPartnerEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="vpay03App.payPartner.detail.title">PayPartner</Translate> [<b>{payPartnerEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="payPartnerCode">
                <Translate contentKey="vpay03App.payPartner.payPartnerCode">Pay Partner Code</Translate>
              </span>
            </dt>
            <dd>{payPartnerEntity.payPartnerCode}</dd>
            <dt>
              <span id="payPartnerName">
                <Translate contentKey="vpay03App.payPartner.payPartnerName">Pay Partner Name</Translate>
              </span>
            </dt>
            <dd>{payPartnerEntity.payPartnerName}</dd>
            <dt>
              <span id="payPartnerDesc">
                <Translate contentKey="vpay03App.payPartner.payPartnerDesc">Pay Partner Desc</Translate>
              </span>
            </dt>
            <dd>{payPartnerEntity.payPartnerDesc}</dd>
            <dt>
              <span id="payPartnerUsername">
                <Translate contentKey="vpay03App.payPartner.payPartnerUsername">Pay Partner Username</Translate>
              </span>
            </dt>
            <dd>{payPartnerEntity.payPartnerUsername}</dd>
            <dt>
              <span id="payPartnerSecretKey">
                <Translate contentKey="vpay03App.payPartner.payPartnerSecretKey">Pay Partner Secret Key</Translate>
              </span>
            </dt>
            <dd>{payPartnerEntity.payPartnerSecretKey}</dd>
            <dt>
              <span id="payPartnerStatus">
                <Translate contentKey="vpay03App.payPartner.payPartnerStatus">Pay Partner Status</Translate>
              </span>
            </dt>
            <dd>{payPartnerEntity.payPartnerStatus}</dd>
            <dt>
              <span id="payPartnerUDF1">
                <Translate contentKey="vpay03App.payPartner.payPartnerUDF1">Pay Partner UDF 1</Translate>
              </span>
            </dt>
            <dd>{payPartnerEntity.payPartnerUDF1}</dd>
            <dt>
              <span id="payPartnerUDF2">
                <Translate contentKey="vpay03App.payPartner.payPartnerUDF2">Pay Partner UDF 2</Translate>
              </span>
            </dt>
            <dd>{payPartnerEntity.payPartnerUDF2}</dd>
            <dt>
              <span id="payPartnerUDF3">
                <Translate contentKey="vpay03App.payPartner.payPartnerUDF3">Pay Partner UDF 3</Translate>
              </span>
            </dt>
            <dd>{payPartnerEntity.payPartnerUDF3}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="vpay03App.payPartner.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{payPartnerEntity.isDeleted ? 'true' : 'false'}</dd>
          </dl>
          <Button tag={Link} to="/entity/pay-partner" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/pay-partner/${payPartnerEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ payPartner }: IRootState) => ({
  payPartnerEntity: payPartner.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PayPartnerDetail);
