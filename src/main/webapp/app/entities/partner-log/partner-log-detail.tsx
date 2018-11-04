import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './partner-log.reducer';
import { IPartnerLog } from 'app/shared/model/partner-log.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPartnerLogDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PartnerLogDetail extends React.Component<IPartnerLogDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { partnerLogEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="vpay03App.partnerLog.detail.title">PartnerLog</Translate> [<b>{partnerLogEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="partnerLogAmount">
                <Translate contentKey="vpay03App.partnerLog.partnerLogAmount">Partner Log Amount</Translate>
              </span>
            </dt>
            <dd>{partnerLogEntity.partnerLogAmount}</dd>
            <dt>
              <span id="partnerLogTransRef">
                <Translate contentKey="vpay03App.partnerLog.partnerLogTransRef">Partner Log Trans Ref</Translate>
              </span>
            </dt>
            <dd>{partnerLogEntity.partnerLogTransRef}</dd>
            <dt>
              <span id="partnerLogUsername">
                <Translate contentKey="vpay03App.partnerLog.partnerLogUsername">Partner Log Username</Translate>
              </span>
            </dt>
            <dd>{partnerLogEntity.partnerLogUsername}</dd>
            <dt>
              <span id="partnerLogProductTypeCode">
                <Translate contentKey="vpay03App.partnerLog.partnerLogProductTypeCode">Partner Log Product Type Code</Translate>
              </span>
            </dt>
            <dd>{partnerLogEntity.partnerLogProductTypeCode}</dd>
            <dt>
              <span id="partnerLogResultCode">
                <Translate contentKey="vpay03App.partnerLog.partnerLogResultCode">Partner Log Result Code</Translate>
              </span>
            </dt>
            <dd>{partnerLogEntity.partnerLogResultCode}</dd>
            <dt>
              <span id="partnerLogResultDesc">
                <Translate contentKey="vpay03App.partnerLog.partnerLogResultDesc">Partner Log Result Desc</Translate>
              </span>
            </dt>
            <dd>{partnerLogEntity.partnerLogResultDesc}</dd>
            <dt>
              <span id="partnerLogDate">
                <Translate contentKey="vpay03App.partnerLog.partnerLogDate">Partner Log Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={partnerLogEntity.partnerLogDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="partnerLogUDF1">
                <Translate contentKey="vpay03App.partnerLog.partnerLogUDF1">Partner Log UDF 1</Translate>
              </span>
            </dt>
            <dd>{partnerLogEntity.partnerLogUDF1}</dd>
            <dt>
              <span id="partnerLogUDF2">
                <Translate contentKey="vpay03App.partnerLog.partnerLogUDF2">Partner Log UDF 2</Translate>
              </span>
            </dt>
            <dd>{partnerLogEntity.partnerLogUDF2}</dd>
            <dt>
              <span id="partnerLogUDF3">
                <Translate contentKey="vpay03App.partnerLog.partnerLogUDF3">Partner Log UDF 3</Translate>
              </span>
            </dt>
            <dd>{partnerLogEntity.partnerLogUDF3}</dd>
            <dt>
              <span id="partnerLogUDF4">
                <Translate contentKey="vpay03App.partnerLog.partnerLogUDF4">Partner Log UDF 4</Translate>
              </span>
            </dt>
            <dd>{partnerLogEntity.partnerLogUDF4}</dd>
            <dt>
              <span id="partnerLogUDF5">
                <Translate contentKey="vpay03App.partnerLog.partnerLogUDF5">Partner Log UDF 5</Translate>
              </span>
            </dt>
            <dd>{partnerLogEntity.partnerLogUDF5}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="vpay03App.partnerLog.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{partnerLogEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="vpay03App.partnerLog.partner">Partner</Translate>
            </dt>
            <dd>{partnerLogEntity.partner ? partnerLogEntity.partner.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/partner-log" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/partner-log/${partnerLogEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ partnerLog }: IRootState) => ({
  partnerLogEntity: partnerLog.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PartnerLogDetail);
