import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IWalletRule } from 'app/shared/model/wallet-rule.model';
import { getEntities as getWalletRules } from 'app/entities/wallet-rule/wallet-rule.reducer';
import { getEntity, updateEntity, createEntity, reset } from './wallet-rule-rate.reducer';
import { IWalletRuleRate } from 'app/shared/model/wallet-rule-rate.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IWalletRuleRateUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IWalletRuleRateUpdateState {
  isNew: boolean;
  walletRuleId: string;
}

export class WalletRuleRateUpdate extends React.Component<IWalletRuleRateUpdateProps, IWalletRuleRateUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      walletRuleId: '0',
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

    this.props.getWalletRules();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { walletRuleRateEntity } = this.props;
      const entity = {
        ...walletRuleRateEntity,
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
    this.props.history.push('/entity/wallet-rule-rate');
  };

  render() {
    const { walletRuleRateEntity, walletRules, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="vpay03App.walletRuleRate.home.createOrEditLabel">
              <Translate contentKey="vpay03App.walletRuleRate.home.createOrEditLabel">Create or edit a WalletRuleRate</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : walletRuleRateEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="wallet-rule-rate-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="walletRuleRateCodeLabel" for="walletRuleRateCode">
                    <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateCode">Wallet Rule Rate Code</Translate>
                  </Label>
                  <AvField
                    id="wallet-rule-rate-walletRuleRateCode"
                    type="text"
                    name="walletRuleRateCode"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="walletRuleRateNameLabel" for="walletRuleRateName">
                    <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateName">Wallet Rule Rate Name</Translate>
                  </Label>
                  <AvField
                    id="wallet-rule-rate-walletRuleRateName"
                    type="text"
                    name="walletRuleRateName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="walletRuleRateDescLabel" for="walletRuleRateDesc">
                    <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateDesc">Wallet Rule Rate Desc</Translate>
                  </Label>
                  <AvField id="wallet-rule-rate-walletRuleRateDesc" type="text" name="walletRuleRateDesc" />
                </AvGroup>
                <AvGroup>
                  <Label id="walletRuleRateFromValueLabel" for="walletRuleRateFromValue">
                    <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateFromValue">Wallet Rule Rate From Value</Translate>
                  </Label>
                  <AvField
                    id="wallet-rule-rate-walletRuleRateFromValue"
                    type="string"
                    className="form-control"
                    name="walletRuleRateFromValue"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="walletRuleRateToValueLabel" for="walletRuleRateToValue">
                    <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateToValue">Wallet Rule Rate To Value</Translate>
                  </Label>
                  <AvField
                    id="wallet-rule-rate-walletRuleRateToValue"
                    type="string"
                    className="form-control"
                    name="walletRuleRateToValue"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="walletRuleRateDiscountLabel" for="walletRuleRateDiscount">
                    <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateDiscount">Wallet Rule Rate Discount</Translate>
                  </Label>
                  <AvField
                    id="wallet-rule-rate-walletRuleRateDiscount"
                    type="string"
                    className="form-control"
                    name="walletRuleRateDiscount"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="walletRuleRateUDF1Label" for="walletRuleRateUDF1">
                    <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateUDF1">Wallet Rule Rate UDF 1</Translate>
                  </Label>
                  <AvField id="wallet-rule-rate-walletRuleRateUDF1" type="text" name="walletRuleRateUDF1" />
                </AvGroup>
                <AvGroup>
                  <Label id="walletRuleRateUDF2Label" for="walletRuleRateUDF2">
                    <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateUDF2">Wallet Rule Rate UDF 2</Translate>
                  </Label>
                  <AvField id="wallet-rule-rate-walletRuleRateUDF2" type="text" name="walletRuleRateUDF2" />
                </AvGroup>
                <AvGroup>
                  <Label id="walletRuleRateUDF3Label" for="walletRuleRateUDF3">
                    <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateUDF3">Wallet Rule Rate UDF 3</Translate>
                  </Label>
                  <AvField id="wallet-rule-rate-walletRuleRateUDF3" type="text" name="walletRuleRateUDF3" />
                </AvGroup>
                <AvGroup>
                  <Label id="walletRuleRateUDF4Label" for="walletRuleRateUDF4">
                    <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateUDF4">Wallet Rule Rate UDF 4</Translate>
                  </Label>
                  <AvField id="wallet-rule-rate-walletRuleRateUDF4" type="text" name="walletRuleRateUDF4" />
                </AvGroup>
                <AvGroup>
                  <Label id="walletRuleRateUDF5Label" for="walletRuleRateUDF5">
                    <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateUDF5">Wallet Rule Rate UDF 5</Translate>
                  </Label>
                  <AvField id="wallet-rule-rate-walletRuleRateUDF5" type="text" name="walletRuleRateUDF5" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="wallet-rule-rate-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="vpay03App.walletRuleRate.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label for="walletRule.id">
                    <Translate contentKey="vpay03App.walletRuleRate.walletRule">Wallet Rule</Translate>
                  </Label>
                  <AvInput id="wallet-rule-rate-walletRule" type="select" className="form-control" name="walletRule.id">
                    <option value="" key="0" />
                    {walletRules
                      ? walletRules.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/wallet-rule-rate" replace color="info">
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
  walletRules: storeState.walletRule.entities,
  walletRuleRateEntity: storeState.walletRuleRate.entity,
  loading: storeState.walletRuleRate.loading,
  updating: storeState.walletRuleRate.updating,
  updateSuccess: storeState.walletRuleRate.updateSuccess
});

const mapDispatchToProps = {
  getWalletRules,
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
)(WalletRuleRateUpdate);
