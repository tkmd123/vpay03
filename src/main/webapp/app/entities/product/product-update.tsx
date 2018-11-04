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
import { getEntity, updateEntity, createEntity, reset } from './product.reducer';
import { IProduct } from 'app/shared/model/product.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IProductUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IProductUpdateState {
  isNew: boolean;
  productTypeId: string;
}

export class ProductUpdate extends React.Component<IProductUpdateProps, IProductUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      productTypeId: '0',
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
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { productEntity } = this.props;
      const entity = {
        ...productEntity,
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
    this.props.history.push('/entity/product');
  };

  render() {
    const { productEntity, productTypes, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="vpay03App.product.home.createOrEditLabel">
              <Translate contentKey="vpay03App.product.home.createOrEditLabel">Create or edit a Product</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : productEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="product-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="productCodeLabel" for="productCode">
                    <Translate contentKey="vpay03App.product.productCode">Product Code</Translate>
                  </Label>
                  <AvField
                    id="product-productCode"
                    type="text"
                    name="productCode"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="productNameLabel" for="productName">
                    <Translate contentKey="vpay03App.product.productName">Product Name</Translate>
                  </Label>
                  <AvField
                    id="product-productName"
                    type="text"
                    name="productName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="productValueLabel" for="productValue">
                    <Translate contentKey="vpay03App.product.productValue">Product Value</Translate>
                  </Label>
                  <AvField id="product-productValue" type="string" className="form-control" name="productValue" />
                </AvGroup>
                <AvGroup>
                  <Label id="productDescLabel" for="productDesc">
                    <Translate contentKey="vpay03App.product.productDesc">Product Desc</Translate>
                  </Label>
                  <AvField id="product-productDesc" type="text" name="productDesc" />
                </AvGroup>
                <AvGroup>
                  <Label id="productUDF1Label" for="productUDF1">
                    <Translate contentKey="vpay03App.product.productUDF1">Product UDF 1</Translate>
                  </Label>
                  <AvField id="product-productUDF1" type="text" name="productUDF1" />
                </AvGroup>
                <AvGroup>
                  <Label id="productUDF2Label" for="productUDF2">
                    <Translate contentKey="vpay03App.product.productUDF2">Product UDF 2</Translate>
                  </Label>
                  <AvField id="product-productUDF2" type="text" name="productUDF2" />
                </AvGroup>
                <AvGroup>
                  <Label id="productUDF3Label" for="productUDF3">
                    <Translate contentKey="vpay03App.product.productUDF3">Product UDF 3</Translate>
                  </Label>
                  <AvField id="product-productUDF3" type="text" name="productUDF3" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="product-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="vpay03App.product.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label for="productType.id">
                    <Translate contentKey="vpay03App.product.productType">Product Type</Translate>
                  </Label>
                  <AvInput id="product-productType" type="select" className="form-control" name="productType.id">
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
                <Button tag={Link} id="cancel-save" to="/entity/product" replace color="info">
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
  productEntity: storeState.product.entity,
  loading: storeState.product.loading,
  updating: storeState.product.updating,
  updateSuccess: storeState.product.updateSuccess
});

const mapDispatchToProps = {
  getProductTypes,
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
)(ProductUpdate);
