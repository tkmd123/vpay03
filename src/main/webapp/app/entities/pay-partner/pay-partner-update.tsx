import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './pay-partner.reducer';
import { IPayPartner } from 'app/shared/model/pay-partner.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPayPartnerUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPayPartnerUpdateState {
  isNew: boolean;
}

export class PayPartnerUpdate extends React.Component<IPayPartnerUpdateProps, IPayPartnerUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (!this.state.isNew) {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { payPartnerEntity } = this.props;
      const entity = {
        ...payPartnerEntity,
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
    this.props.history.push('/entity/pay-partner');
  };

  render() {
    const { payPartnerEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="vpay03App.payPartner.home.createOrEditLabel">
              <Translate contentKey="vpay03App.payPartner.home.createOrEditLabel">Create or edit a PayPartner</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : payPartnerEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="pay-partner-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="payPartnerCodeLabel" for="payPartnerCode">
                    <Translate contentKey="vpay03App.payPartner.payPartnerCode">Pay Partner Code</Translate>
                  </Label>
                  <AvField
                    id="pay-partner-payPartnerCode"
                    type="text"
                    name="payPartnerCode"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="payPartnerNameLabel" for="payPartnerName">
                    <Translate contentKey="vpay03App.payPartner.payPartnerName">Pay Partner Name</Translate>
                  </Label>
                  <AvField
                    id="pay-partner-payPartnerName"
                    type="text"
                    name="payPartnerName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="payPartnerDescLabel" for="payPartnerDesc">
                    <Translate contentKey="vpay03App.payPartner.payPartnerDesc">Pay Partner Desc</Translate>
                  </Label>
                  <AvField id="pay-partner-payPartnerDesc" type="text" name="payPartnerDesc" />
                </AvGroup>
                <AvGroup>
                  <Label id="payPartnerUsernameLabel" for="payPartnerUsername">
                    <Translate contentKey="vpay03App.payPartner.payPartnerUsername">Pay Partner Username</Translate>
                  </Label>
                  <AvField id="pay-partner-payPartnerUsername" type="text" name="payPartnerUsername" />
                </AvGroup>
                <AvGroup>
                  <Label id="payPartnerSecretKeyLabel" for="payPartnerSecretKey">
                    <Translate contentKey="vpay03App.payPartner.payPartnerSecretKey">Pay Partner Secret Key</Translate>
                  </Label>
                  <AvField id="pay-partner-payPartnerSecretKey" type="text" name="payPartnerSecretKey" />
                </AvGroup>
                <AvGroup>
                  <Label id="payPartnerStatusLabel" for="payPartnerStatus">
                    <Translate contentKey="vpay03App.payPartner.payPartnerStatus">Pay Partner Status</Translate>
                  </Label>
                  <AvField id="pay-partner-payPartnerStatus" type="text" name="payPartnerStatus" />
                </AvGroup>
                <AvGroup>
                  <Label id="payPartnerUDF1Label" for="payPartnerUDF1">
                    <Translate contentKey="vpay03App.payPartner.payPartnerUDF1">Pay Partner UDF 1</Translate>
                  </Label>
                  <AvField id="pay-partner-payPartnerUDF1" type="text" name="payPartnerUDF1" />
                </AvGroup>
                <AvGroup>
                  <Label id="payPartnerUDF2Label" for="payPartnerUDF2">
                    <Translate contentKey="vpay03App.payPartner.payPartnerUDF2">Pay Partner UDF 2</Translate>
                  </Label>
                  <AvField id="pay-partner-payPartnerUDF2" type="text" name="payPartnerUDF2" />
                </AvGroup>
                <AvGroup>
                  <Label id="payPartnerUDF3Label" for="payPartnerUDF3">
                    <Translate contentKey="vpay03App.payPartner.payPartnerUDF3">Pay Partner UDF 3</Translate>
                  </Label>
                  <AvField id="pay-partner-payPartnerUDF3" type="text" name="payPartnerUDF3" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="pay-partner-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="vpay03App.payPartner.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/pay-partner" replace color="info">
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
  payPartnerEntity: storeState.payPartner.entity,
  loading: storeState.payPartner.loading,
  updating: storeState.payPartner.updating,
  updateSuccess: storeState.payPartner.updateSuccess
});

const mapDispatchToProps = {
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
)(PayPartnerUpdate);
