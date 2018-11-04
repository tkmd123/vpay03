import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './partner-transaction.reducer';
import { IPartnerTransaction } from 'app/shared/model/partner-transaction.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPartnerTransactionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PartnerTransactionDetail extends React.Component<IPartnerTransactionDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { partnerTransactionEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="vpay03App.partnerTransaction.detail.title">PartnerTransaction</Translate> [<b>
              {partnerTransactionEntity.id}
            </b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="partnerTransAmount">
                <Translate contentKey="vpay03App.partnerTransaction.partnerTransAmount">Partner Trans Amount</Translate>
              </span>
            </dt>
            <dd>{partnerTransactionEntity.partnerTransAmount}</dd>
            <dt>
              <span id="partnerTransDate">
                <Translate contentKey="vpay03App.partnerTransaction.partnerTransDate">Partner Trans Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={partnerTransactionEntity.partnerTransDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="partnerTransRef">
                <Translate contentKey="vpay03App.partnerTransaction.partnerTransRef">Partner Trans Ref</Translate>
              </span>
            </dt>
            <dd>{partnerTransactionEntity.partnerTransRef}</dd>
            <dt>
              <span id="partnerTransUsername">
                <Translate contentKey="vpay03App.partnerTransaction.partnerTransUsername">Partner Trans Username</Translate>
              </span>
            </dt>
            <dd>{partnerTransactionEntity.partnerTransUsername}</dd>
            <dt>
              <span id="partnerTransStatus">
                <Translate contentKey="vpay03App.partnerTransaction.partnerTransStatus">Partner Trans Status</Translate>
              </span>
            </dt>
            <dd>{partnerTransactionEntity.partnerTransStatus}</dd>
            <dt>
              <span id="partnerTransUDF1">
                <Translate contentKey="vpay03App.partnerTransaction.partnerTransUDF1">Partner Trans UDF 1</Translate>
              </span>
            </dt>
            <dd>{partnerTransactionEntity.partnerTransUDF1}</dd>
            <dt>
              <span id="partnerTransUDF2">
                <Translate contentKey="vpay03App.partnerTransaction.partnerTransUDF2">Partner Trans UDF 2</Translate>
              </span>
            </dt>
            <dd>{partnerTransactionEntity.partnerTransUDF2}</dd>
            <dt>
              <span id="partnerTransUDF3">
                <Translate contentKey="vpay03App.partnerTransaction.partnerTransUDF3">Partner Trans UDF 3</Translate>
              </span>
            </dt>
            <dd>{partnerTransactionEntity.partnerTransUDF3}</dd>
            <dt>
              <span id="partnerTransUDF4">
                <Translate contentKey="vpay03App.partnerTransaction.partnerTransUDF4">Partner Trans UDF 4</Translate>
              </span>
            </dt>
            <dd>{partnerTransactionEntity.partnerTransUDF4}</dd>
            <dt>
              <span id="partnerTransUDF5">
                <Translate contentKey="vpay03App.partnerTransaction.partnerTransUDF5">Partner Trans UDF 5</Translate>
              </span>
            </dt>
            <dd>{partnerTransactionEntity.partnerTransUDF5}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="vpay03App.partnerTransaction.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{partnerTransactionEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="vpay03App.partnerTransaction.productType">Product Type</Translate>
            </dt>
            <dd>{partnerTransactionEntity.productType ? partnerTransactionEntity.productType.id : ''}</dd>
            <dt>
              <Translate contentKey="vpay03App.partnerTransaction.partner">Partner</Translate>
            </dt>
            <dd>{partnerTransactionEntity.partner ? partnerTransactionEntity.partner.id : ''}</dd>
            <dt>
              <Translate contentKey="vpay03App.partnerTransaction.walletTransaction">Wallet Transaction</Translate>
            </dt>
            <dd>{partnerTransactionEntity.walletTransaction ? partnerTransactionEntity.walletTransaction.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/partner-transaction" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/partner-transaction/${partnerTransactionEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ partnerTransaction }: IRootState) => ({
  partnerTransactionEntity: partnerTransaction.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PartnerTransactionDetail);
