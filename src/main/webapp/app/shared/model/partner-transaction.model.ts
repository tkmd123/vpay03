import { Moment } from 'moment';
import { IProductType } from 'app/shared/model//product-type.model';
import { IPartner } from 'app/shared/model//partner.model';
import { IWalletTransaction } from 'app/shared/model//wallet-transaction.model';

export interface IPartnerTransaction {
  id?: number;
  partnerTransAmount?: number;
  partnerTransDate?: Moment;
  partnerTransRef?: string;
  partnerTransUsername?: string;
  partnerTransStatus?: string;
  partnerTransUDF1?: string;
  partnerTransUDF2?: string;
  partnerTransUDF3?: string;
  partnerTransUDF4?: string;
  partnerTransUDF5?: string;
  isDeleted?: boolean;
  productType?: IProductType;
  partner?: IPartner;
  walletTransaction?: IWalletTransaction;
}

export const defaultValue: Readonly<IPartnerTransaction> = {
  isDeleted: false
};
