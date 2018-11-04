import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './wallet-transaction-type.reducer';
import { IWalletTransactionType } from 'app/shared/model/wallet-transaction-type.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IWalletTransactionTypeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class WalletTransactionTypeDetail extends React.Component<IWalletTransactionTypeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { walletTransactionTypeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="vpay03App.walletTransactionType.detail.title">WalletTransactionType</Translate> [<b>
              {walletTransactionTypeEntity.id}
            </b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="walletTransTypeCode">
                <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeCode">Wallet Trans Type Code</Translate>
              </span>
            </dt>
            <dd>{walletTransactionTypeEntity.walletTransTypeCode}</dd>
            <dt>
              <span id="walletTransTypeName">
                <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeName">Wallet Trans Type Name</Translate>
              </span>
            </dt>
            <dd>{walletTransactionTypeEntity.walletTransTypeName}</dd>
            <dt>
              <span id="walletTransTypeDesc">
                <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeDesc">Wallet Trans Type Desc</Translate>
              </span>
            </dt>
            <dd>{walletTransactionTypeEntity.walletTransTypeDesc}</dd>
            <dt>
              <span id="walletTransTypeFlag">
                <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeFlag">Wallet Trans Type Flag</Translate>
              </span>
            </dt>
            <dd>{walletTransactionTypeEntity.walletTransTypeFlag}</dd>
            <dt>
              <span id="walletTransTypeUDF1">
                <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeUDF1">Wallet Trans Type UDF 1</Translate>
              </span>
            </dt>
            <dd>{walletTransactionTypeEntity.walletTransTypeUDF1}</dd>
            <dt>
              <span id="walletTransTypeUDF2">
                <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeUDF2">Wallet Trans Type UDF 2</Translate>
              </span>
            </dt>
            <dd>{walletTransactionTypeEntity.walletTransTypeUDF2}</dd>
            <dt>
              <span id="walletTransTypeUDF3">
                <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeUDF3">Wallet Trans Type UDF 3</Translate>
              </span>
            </dt>
            <dd>{walletTransactionTypeEntity.walletTransTypeUDF3}</dd>
            <dt>
              <span id="walletTransTypeUDF4">
                <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeUDF4">Wallet Trans Type UDF 4</Translate>
              </span>
            </dt>
            <dd>{walletTransactionTypeEntity.walletTransTypeUDF4}</dd>
            <dt>
              <span id="walletTransTypeUDF5">
                <Translate contentKey="vpay03App.walletTransactionType.walletTransTypeUDF5">Wallet Trans Type UDF 5</Translate>
              </span>
            </dt>
            <dd>{walletTransactionTypeEntity.walletTransTypeUDF5}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="vpay03App.walletTransactionType.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{walletTransactionTypeEntity.isDeleted ? 'true' : 'false'}</dd>
          </dl>
          <Button tag={Link} to="/entity/wallet-transaction-type" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/wallet-transaction-type/${walletTransactionTypeEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ walletTransactionType }: IRootState) => ({
  walletTransactionTypeEntity: walletTransactionType.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(WalletTransactionTypeDetail);
