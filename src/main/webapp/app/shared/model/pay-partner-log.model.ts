import { Moment } from 'moment';
import { IPayPartner } from 'app/shared/model//pay-partner.model';

export interface IPayPartnerLog {
  id?: number;
  payLogAmount?: string;
  payLogTransRef?: string;
  payLogUsername?: string;
  payLogProductTypeCode?: string;
  payLogResuleCode?: string;
  payLogResuleDesc?: string;
  payLogDate?: Moment;
  payLogUDF1?: string;
  payLogUDF2?: string;
  payLogUDF3?: string;
  payLogUDF4?: string;
  payLogUDF5?: string;
  isDeleted?: boolean;
  payPartner?: IPayPartner;
}

export const defaultValue: Readonly<IPayPartnerLog> = {
  isDeleted: false
};
