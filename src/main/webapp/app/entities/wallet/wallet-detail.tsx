import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './wallet.reducer';
import { IWallet } from 'app/shared/model/wallet.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IWalletDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class WalletDetail extends React.Component<IWalletDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { walletEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="vpay03App.wallet.detail.title">Wallet</Translate> [<b>{walletEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="walletNumber">
                <Translate contentKey="vpay03App.wallet.walletNumber">Wallet Number</Translate>
              </span>
            </dt>
            <dd>{walletEntity.walletNumber}</dd>
            <dt>
              <span id="walletIsActive">
                <Translate contentKey="vpay03App.wallet.walletIsActive">Wallet Is Active</Translate>
              </span>
            </dt>
            <dd>{walletEntity.walletIsActive ? 'true' : 'false'}</dd>
            <dt>
              <span id="walletDesc">
                <Translate contentKey="vpay03App.wallet.walletDesc">Wallet Desc</Translate>
              </span>
            </dt>
            <dd>{walletEntity.walletDesc}</dd>
            <dt>
              <span id="walletUDF1">
                <Translate contentKey="vpay03App.wallet.walletUDF1">Wallet UDF 1</Translate>
              </span>
            </dt>
            <dd>{walletEntity.walletUDF1}</dd>
            <dt>
              <span id="walletUDF2">
                <Translate contentKey="vpay03App.wallet.walletUDF2">Wallet UDF 2</Translate>
              </span>
            </dt>
            <dd>{walletEntity.walletUDF2}</dd>
            <dt>
              <span id="walletUDF3">
                <Translate contentKey="vpay03App.wallet.walletUDF3">Wallet UDF 3</Translate>
              </span>
            </dt>
            <dd>{walletEntity.walletUDF3}</dd>
            <dt>
              <span id="walletUDF4">
                <Translate contentKey="vpay03App.wallet.walletUDF4">Wallet UDF 4</Translate>
              </span>
            </dt>
            <dd>{walletEntity.walletUDF4}</dd>
            <dt>
              <span id="walletUDF5">
                <Translate contentKey="vpay03App.wallet.walletUDF5">Wallet UDF 5</Translate>
              </span>
            </dt>
            <dd>{walletEntity.walletUDF5}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="vpay03App.wallet.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{walletEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="vpay03App.wallet.productType">Product Type</Translate>
            </dt>
            <dd>{walletEntity.productType ? walletEntity.productType.id : ''}</dd>
            <dt>
              <Translate contentKey="vpay03App.wallet.partner">Partner</Translate>
            </dt>
            <dd>{walletEntity.partner ? walletEntity.partner.id : ''}</dd>
            <dt>
              <Translate contentKey="vpay03App.wallet.payPartner">Pay Partner</Translate>
            </dt>
            <dd>{walletEntity.payPartner ? walletEntity.payPartner.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/wallet" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/wallet/${walletEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ wallet }: IRootState) => ({
  walletEntity: wallet.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(WalletDetail);
