import { Moment } from 'moment';
import { IPartner } from 'app/shared/model//partner.model';

export interface IPartnerLog {
  id?: number;
  partnerLogAmount?: string;
  partnerLogTransRef?: string;
  partnerLogUsername?: string;
  partnerLogProductTypeCode?: string;
  partnerLogResultCode?: string;
  partnerLogResultDesc?: string;
  partnerLogDate?: Moment;
  partnerLogUDF1?: string;
  partnerLogUDF2?: string;
  partnerLogUDF3?: string;
  partnerLogUDF4?: string;
  partnerLogUDF5?: string;
  isDeleted?: boolean;
  partner?: IPartner;
}

export const defaultValue: Readonly<IPartnerLog> = {
  isDeleted: false
};
