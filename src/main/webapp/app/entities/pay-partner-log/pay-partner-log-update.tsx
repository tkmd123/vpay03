import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPayPartner } from 'app/shared/model/pay-partner.model';
import { getEntities as getPayPartners } from 'app/entities/pay-partner/pay-partner.reducer';
import { getEntity, updateEntity, createEntity, reset } from './pay-partner-log.reducer';
import { IPayPartnerLog } from 'app/shared/model/pay-partner-log.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPayPartnerLogUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPayPartnerLogUpdateState {
  isNew: boolean;
  payPartnerId: string;
}

export class PayPartnerLogUpdate extends React.Component<IPayPartnerLogUpdateProps, IPayPartnerLogUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      payPartnerId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getPayPartners();
  }

  saveEntity = (event, errors, values) => {
    values.payLogDate = new Date(values.payLogDate);

    if (errors.length === 0) {
      const { payPartnerLogEntity } = this.props;
      const entity = {
        ...payPartnerLogEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/pay-partner-log');
  };

  render() {
    const { payPartnerLogEntity, payPartners, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="vpay03App.payPartnerLog.home.createOrEditLabel">
              <Translate contentKey="vpay03App.payPartnerLog.home.createOrEditLabel">Create or edit a PayPartnerLog</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : payPartnerLogEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="pay-partner-log-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="payLogAmountLabel" for="payLogAmount">
                    <Translate contentKey="vpay03App.payPartnerLog.payLogAmount">Pay Log Amount</Translate>
                  </Label>
                  <AvField id="pay-partner-log-payLogAmount" type="text" name="payLogAmount" />
                </AvGroup>
                <AvGroup>
                  <Label id="payLogTransRefLabel" for="payLogTransRef">
                    <Translate contentKey="vpay03App.payPartnerLog.payLogTransRef">Pay Log Trans Ref</Translate>
                  </Label>
                  <AvField id="pay-partner-log-payLogTransRef" type="text" name="payLogTransRef" />
                </AvGroup>
                <AvGroup>
                  <Label id="payLogUsernameLabel" for="payLogUsername">
                    <Translate contentKey="vpay03App.payPartnerLog.payLogUsername">Pay Log Username</Translate>
                  </Label>
                  <AvField id="pay-partner-log-payLogUsername" type="text" name="payLogUsername" />
                </AvGroup>
                <AvGroup>
                  <Label id="payLogProductTypeCodeLabel" for="payLogProductTypeCode">
                    <Translate contentKey="vpay03App.payPartnerLog.payLogProductTypeCode">Pay Log Product Type Code</Translate>
                  </Label>
                  <AvField id="pay-partner-log-payLogProductTypeCode" type="text" name="payLogProductTypeCode" />
                </AvGroup>
                <AvGroup>
                  <Label id="payLogResuleCodeLabel" for="payLogResuleCode">
                    <Translate contentKey="vpay03App.payPartnerLog.payLogResuleCode">Pay Log Resule Code</Translate>
                  </Label>
                  <AvField id="pay-partner-log-payLogResuleCode" type="text" name="payLogResuleCode" />
                </AvGroup>
                <AvGroup>
                  <Label id="payLogResuleDescLabel" for="payLogResuleDesc">
                    <Translate contentKey="vpay03App.payPartnerLog.payLogResuleDesc">Pay Log Resule Desc</Translate>
                  </Label>
                  <AvField id="pay-partner-log-payLogResuleDesc" type="text" name="payLogResuleDesc" />
                </AvGroup>
                <AvGroup>
                  <Label id="payLogDateLabel" for="payLogDate">
                    <Translate contentKey="vpay03App.payPartnerLog.payLogDate">Pay Log Date</Translate>
                  </Label>
                  <AvInput
                    id="pay-partner-log-payLogDate"
                    type="datetime-local"
                    className="form-control"
                    name="payLogDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.payPartnerLogEntity.payLogDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="payLogUDF1Label" for="payLogUDF1">
                    <Translate contentKey="vpay03App.payPartnerLog.payLogUDF1">Pay Log UDF 1</Translate>
                  </Label>
                  <AvField id="pay-partner-log-payLogUDF1" type="text" name="payLogUDF1" />
                </AvGroup>
                <AvGroup>
                  <Label id="payLogUDF2Label" for="payLogUDF2">
                    <Translate contentKey="vpay03App.payPartnerLog.payLogUDF2">Pay Log UDF 2</Translate>
                  </Label>
                  <AvField id="pay-partner-log-payLogUDF2" type="text" name="payLogUDF2" />
                </AvGroup>
                <AvGroup>
                  <Label id="payLogUDF3Label" for="payLogUDF3">
                    <Translate contentKey="vpay03App.payPartnerLog.payLogUDF3">Pay Log UDF 3</Translate>
                  </Label>
                  <AvField id="pay-partner-log-payLogUDF3" type="text" name="payLogUDF3" />
                </AvGroup>
                <AvGroup>
                  <Label id="payLogUDF4Label" for="payLogUDF4">
                    <Translate contentKey="vpay03App.payPartnerLog.payLogUDF4">Pay Log UDF 4</Translate>
                  </Label>
                  <AvField id="pay-partner-log-payLogUDF4" type="text" name="payLogUDF4" />
                </AvGroup>
                <AvGroup>
                  <Label id="payLogUDF5Label" for="payLogUDF5">
                    <Translate contentKey="vpay03App.payPartnerLog.payLogUDF5">Pay Log UDF 5</Translate>
                  </Label>
                  <AvField id="pay-partner-log-payLogUDF5" type="text" name="payLogUDF5" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="pay-partner-log-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="vpay03App.payPartnerLog.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label for="payPartner.id">
                    <Translate contentKey="vpay03App.payPartnerLog.payPartner">Pay Partner</Translate>
                  </Label>
                  <AvInput id="pay-partner-log-payPartner" type="select" className="form-control" name="payPartner.id">
                    <option value="" key="0" />
                    {payPartners
                      ? payPartners.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/pay-partner-log" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  payPartners: storeState.payPartner.entities,
  payPartnerLogEntity: storeState.payPartnerLog.entity,
  loading: storeState.payPartnerLog.loading,
  updating: storeState.payPartnerLog.updating,
  updateSuccess: storeState.payPartnerLog.updateSuccess
});

const mapDispatchToProps = {
  getPayPartners,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PayPartnerLogUpdate);
