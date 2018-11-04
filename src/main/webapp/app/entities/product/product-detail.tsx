import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './product.reducer';
import { IProduct } from 'app/shared/model/product.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProductDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ProductDetail extends React.Component<IProductDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { productEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="vpay03App.product.detail.title">Product</Translate> [<b>{productEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="productCode">
                <Translate contentKey="vpay03App.product.productCode">Product Code</Translate>
              </span>
            </dt>
            <dd>{productEntity.productCode}</dd>
            <dt>
              <span id="productName">
                <Translate contentKey="vpay03App.product.productName">Product Name</Translate>
              </span>
            </dt>
            <dd>{productEntity.productName}</dd>
            <dt>
              <span id="productValue">
                <Translate contentKey="vpay03App.product.productValue">Product Value</Translate>
              </span>
            </dt>
            <dd>{productEntity.productValue}</dd>
            <dt>
              <span id="productDesc">
                <Translate contentKey="vpay03App.product.productDesc">Product Desc</Translate>
              </span>
            </dt>
            <dd>{productEntity.productDesc}</dd>
            <dt>
              <span id="productUDF1">
                <Translate contentKey="vpay03App.product.productUDF1">Product UDF 1</Translate>
              </span>
            </dt>
            <dd>{productEntity.productUDF1}</dd>
            <dt>
              <span id="productUDF2">
                <Translate contentKey="vpay03App.product.productUDF2">Product UDF 2</Translate>
              </span>
            </dt>
            <dd>{productEntity.productUDF2}</dd>
            <dt>
              <span id="productUDF3">
                <Translate contentKey="vpay03App.product.productUDF3">Product UDF 3</Translate>
              </span>
            </dt>
            <dd>{productEntity.productUDF3}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="vpay03App.product.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{productEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="vpay03App.product.productType">Product Type</Translate>
            </dt>
            <dd>{productEntity.productType ? productEntity.productType.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/product" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/product/${productEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ product }: IRootState) => ({
  productEntity: product.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ProductDetail);
