import React from 'react';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from '../header-components';

export const EntitiesMenu = props => (
  // tslint:disable-next-line:jsx-self-close
  <NavDropdown icon="th-list" name={translate('global.menu.entities.main')} id="entity-menu">
    <DropdownItem tag={Link} to="/entity/product-type">
      <FontAwesomeIcon icon="asterisk" />&nbsp;<Translate contentKey="global.menu.entities.productType" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/product">
      <FontAwesomeIcon icon="asterisk" />&nbsp;<Translate contentKey="global.menu.entities.product" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/product-type-wallet">
      <FontAwesomeIcon icon="asterisk" />&nbsp;<Translate contentKey="global.menu.entities.productTypeWallet" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/partner">
      <FontAwesomeIcon icon="asterisk" />&nbsp;<Translate contentKey="global.menu.entities.partner" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/pay-partner">
      <FontAwesomeIcon icon="asterisk" />&nbsp;<Translate contentKey="global.menu.entities.payPartner" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/wallet-transaction-type">
      <FontAwesomeIcon icon="asterisk" />&nbsp;<Translate contentKey="global.menu.entities.walletTransactionType" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/wallet">
      <FontAwesomeIcon icon="asterisk" />&nbsp;<Translate contentKey="global.menu.entities.wallet" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/wallet-rule">
      <FontAwesomeIcon icon="asterisk" />&nbsp;<Translate contentKey="global.menu.entities.walletRule" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/wallet-rule-rate">
      <FontAwesomeIcon icon="asterisk" />&nbsp;<Translate contentKey="global.menu.entities.walletRuleRate" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/wallet-transaction">
      <FontAwesomeIcon icon="asterisk" />&nbsp;<Translate contentKey="global.menu.entities.walletTransaction" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/partner-transaction">
      <FontAwesomeIcon icon="asterisk" />&nbsp;<Translate contentKey="global.menu.entities.partnerTransaction" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/pay-partner-log">
      <FontAwesomeIcon icon="asterisk" />&nbsp;<Translate contentKey="global.menu.entities.payPartnerLog" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/partner-log">
      <FontAwesomeIcon icon="asterisk" />&nbsp;<Translate contentKey="global.menu.entities.partnerLog" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/status">
      <FontAwesomeIcon icon="asterisk" />&nbsp;<Translate contentKey="global.menu.entities.status" />
    </DropdownItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
