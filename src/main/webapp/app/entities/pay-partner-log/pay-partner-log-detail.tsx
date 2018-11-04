import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pay-partner-log.reducer';
import { IPayPartnerLog } from 'app/shared/model/pay-partner-log.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPayPartnerLogDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PayPartnerLogDetail extends React.Component<IPayPartnerLogDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { payPartnerLogEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="vpay03App.payPartnerLog.detail.title">PayPartnerLog</Translate> [<b>{payPartnerLogEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="payLogAmount">
                <Translate contentKey="vpay03App.payPartnerLog.payLogAmount">Pay Log Amount</Translate>
              </span>
            </dt>
            <dd>{payPartnerLogEntity.payLogAmount}</dd>
            <dt>
              <span id="payLogTransRef">
                <Translate contentKey="vpay03App.payPartnerLog.payLogTransRef">Pay Log Trans Ref</Translate>
              </span>
            </dt>
            <dd>{payPartnerLogEntity.payLogTransRef}</dd>
            <dt>
              <span id="payLogUsername">
                <Translate contentKey="vpay03App.payPartnerLog.payLogUsername">Pay Log Username</Translate>
              </span>
            </dt>
            <dd>{payPartnerLogEntity.payLogUsername}</dd>
            <dt>
              <span id="payLogProductTypeCode">
                <Translate contentKey="vpay03App.payPartnerLog.payLogProductTypeCode">Pay Log Product Type Code</Translate>
              </span>
            </dt>
            <dd>{payPartnerLogEntity.payLogProductTypeCode}</dd>
            <dt>
              <span id="payLogResuleCode">
                <Translate contentKey="vpay03App.payPartnerLog.payLogResuleCode">Pay Log Resule Code</Translate>
              </span>
            </dt>
            <dd>{payPartnerLogEntity.payLogResuleCode}</dd>
            <dt>
              <span id="payLogResuleDesc">
                <Translate contentKey="vpay03App.payPartnerLog.payLogResuleDesc">Pay Log Resule Desc</Translate>
              </span>
            </dt>
            <dd>{payPartnerLogEntity.payLogResuleDesc}</dd>
            <dt>
              <span id="payLogDate">
                <Translate contentKey="vpay03App.payPartnerLog.payLogDate">Pay Log Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={payPartnerLogEntity.payLogDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="payLogUDF1">
                <Translate contentKey="vpay03App.payPartnerLog.payLogUDF1">Pay Log UDF 1</Translate>
              </span>
            </dt>
            <dd>{payPartnerLogEntity.payLogUDF1}</dd>
            <dt>
              <span id="payLogUDF2">
                <Translate contentKey="vpay03App.payPartnerLog.payLogUDF2">Pay Log UDF 2</Translate>
              </span>
            </dt>
            <dd>{payPartnerLogEntity.payLogUDF2}</dd>
            <dt>
              <span id="payLogUDF3">
                <Translate contentKey="vpay03App.payPartnerLog.payLogUDF3">Pay Log UDF 3</Translate>
              </span>
            </dt>
            <dd>{payPartnerLogEntity.payLogUDF3}</dd>
            <dt>
              <span id="payLogUDF4">
                <Translate contentKey="vpay03App.payPartnerLog.payLogUDF4">Pay Log UDF 4</Translate>
              </span>
            </dt>
            <dd>{payPartnerLogEntity.payLogUDF4}</dd>
            <dt>
              <span id="payLogUDF5">
                <Translate contentKey="vpay03App.payPartnerLog.payLogUDF5">Pay Log UDF 5</Translate>
              </span>
            </dt>
            <dd>{payPartnerLogEntity.payLogUDF5}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="vpay03App.payPartnerLog.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{payPartnerLogEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="vpay03App.payPartnerLog.payPartner">Pay Partner</Translate>
            </dt>
            <dd>{payPartnerLogEntity.payPartner ? payPartnerLogEntity.payPartner.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/pay-partner-log" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/pay-partner-log/${payPartnerLogEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ payPartnerLog }: IRootState) => ({
  payPartnerLogEntity: payPartnerLog.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PayPartnerLogDetail);
