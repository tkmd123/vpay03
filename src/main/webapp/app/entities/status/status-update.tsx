import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './status.reducer';
import { IStatus } from 'app/shared/model/status.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IStatusUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IStatusUpdateState {
  isNew: boolean;
}

export class StatusUpdate extends React.Component<IStatusUpdateProps, IStatusUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { statusEntity } = this.props;
      const entity = {
        ...statusEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/status');
  };

  render() {
    const { statusEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="vpay03App.status.home.createOrEditLabel">
              <Translate contentKey="vpay03App.status.home.createOrEditLabel">Create or edit a Status</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : statusEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="status-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="statusCodeLabel" for="statusCode">
                    <Translate contentKey="vpay03App.status.statusCode">Status Code</Translate>
                  </Label>
                  <AvField
                    id="status-statusCode"
                    type="text"
                    name="statusCode"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="statusValueLabel" for="statusValue">
                    <Translate contentKey="vpay03App.status.statusValue">Status Value</Translate>
                  </Label>
                  <AvField id="status-statusValue" type="string" className="form-control" name="statusValue" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusNameLabel" for="statusName">
                    <Translate contentKey="vpay03App.status.statusName">Status Name</Translate>
                  </Label>
                  <AvField id="status-statusName" type="text" name="statusName" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusDescLabel" for="statusDesc">
                    <Translate contentKey="vpay03App.status.statusDesc">Status Desc</Translate>
                  </Label>
                  <AvField id="status-statusDesc" type="text" name="statusDesc" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusUDF1Label" for="statusUDF1">
                    <Translate contentKey="vpay03App.status.statusUDF1">Status UDF 1</Translate>
                  </Label>
                  <AvField id="status-statusUDF1" type="text" name="statusUDF1" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusUDF2Label" for="statusUDF2">
                    <Translate contentKey="vpay03App.status.statusUDF2">Status UDF 2</Translate>
                  </Label>
                  <AvField id="status-statusUDF2" type="text" name="statusUDF2" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusUDF3Label" for="statusUDF3">
                    <Translate contentKey="vpay03App.status.statusUDF3">Status UDF 3</Translate>
                  </Label>
                  <AvField id="status-statusUDF3" type="text" name="statusUDF3" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="status-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="vpay03App.status.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/status" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  statusEntity: storeState.status.entity,
  loading: storeState.status.loading,
  updating: storeState.status.updating,
  updateSuccess: storeState.status.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(StatusUpdate);
