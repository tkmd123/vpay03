import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './wallet-rule.reducer';
import { IWalletRule } from 'app/shared/model/wallet-rule.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IWalletRuleDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class WalletRuleDetail extends React.Component<IWalletRuleDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { walletRuleEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="vpay03App.walletRule.detail.title">WalletRule</Translate> [<b>{walletRuleEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="walletRuleCode">
                <Translate contentKey="vpay03App.walletRule.walletRuleCode">Wallet Rule Code</Translate>
              </span>
            </dt>
            <dd>{walletRuleEntity.walletRuleCode}</dd>
            <dt>
              <span id="walletRuleName">
                <Translate contentKey="vpay03App.walletRule.walletRuleName">Wallet Rule Name</Translate>
              </span>
            </dt>
            <dd>{walletRuleEntity.walletRuleName}</dd>
            <dt>
              <span id="walletRuleDesc">
                <Translate contentKey="vpay03App.walletRule.walletRuleDesc">Wallet Rule Desc</Translate>
              </span>
            </dt>
            <dd>{walletRuleEntity.walletRuleDesc}</dd>
            <dt>
              <span id="walletRuleFromDate">
                <Translate contentKey="vpay03App.walletRule.walletRuleFromDate">Wallet Rule From Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={walletRuleEntity.walletRuleFromDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="walletRuleToDate">
                <Translate contentKey="vpay03App.walletRule.walletRuleToDate">Wallet Rule To Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={walletRuleEntity.walletRuleToDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="walletRuleUDF1">
                <Translate contentKey="vpay03App.walletRule.walletRuleUDF1">Wallet Rule UDF 1</Translate>
              </span>
            </dt>
            <dd>{walletRuleEntity.walletRuleUDF1}</dd>
            <dt>
              <span id="walletRuleUDF2">
                <Translate contentKey="vpay03App.walletRule.walletRuleUDF2">Wallet Rule UDF 2</Translate>
              </span>
            </dt>
            <dd>{walletRuleEntity.walletRuleUDF2}</dd>
            <dt>
              <span id="walletRuleUDF3">
                <Translate contentKey="vpay03App.walletRule.walletRuleUDF3">Wallet Rule UDF 3</Translate>
              </span>
            </dt>
            <dd>{walletRuleEntity.walletRuleUDF3}</dd>
            <dt>
              <span id="walletRuleUDF4">
                <Translate contentKey="vpay03App.walletRule.walletRuleUDF4">Wallet Rule UDF 4</Translate>
              </span>
            </dt>
            <dd>{walletRuleEntity.walletRuleUDF4}</dd>
            <dt>
              <span id="walletRuleUDF5">
                <Translate contentKey="vpay03App.walletRule.walletRuleUDF5">Wallet Rule UDF 5</Translate>
              </span>
            </dt>
            <dd>{walletRuleEntity.walletRuleUDF5}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="vpay03App.walletRule.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{walletRuleEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="vpay03App.walletRule.productType">Product Type</Translate>
            </dt>
            <dd>{walletRuleEntity.productType ? walletRuleEntity.productType.id : ''}</dd>
            <dt>
              <Translate contentKey="vpay03App.walletRule.payPartner">Pay Partner</Translate>
            </dt>
            <dd>{walletRuleEntity.payPartner ? walletRuleEntity.payPartner.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/wallet-rule" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/wallet-rule/${walletRuleEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ walletRule }: IRootState) => ({
  walletRuleEntity: walletRule.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(WalletRuleDetail);
