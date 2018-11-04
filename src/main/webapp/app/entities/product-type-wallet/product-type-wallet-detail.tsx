import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './product-type-wallet.reducer';
import { IProductTypeWallet } from 'app/shared/model/product-type-wallet.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProductTypeWalletDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ProductTypeWalletDetail extends React.Component<IProductTypeWalletDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { productTypeWalletEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="vpay03App.productTypeWallet.detail.title">ProductTypeWallet</Translate> [<b>
              {productTypeWalletEntity.id}
            </b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="productTypeWalletUDF1">
                <Translate contentKey="vpay03App.productTypeWallet.productTypeWalletUDF1">Product Type Wallet UDF 1</Translate>
              </span>
            </dt>
            <dd>{productTypeWalletEntity.productTypeWalletUDF1}</dd>
            <dt>
              <span id="productTypeWalletUDF2">
                <Translate contentKey="vpay03App.productTypeWallet.productTypeWalletUDF2">Product Type Wallet UDF 2</Translate>
              </span>
            </dt>
            <dd>{productTypeWalletEntity.productTypeWalletUDF2}</dd>
            <dt>
              <span id="productTypeWalletUDF3">
                <Translate contentKey="vpay03App.productTypeWallet.productTypeWalletUDF3">Product Type Wallet UDF 3</Translate>
              </span>
            </dt>
            <dd>{productTypeWalletEntity.productTypeWalletUDF3}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="vpay03App.productTypeWallet.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{productTypeWalletEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="vpay03App.productTypeWallet.productType">Product Type</Translate>
            </dt>
            <dd>{productTypeWalletEntity.productType ? productTypeWalletEntity.productType.id : ''}</dd>
            <dt>
              <Translate contentKey="vpay03App.productTypeWallet.wallet">Wallet</Translate>
            </dt>
            <dd>{productTypeWalletEntity.wallet ? productTypeWalletEntity.wallet.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/product-type-wallet" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/product-type-wallet/${productTypeWalletEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ productTypeWallet }: IRootState) => ({
  productTypeWalletEntity: productTypeWallet.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ProductTypeWalletDetail);
