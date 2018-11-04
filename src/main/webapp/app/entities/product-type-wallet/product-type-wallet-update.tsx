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
import { IWallet } from 'app/shared/model/wallet.model';
import { getEntities as getWallets } from 'app/entities/wallet/wallet.reducer';
import { getEntity, updateEntity, createEntity, reset } from './product-type-wallet.reducer';
import { IProductTypeWallet } from 'app/shared/model/product-type-wallet.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IProductTypeWalletUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IProductTypeWalletUpdateState {
  isNew: boolean;
  productTypeId: string;
  walletId: string;
}

export class ProductTypeWalletUpdate extends React.Component<IProductTypeWalletUpdateProps, IProductTypeWalletUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      productTypeId: '0',
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

    this.props.getProductTypes();
    this.props.getWallets();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { productTypeWalletEntity } = this.props;
      const entity = {
        ...productTypeWalletEntity,
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
    this.props.history.push('/entity/product-type-wallet');
  };

  render() {
    const { productTypeWalletEntity, productTypes, wallets, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="vpay03App.productTypeWallet.home.createOrEditLabel">
              <Translate contentKey="vpay03App.productTypeWallet.home.createOrEditLabel">Create or edit a ProductTypeWallet</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : productTypeWalletEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="product-type-wallet-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="productTypeWalletUDF1Label" for="productTypeWalletUDF1">
                    <Translate contentKey="vpay03App.productTypeWallet.productTypeWalletUDF1">Product Type Wallet UDF 1</Translate>
                  </Label>
                  <AvField id="product-type-wallet-productTypeWalletUDF1" type="text" name="productTypeWalletUDF1" />
                </AvGroup>
                <AvGroup>
                  <Label id="productTypeWalletUDF2Label" for="productTypeWalletUDF2">
                    <Translate contentKey="vpay03App.productTypeWallet.productTypeWalletUDF2">Product Type Wallet UDF 2</Translate>
                  </Label>
                  <AvField id="product-type-wallet-productTypeWalletUDF2" type="text" name="productTypeWalletUDF2" />
                </AvGroup>
                <AvGroup>
                  <Label id="productTypeWalletUDF3Label" for="productTypeWalletUDF3">
                    <Translate contentKey="vpay03App.productTypeWallet.productTypeWalletUDF3">Product Type Wallet UDF 3</Translate>
                  </Label>
                  <AvField id="product-type-wallet-productTypeWalletUDF3" type="text" name="productTypeWalletUDF3" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="product-type-wallet-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="vpay03App.productTypeWallet.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label for="productType.id">
                    <Translate contentKey="vpay03App.productTypeWallet.productType">Product Type</Translate>
                  </Label>
                  <AvInput id="product-type-wallet-productType" type="select" className="form-control" name="productType.id">
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
                  <Label for="wallet.id">
                    <Translate contentKey="vpay03App.productTypeWallet.wallet">Wallet</Translate>
                  </Label>
                  <AvInput id="product-type-wallet-wallet" type="select" className="form-control" name="wallet.id">
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
                <Button tag={Link} id="cancel-save" to="/entity/product-type-wallet" replace color="info">
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
  wallets: storeState.wallet.entities,
  productTypeWalletEntity: storeState.productTypeWallet.entity,
  loading: storeState.productTypeWallet.loading,
  updating: storeState.productTypeWallet.updating,
  updateSuccess: storeState.productTypeWallet.updateSuccess
});

const mapDispatchToProps = {
  getProductTypes,
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
)(ProductTypeWalletUpdate);
