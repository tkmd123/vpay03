import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './wallet-rule-rate.reducer';
import { IWalletRuleRate } from 'app/shared/model/wallet-rule-rate.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IWalletRuleRateDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class WalletRuleRateDetail extends React.Component<IWalletRuleRateDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { walletRuleRateEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="vpay03App.walletRuleRate.detail.title">WalletRuleRate</Translate> [<b>{walletRuleRateEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="walletRuleRateCode">
                <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateCode">Wallet Rule Rate Code</Translate>
              </span>
            </dt>
            <dd>{walletRuleRateEntity.walletRuleRateCode}</dd>
            <dt>
              <span id="walletRuleRateName">
                <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateName">Wallet Rule Rate Name</Translate>
              </span>
            </dt>
            <dd>{walletRuleRateEntity.walletRuleRateName}</dd>
            <dt>
              <span id="walletRuleRateDesc">
                <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateDesc">Wallet Rule Rate Desc</Translate>
              </span>
            </dt>
            <dd>{walletRuleRateEntity.walletRuleRateDesc}</dd>
            <dt>
              <span id="walletRuleRateFromValue">
                <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateFromValue">Wallet Rule Rate From Value</Translate>
              </span>
            </dt>
            <dd>{walletRuleRateEntity.walletRuleRateFromValue}</dd>
            <dt>
              <span id="walletRuleRateToValue">
                <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateToValue">Wallet Rule Rate To Value</Translate>
              </span>
            </dt>
            <dd>{walletRuleRateEntity.walletRuleRateToValue}</dd>
            <dt>
              <span id="walletRuleRateDiscount">
                <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateDiscount">Wallet Rule Rate Discount</Translate>
              </span>
            </dt>
            <dd>{walletRuleRateEntity.walletRuleRateDiscount}</dd>
            <dt>
              <span id="walletRuleRateUDF1">
                <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateUDF1">Wallet Rule Rate UDF 1</Translate>
              </span>
            </dt>
            <dd>{walletRuleRateEntity.walletRuleRateUDF1}</dd>
            <dt>
              <span id="walletRuleRateUDF2">
                <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateUDF2">Wallet Rule Rate UDF 2</Translate>
              </span>
            </dt>
            <dd>{walletRuleRateEntity.walletRuleRateUDF2}</dd>
            <dt>
              <span id="walletRuleRateUDF3">
                <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateUDF3">Wallet Rule Rate UDF 3</Translate>
              </span>
            </dt>
            <dd>{walletRuleRateEntity.walletRuleRateUDF3}</dd>
            <dt>
              <span id="walletRuleRateUDF4">
                <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateUDF4">Wallet Rule Rate UDF 4</Translate>
              </span>
            </dt>
            <dd>{walletRuleRateEntity.walletRuleRateUDF4}</dd>
            <dt>
              <span id="walletRuleRateUDF5">
                <Translate contentKey="vpay03App.walletRuleRate.walletRuleRateUDF5">Wallet Rule Rate UDF 5</Translate>
              </span>
            </dt>
            <dd>{walletRuleRateEntity.walletRuleRateUDF5}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="vpay03App.walletRuleRate.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{walletRuleRateEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="vpay03App.walletRuleRate.walletRule">Wallet Rule</Translate>
            </dt>
            <dd>{walletRuleRateEntity.walletRule ? walletRuleRateEntity.walletRule.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/wallet-rule-rate" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/wallet-rule-rate/${walletRuleRateEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ walletRuleRate }: IRootState) => ({
  walletRuleRateEntity: walletRuleRate.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(WalletRuleRateDetail);
