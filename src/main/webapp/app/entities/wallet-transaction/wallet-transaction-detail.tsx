import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './wallet-transaction.reducer';
import { IWalletTransaction } from 'app/shared/model/wallet-transaction.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IWalletTransactionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class WalletTransactionDetail extends React.Component<IWalletTransactionDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { walletTransactionEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="vpay03App.walletTransaction.detail.title">WalletTransaction</Translate> [<b>
              {walletTransactionEntity.id}
            </b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="walletTransAmount">
                <Translate contentKey="vpay03App.walletTransaction.walletTransAmount">Wallet Trans Amount</Translate>
              </span>
            </dt>
            <dd>{walletTransactionEntity.walletTransAmount}</dd>
            <dt>
              <span id="walletTransDate">
                <Translate contentKey="vpay03App.walletTransaction.walletTransDate">Wallet Trans Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={walletTransactionEntity.walletTransDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="walletTransRef">
                <Translate contentKey="vpay03App.walletTransaction.walletTransRef">Wallet Trans Ref</Translate>
              </span>
            </dt>
            <dd>{walletTransactionEntity.walletTransRef}</dd>
            <dt>
              <span id="walletTransUsername">
                <Translate contentKey="vpay03App.walletTransaction.walletTransUsername">Wallet Trans Username</Translate>
              </span>
            </dt>
            <dd>{walletTransactionEntity.walletTransUsername}</dd>
            <dt>
              <span id="walletTransStatus">
                <Translate contentKey="vpay03App.walletTransaction.walletTransStatus">Wallet Trans Status</Translate>
              </span>
            </dt>
            <dd>{walletTransactionEntity.walletTransStatus}</dd>
            <dt>
              <span id="walletTransUDF1">
                <Translate contentKey="vpay03App.walletTransaction.walletTransUDF1">Wallet Trans UDF 1</Translate>
              </span>
            </dt>
            <dd>{walletTransactionEntity.walletTransUDF1}</dd>
            <dt>
              <span id="walletTransUDF2">
                <Translate contentKey="vpay03App.walletTransaction.walletTransUDF2">Wallet Trans UDF 2</Translate>
              </span>
            </dt>
            <dd>{walletTransactionEntity.walletTransUDF2}</dd>
            <dt>
              <span id="walletTransUDF3">
                <Translate contentKey="vpay03App.walletTransaction.walletTransUDF3">Wallet Trans UDF 3</Translate>
              </span>
            </dt>
            <dd>{walletTransactionEntity.walletTransUDF3}</dd>
            <dt>
              <span id="walletTransUDF4">
                <Translate contentKey="vpay03App.walletTransaction.walletTransUDF4">Wallet Trans UDF 4</Translate>
              </span>
            </dt>
            <dd>{walletTransactionEntity.walletTransUDF4}</dd>
            <dt>
              <span id="walletTransUDF5">
                <Translate contentKey="vpay03App.walletTransaction.walletTransUDF5">Wallet Trans UDF 5</Translate>
              </span>
            </dt>
            <dd>{walletTransactionEntity.walletTransUDF5}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="vpay03App.walletTransaction.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{walletTransactionEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="vpay03App.walletTransaction.walletTransactionType">Wallet Transaction Type</Translate>
            </dt>
            <dd>{walletTransactionEntity.walletTransactionType ? walletTransactionEntity.walletTransactionType.id : ''}</dd>
            <dt>
              <Translate contentKey="vpay03App.walletTransaction.wallet">Wallet</Translate>
            </dt>
            <dd>{walletTransactionEntity.wallet ? walletTransactionEntity.wallet.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/wallet-transaction" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/wallet-transaction/${walletTransactionEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ walletTransaction }: IRootState) => ({
  walletTransactionEntity: walletTransaction.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(WalletTransactionDetail);
