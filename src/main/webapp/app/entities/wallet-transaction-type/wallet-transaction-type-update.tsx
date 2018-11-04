import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './wallet-transaction-type.reducer';
import { IWalletTransactionType } from 'app/shared/model/wallet-transaction-type.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IWalletTransactionTypeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IWalletTransactionTypeUpdateState {
  isNew: boolean;
}

export class WalletTransactionTypeUpdate extends React.Component<IWalletTransactionTypeUpdateProps, IWalletTransactionTypeUpdateState> {
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
      const { walletTransactionTypeEntity } = this.props;
      const entity = {
        ...walletTransactionTypeEntity,
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
    this.props.history.push('/entity/wallet-transaction-type');
  };

  render() {
    const { walletTransactionTypeEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="vpay03App.walletTransactionType.home.createOrEditLabel">
              <Translate contentKey="vpay03App.walletTransactionType.home.createOrEditLabel">
                Create or edit a WalletTransactionType
              </Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : walletTransactionTypeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="wallet-transaction-type-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="walletTransTypeCodeLabel" for="walletTransTypeCode">
                    <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeCode">Wallet Trans Type Code</Translate>
                  </Label>
                  <AvField
                    id="wallet-transaction-type-walletTransTypeCode"
                    type="text"
                    name="walletTransTypeCode"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="walletTransTypeNameLabel" for="walletTransTypeName">
                    <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeName">Wallet Trans Type Name</Translate>
                  </Label>
                  <AvField
                    id="wallet-transaction-type-walletTransTypeName"
                    type="text"
                    name="walletTransTypeName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="walletTransTypeDescLabel" for="walletTransTypeDesc">
                    <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeDesc">Wallet Trans Type Desc</Translate>
                  </Label>
                  <AvField id="wallet-transaction-type-walletTransTypeDesc" type="text" name="walletTransTypeDesc" />
                </AvGroup>
                <AvGroup>
                  <Label id="walletTransTypeFlagLabel" for="walletTransTypeFlag">
                    <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeFlag">Wallet Trans Type Flag</Translate>
                  </Label>
                  <AvField
                    id="wallet-transaction-type-walletTransTypeFlag"
                    type="string"
                    className="form-control"
                    name="walletTransTypeFlag"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="walletTransTypeUDF1Label" for="walletTransTypeUDF1">
                    <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeUDF1">Wallet Trans Type UDF 1</Translate>
                  </Label>
                  <AvField id="wallet-transaction-type-walletTransTypeUDF1" type="text" name="walletTransTypeUDF1" />
                </AvGroup>
                <AvGroup>
                  <Label id="walletTransTypeUDF2Label" for="walletTransTypeUDF2">
                    <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeUDF2">Wallet Trans Type UDF 2</Translate>
                  </Label>
                  <AvField id="wallet-transaction-type-walletTransTypeUDF2" type="text" name="walletTransTypeUDF2" />
                </AvGroup>
                <AvGroup>
                  <Label id="walletTransTypeUDF3Label" for="walletTransTypeUDF3">
                    <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeUDF3">Wallet Trans Type UDF 3</Translate>
                  </Label>
                  <AvField id="wallet-transaction-type-walletTransTypeUDF3" type="text" name="walletTransTypeUDF3" />
                </AvGroup>
                <AvGroup>
                  <Label id="walletTransTypeUDF4Label" for="walletTransTypeUDF4">
                    <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeUDF4">Wallet Trans Type UDF 4</Translate>
                  </Label>
                  <AvField id="wallet-transaction-type-walletTransTypeUDF4" type="text" name="walletTransTypeUDF4" />
                </AvGroup>
                <AvGroup>
                  <Label id="walletTransTypeUDF5Label" for="walletTransTypeUDF5">
                    <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeUDF5">Wallet Trans Type UDF 5</Translate>
                  </Label>
                  <AvField id="wallet-transaction-type-walletTransTypeUDF5" type="text" name="walletTransTypeUDF5" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="wallet-transaction-type-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="vpay03App.walletTransactionType.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/wallet-transaction-type" replace color="info">
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
  walletTransactionTypeEntity: storeState.walletTransactionType.entity,
  loading: storeState.walletTransactionType.loading,
  updating: storeState.walletTransactionType.updating,
  updateSuccess: storeState.walletTransactionType.updateSuccess
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
)(WalletTransactionTypeUpdate);
