import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPartner } from 'app/shared/model/partner.model';
import { getEntities as getPartners } from 'app/entities/partner/partner.reducer';
import { getEntity, updateEntity, createEntity, reset } from './partner-log.reducer';
import { IPartnerLog } from 'app/shared/model/partner-log.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPartnerLogUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPartnerLogUpdateState {
  isNew: boolean;
  partnerId: string;
}

export class PartnerLogUpdate extends React.Component<IPartnerLogUpdateProps, IPartnerLogUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      partnerId: '0',
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

    this.props.getPartners();
  }

  saveEntity = (event, errors, values) => {
    values.partnerLogDate = new Date(values.partnerLogDate);

    if (errors.length === 0) {
      const { partnerLogEntity } = this.props;
      const entity = {
        ...partnerLogEntity,
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
    this.props.history.push('/entity/partner-log');
  };

  render() {
    const { partnerLogEntity, partners, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="vpay03App.partnerLog.home.createOrEditLabel">
              <Translate contentKey="vpay03App.partnerLog.home.createOrEditLabel">Create or edit a PartnerLog</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : partnerLogEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="partner-log-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="partnerLogAmountLabel" for="partnerLogAmount">
                    <Translate contentKey="vpay03App.partnerLog.partnerLogAmount">Partner Log Amount</Translate>
                  </Label>
                  <AvField id="partner-log-partnerLogAmount" type="text" name="partnerLogAmount" />
                </AvGroup>
                <AvGroup>
                  <Label id="partnerLogTransRefLabel" for="partnerLogTransRef">
                    <Translate contentKey="vpay03App.partnerLog.partnerLogTransRef">Partner Log Trans Ref</Translate>
                  </Label>
                  <AvField id="partner-log-partnerLogTransRef" type="text" name="partnerLogTransRef" />
                </AvGroup>
                <AvGroup>
                  <Label id="partnerLogUsernameLabel" for="partnerLogUsername">
                    <Translate contentKey="vpay03App.partnerLog.partnerLogUsername">Partner Log Username</Translate>
                  </Label>
                  <AvField id="partner-log-partnerLogUsername" type="text" name="partnerLogUsername" />
                </AvGroup>
                <AvGroup>
                  <Label id="partnerLogProductTypeCodeLabel" for="partnerLogProductTypeCode">
                    <Translate contentKey="vpay03App.partnerLog.partnerLogProductTypeCode">Partner Log Product Type Code</Translate>
                  </Label>
                  <AvField id="partner-log-partnerLogProductTypeCode" type="text" name="partnerLogProductTypeCode" />
                </AvGroup>
                <AvGroup>
                  <Label id="partnerLogResultCodeLabel" for="partnerLogResultCode">
                    <Translate contentKey="vpay03App.partnerLog.partnerLogResultCode">Partner Log Result Code</Translate>
                  </Label>
                  <AvField id="partner-log-partnerLogResultCode" type="text" name="partnerLogResultCode" />
                </AvGroup>
                <AvGroup>
                  <Label id="partnerLogResultDescLabel" for="partnerLogResultDesc">
                    <Translate contentKey="vpay03App.partnerLog.partnerLogResultDesc">Partner Log Result Desc</Translate>
                  </Label>
                  <AvField id="partner-log-partnerLogResultDesc" type="text" name="partnerLogResultDesc" />
                </AvGroup>
                <AvGroup>
                  <Label id="partnerLogDateLabel" for="partnerLogDate">
                    <Translate contentKey="vpay03App.partnerLog.partnerLogDate">Partner Log Date</Translate>
                  </Label>
                  <AvInput
                    id="partner-log-partnerLogDate"
                    type="datetime-local"
                    className="form-control"
                    name="partnerLogDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.partnerLogEntity.partnerLogDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="partnerLogUDF1Label" for="partnerLogUDF1">
                    <Translate contentKey="vpay03App.partnerLog.partnerLogUDF1">Partner Log UDF 1</Translate>
                  </Label>
                  <AvField id="partner-log-partnerLogUDF1" type="text" name="partnerLogUDF1" />
                </AvGroup>
                <AvGroup>
                  <Label id="partnerLogUDF2Label" for="partnerLogUDF2">
                    <Translate contentKey="vpay03App.partnerLog.partnerLogUDF2">Partner Log UDF 2</Translate>
                  </Label>
                  <AvField id="partner-log-partnerLogUDF2" type="text" name="partnerLogUDF2" />
                </AvGroup>
                <AvGroup>
                  <Label id="partnerLogUDF3Label" for="partnerLogUDF3">
                    <Translate contentKey="vpay03App.partnerLog.partnerLogUDF3">Partner Log UDF 3</Translate>
                  </Label>
                  <AvField id="partner-log-partnerLogUDF3" type="text" name="partnerLogUDF3" />
                </AvGroup>
                <AvGroup>
                  <Label id="partnerLogUDF4Label" for="partnerLogUDF4">
                    <Translate contentKey="vpay03App.partnerLog.partnerLogUDF4">Partner Log UDF 4</Translate>
                  </Label>
                  <AvField id="partner-log-partnerLogUDF4" type="text" name="partnerLogUDF4" />
                </AvGroup>
                <AvGroup>
                  <Label id="partnerLogUDF5Label" for="partnerLogUDF5">
                    <Translate contentKey="vpay03App.partnerLog.partnerLogUDF5">Partner Log UDF 5</Translate>
                  </Label>
                  <AvField id="partner-log-partnerLogUDF5" type="text" name="partnerLogUDF5" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="partner-log-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="vpay03App.partnerLog.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label for="partner.id">
                    <Translate contentKey="vpay03App.partnerLog.partner">Partner</Translate>
                  </Label>
                  <AvInput id="partner-log-partner" type="select" className="form-control" name="partner.id">
                    <option value="" key="0" />
                    {partners
                      ? partners.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/partner-log" replace color="info">
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
  partners: storeState.partner.entities,
  partnerLogEntity: storeState.partnerLog.entity,
  loading: storeState.partnerLog.loading,
  updating: storeState.partnerLog.updating,
  updateSuccess: storeState.partnerLog.updateSuccess
});

const mapDispatchToProps = {
  getPartners,
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
)(PartnerLogUpdate);
