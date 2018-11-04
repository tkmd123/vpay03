import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './product-type-wallet.reducer';
import { IProductTypeWallet } from 'app/shared/model/product-type-wallet.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProductTypeWalletProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class ProductTypeWallet extends React.Component<IProductTypeWalletProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { productTypeWalletList, match } = this.props;
    return (
      <div>
        <h2 id="product-type-wallet-heading">
          <Translate contentKey="vpay03App.productTypeWallet.home.title">Product Type Wallets</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="vpay03App.productTypeWallet.home.createLabel">Create new Product Type Wallet</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="vpay03App.productTypeWallet.productTypeWalletUDF1">Product Type Wallet UDF 1</Translate>
                </th>
                <th>
                  <Translate contentKey="vpay03App.productTypeWallet.productTypeWalletUDF2">Product Type Wallet UDF 2</Translate>
                </th>
                <th>
                  <Translate contentKey="vpay03App.productTypeWallet.productTypeWalletUDF3">Product Type Wallet UDF 3</Translate>
                </th>
                <th>
                  <Translate contentKey="vpay03App.productTypeWallet.isDeleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="vpay03App.productTypeWallet.productType">Product Type</Translate>
                </th>
                <th>
                  <Translate contentKey="vpay03App.productTypeWallet.wallet">Wallet</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {productTypeWalletList.map((productTypeWallet, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${productTypeWallet.id}`} color="link" size="sm">
                      {productTypeWallet.id}
                    </Button>
                  </td>
                  <td>{productTypeWallet.productTypeWalletUDF1}</td>
                  <td>{productTypeWallet.productTypeWalletUDF2}</td>
                  <td>{productTypeWallet.productTypeWalletUDF3}</td>
                  <td>{productTypeWallet.isDeleted ? 'true' : 'false'}</td>
                  <td>
                    {productTypeWallet.productType ? (
                      <Link to={`product-type/${productTypeWallet.productType.id}`}>{productTypeWallet.productType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {productTypeWallet.wallet ? (
                      <Link to={`wallet/${productTypeWallet.wallet.id}`}>{productTypeWallet.wallet.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${productTypeWallet.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${productTypeWallet.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${productTypeWallet.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ productTypeWallet }: IRootState) => ({
  productTypeWalletList: productTypeWallet.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ProductTypeWallet);
