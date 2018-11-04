import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IProductType } from 'app/shared/model/product-type.model';
import { getEntities as getProductTypes } from 'app/entities/product-type/product-type.reducer';
import { IPayPartner } from 'app/shared/model/pay-partner.model';
import { getEntities as getPayPartners } from 'app/entities/pay-partner/pay-partner.reducer';
import { getEntity, updateEntity, createEntity, reset } from './wallet-rule.reducer';
import { IWalletRule } from 'app/shared/model/wallet-rule.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IWalletRuleUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IWalletRuleUpdateState {
  isNew: boolean;
  productTypeId: string;
  payPartnerId: string;
}

export class WalletRuleUpdate extends React.Component<IWalletRuleUpdateProps, IWalletRuleUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      productTypeId: '0',
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
    if (!this.state.isNew) {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getProductTypes();
    this.props.getPayPartners();
  }

  saveEntity = (event, errors, values) => {
    values.walletRuleFromDate = new Date(values.walletRuleFromDate);
    values.walletRuleToDate = new Date(values.walletRuleToDate);

    if (errors.length === 0) {
      const { walletRuleEntity } = this.props;
      const entity = {
        ...walletRuleEntity,
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
    this.props.history.push('/entity/wallet-rule');
  };

  render() {
    const { walletRuleEntity, productTypes, payPartners, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="vpay03App.walletRule.home.createOrEditLabel">
              <Translate contentKey="vpay03App.walletRule.home.createOrEditLabel">Create or edit a WalletRule</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : walletRuleEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="wallet-rule-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="walletRuleCodeLabel" for="walletRuleCode">
                    <Translate contentKey="vpay03App.walletRule.walletRuleCode">Wallet Rule Code</Translate>
                  </Label>
                  <AvField
                    id="wallet-rule-walletRuleCode"
                    type="text"
                    name="walletRuleCode"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="walletRuleNameLabel" for="walletRuleName">
                    <Translate contentKey="vpay03App.walletRule.walletRuleName">Wallet Rule Name</Translate>
                  </Label>
                  <AvField
                    id="wallet-rule-walletRuleName"
                    type="text"
                    name="walletRuleName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="walletRuleDescLabel" for="walletRuleDesc">
                    <Translate contentKey="vpay03App.walletRule.walletRuleDesc">Wallet Rule Desc</Translate>
                  </Label>
                  <AvField id="wallet-rule-walletRuleDesc" type="text" name="walletRuleDesc" />
                </AvGroup>
                <AvGroup>
                  <Label id="walletRuleFromDateLabel" for="walletRuleFromDate">
                    <Translate contentKey="vpay03App.walletRule.walletRuleFromDate">Wallet Rule From Date</Translate>
                  </Label>
                  <AvInput
                    id="wallet-rule-walletRuleFromDate"
                    type="datetime-local"
                    className="form-control"
                    name="walletRuleFromDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.walletRuleEntity.walletRuleFromDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="walletRuleToDateLabel" for="walletRuleToDate">
                    <Translate contentKey="vpay03App.walletRule.walletRuleToDate">Wallet Rule To Date</Translate>
                  </Label>
                  <AvInput
                    id="wallet-rule-walletRuleToDate"
                    type="datetime-local"
                    className="form-control"
                    name="walletRuleToDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.walletRuleEntity.walletRuleToDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="walletRuleUDF1Label" for="walletRuleUDF1">
                    <Translate contentKey="vpay03App.walletRule.walletRuleUDF1">Wallet Rule UDF 1</Translate>
                  </Label>
                  <AvField id="wallet-rule-walletRuleUDF1" type="text" name="walletRuleUDF1" />
                </AvGroup>
                <AvGroup>
                  <Label id="walletRuleUDF2Label" for="walletRuleUDF2">
                    <Translate contentKey="vpay03App.walletRule.walletRuleUDF2">Wallet Rule UDF 2</Translate>
                  </Label>
                  <AvField id="wallet-rule-walletRuleUDF2" type="text" name="walletRuleUDF2" />
                </AvGroup>
                <AvGroup>
                  <Label id="walletRuleUDF3Label" for="walletRuleUDF3">
                    <Translate contentKey="vpay03App.walletRule.walletRuleUDF3">Wallet Rule UDF 3</Translate>
                  </Label>
                  <AvField id="wallet-rule-walletRuleUDF3" type="text" name="walletRuleUDF3" />
                </AvGroup>
                <AvGroup>
                  <Label id="walletRuleUDF4Label" for="walletRuleUDF4">
                    <Translate contentKey="vpay03App.walletRule.walletRuleUDF4">Wallet Rule UDF 4</Translate>
                  </Label>
                  <AvField id="wallet-rule-walletRuleUDF4" type="text" name="walletRuleUDF4" />
                </AvGroup>
                <AvGroup>
                  <Label id="walletRuleUDF5Label" for="walletRuleUDF5">
                    <Translate contentKey="vpay03App.walletRule.walletRuleUDF5">Wallet Rule UDF 5</Translate>
                  </Label>
                  <AvField id="wallet-rule-walletRuleUDF5" type="text" name="walletRuleUDF5" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="wallet-rule-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="vpay03App.walletRule.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label for="productType.id">
                    <Translate contentKey="vpay03App.walletRule.productType">Product Type</Translate>
                  </Label>
                  <AvInput id="wallet-rule-productType" type="select" className="form-control" name="productType.id">
                    <option value="" key="0" />
                    {productTypes
                      ? productTypes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="payPartner.id">
                    <Translate contentKey="vpay03App.walletRule.payPartner">Pay Partner</Translate>
                  </Label>
                  <AvInput id="wallet-rule-payPartner" type="select" className="form-control" name="payPartner.id">
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
                <Button tag={Link} id="cancel-save" to="/entity/wallet-rule" replace color="info">
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
  productTypes: storeState.productType.entities,
  payPartners: storeState.payPartner.entities,
  walletRuleEntity: storeState.walletRule.entity,
  loading: storeState.walletRule.loading,
  updating: storeState.walletRule.updating,
  updateSuccess: storeState.walletRule.updateSuccess
});

const mapDispatchToProps = {
  getProductTypes,
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
)(WalletRuleUpdate);
