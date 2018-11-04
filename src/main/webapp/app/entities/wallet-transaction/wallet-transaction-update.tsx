import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IWalletTransactionType } from 'app/shared/model/wallet-transaction-type.model';
import { getEntities as getWalletTransactionTypes } from 'app/entities/wallet-transaction-type/wallet-transaction-type.reducer';
import { IWallet } from 'app/shared/model/wallet.model';
import { getEntities as getWallets } from 'app/entities/wallet/wallet.reducer';
import { getEntity, updateEntity, createEntity, reset } from './wallet-transaction.reducer';
import { IWalletTransaction } from 'app/shared/model/wallet-transaction.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IWalletTransactionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IWalletTransactionUpdateState {
  isNew: boolean;
  walletTransactionTypeId: string;
  walletId: string;
}

export class WalletTransactionUpdate extends React.Component<IWalletTransactionUpdateProps, IWalletTransactionUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      walletTransactionTypeId: '0',
      walletId: '0',
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

    this.props.getWalletTransactionTypes();
    this.props.getWallets();
  }

  saveEntity = (event, errors, values) => {
    values.walletTransDate = new Date(values.walletTransDate);

    if (errors.length === 0) {
      const { walletTransactionEntity } = this.props;
      const entity = {
        ...walletTransactionEntity,
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
    this.props.history.push('/entity/wallet-transaction');
  };

  render() {
    const { walletTransactionEntity, walletTransactionTypes, wallets, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="vpay03App.walletTransaction.home.createOrEditLabel">
              <Translate contentKey="vpay03App.walletTransaction.home.createOrEditLabel">Create or edit a WalletTransaction</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : walletTransactionEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="wallet-transaction-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="walletTransAmountLabel" for="walletTransAmount">
                    <Translate contentKey="vpay03App.walletTransaction.walletTransAmount">Wallet Trans Amount</Translate>
                  </Label>
                  <AvField
                    id="wallet-transaction-walletTransAmount"
                    type="string"
                    className="form-control"
                    name="walletTransAmount"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="walletTransDateLabel" for="walletTransDate">
                    <Translate contentKey="vpay03App.walletTransaction.walletTransDate">Wallet Trans Date</Translate>
                  </Label>
                  <AvInput
                    id="wallet-transaction-walletTransDate"
                    type="datetime-local"
                    className="form-control"
                    name="walletTransDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.walletTransactionEntity.walletTransDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="walletTransRefLabel" for="walletTransRef">
                    <Translate contentKey="vpay03App.walletTransaction.walletTransRef">Wallet Trans Ref</Translate>
                  </Label>
                  <AvField id="wallet-transaction-walletTransRef" type="text" name="walletTransRef" />
                </AvGroup>
                <AvGroup>
                  <Label id="walletTransUsernameLabel" for="walletTransUsername">
                    <Translate contentKey="vpay03App.walletTransaction.walletTransUsername">Wallet Trans Username</Translate>
                  </Label>
                  <AvField id="wallet-transaction-walletTransUsername" type="text" name="walletTransUsername" />
                </AvGroup>
                <AvGroup>
                  <Label id="walletTransStatusLabel" for="walletTransStatus">
                    <Translate contentKey="vpay03App.walletTransaction.walletTransStatus">Wallet Trans Status</Translate>
                  </Label>
                  <AvField id="wallet-transaction-walletTransStatus" type="text" name="walletTransStatus" />
                </AvGroup>
                <AvGroup>
                  <Label id="walletTransUDF1Label" for="walletTransUDF1">
                    <Translate contentKey="vpay03App.walletTransaction.walletTransUDF1">Wallet Trans UDF 1</Translate>
                  </Label>
                  <AvField id="wallet-transaction-walletTransUDF1" type="text" name="walletTransUDF1" />
                </AvGroup>
                <AvGroup>
                  <Label id="walletTransUDF2Label" for="walletTransUDF2">
                    <Translate contentKey="vpay03App.walletTransaction.walletTransUDF2">Wallet Trans UDF 2</Translate>
                  </Label>
                  <AvField id="wallet-transaction-walletTransUDF2" type="text" name="walletTransUDF2" />
                </AvGroup>
                <AvGroup>
                  <Label id="walletTransUDF3Label" for="walletTransUDF3">
                    <Translate contentKey="vpay03App.walletTransaction.walletTransUDF3">Wallet Trans UDF 3</Translate>
                  </Label>
                  <AvField id="wallet-transaction-walletTransUDF3" type="text" name="walletTransUDF3" />
                </AvGroup>
                <AvGroup>
                  <Label id="walletTransUDF4Label" for="walletTransUDF4">
                    <Translate contentKey="vpay03App.walletTransaction.walletTransUDF4">Wallet Trans UDF 4</Translate>
                  </Label>
                  <AvField id="wallet-transaction-walletTransUDF4" type="text" name="walletTransUDF4" />
                </AvGroup>
                <AvGroup>
                  <Label id="walletTransUDF5Label" for="walletTransUDF5">
                    <Translate contentKey="vpay03App.walletTransaction.walletTransUDF5">Wallet Trans UDF 5</Translate>
                  </Label>
                  <AvField id="wallet-transaction-walletTransUDF5" type="text" name="walletTransUDF5" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="wallet-transaction-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="vpay03App.walletTransaction.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label for="walletTransactionType.id">
                    <Translate contentKey="vpay03App.walletTransaction.walletTransactionType">Wallet Transaction Type</Translate>
                  </Label>
                  <AvInput
                    id="wallet-transaction-walletTransactionType"
                    type="select"
                    className="form-control"
                    name="walletTransactionType.id"
                  >
                    <option value="" key="0" />
                    {walletTransactionTypes
                      ? walletTransactionTypes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="wallet.id">
                    <Translate contentKey="vpay03App.walletTransaction.wallet">Wallet</Translate>
                  </Label>
                  <AvInput id="wallet-transaction-wallet" type="select" className="form-control" name="wallet.id">
                    <option value="" key="0" />
                    {wallets
                      ? wallets.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/wallet-transaction" replace color="info">
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
  walletTransactionTypes: storeState.walletTransactionType.entities,
  wallets: storeState.wallet.entities,
  walletTransactionEntity: storeState.walletTransaction.entity,
  loading: storeState.walletTransaction.loading,
  updating: storeState.walletTransaction.updating,
  updateSuccess: storeState.walletTransaction.updateSuccess
});

const mapDispatchToProps = {
  getWalletTransactionTypes,
  getWallets,
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
)(WalletTransactionUpdate);
