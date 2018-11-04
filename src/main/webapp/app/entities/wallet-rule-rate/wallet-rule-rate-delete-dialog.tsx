import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IWalletRuleRate } from 'app/shared/model/wallet-rule-rate.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './wallet-rule-rate.reducer';

export interface IWalletRuleRateDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class WalletRuleRateDeleteDialog extends React.Component<IWalletRuleRateDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.walletRuleRateEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { walletRuleRateEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>
          <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
        </ModalHeader>
        <ModalBody id="vpay03App.walletRuleRate.delete.question">
          <Translate contentKey="vpay03App.walletRuleRate.delete.question" interpolate={{ id: walletRuleRateEntity.id }}>
            Are you sure you want to delete this WalletRuleRate?
          </Translate>
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />&nbsp;
            <Translate contentKey="entity.action.cancel">Cancel</Translate>
          </Button>
          <Button id="jhi-confirm-delete-walletRuleRate" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />&nbsp;
            <Translate contentKey="entity.action.delete">Delete</Translate>
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ walletRuleRate }: IRootState) => ({
  walletRuleRateEntity: walletRuleRate.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(WalletRuleRateDeleteDialog);
