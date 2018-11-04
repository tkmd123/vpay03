import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './status.reducer';
import { IStatus } from 'app/shared/model/status.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStatusDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class StatusDetail extends React.Component<IStatusDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { statusEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="vpay03App.status.detail.title">Status</Translate> [<b>{statusEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="statusCode">
                <Translate contentKey="vpay03App.status.statusCode">Status Code</Translate>
              </span>
            </dt>
            <dd>{statusEntity.statusCode}</dd>
            <dt>
              <span id="statusValue">
                <Translate contentKey="vpay03App.status.statusValue">Status Value</Translate>
              </span>
            </dt>
            <dd>{statusEntity.statusValue}</dd>
            <dt>
              <span id="statusName">
                <Translate contentKey="vpay03App.status.statusName">Status Name</Translate>
              </span>
            </dt>
            <dd>{statusEntity.statusName}</dd>
            <dt>
              <span id="statusDesc">
                <Translate contentKey="vpay03App.status.statusDesc">Status Desc</Translate>
              </span>
            </dt>
            <dd>{statusEntity.statusDesc}</dd>
            <dt>
              <span id="statusUDF1">
                <Translate contentKey="vpay03App.status.statusUDF1">Status UDF 1</Translate>
              </span>
            </dt>
            <dd>{statusEntity.statusUDF1}</dd>
            <dt>
              <span id="statusUDF2">
                <Translate contentKey="vpay03App.status.statusUDF2">Status UDF 2</Translate>
              </span>
            </dt>
            <dd>{statusEntity.statusUDF2}</dd>
            <dt>
              <span id="statusUDF3">
                <Translate contentKey="vpay03App.status.statusUDF3">Status UDF 3</Translate>
              </span>
            </dt>
            <dd>{statusEntity.statusUDF3}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="vpay03App.status.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{statusEntity.isDeleted ? 'true' : 'false'}</dd>
          </dl>
          <Button tag={Link} to="/entity/status" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/status/${statusEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ status }: IRootState) => ({
  statusEntity: status.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(StatusDetail);
