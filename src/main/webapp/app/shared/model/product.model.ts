import { IProductType } from 'app/shared/model//product-type.model';

export interface IProduct {
  id?: number;
  productCode?: string;
  productName?: string;
  productValue?: number;
  productDesc?: string;
  productUDF1?: string;
  productUDF2?: string;
  productUDF3?: string;
  isDeleted?: boolean;
  productType?: IProductType;
}

export const defaultValue: Readonly<IProduct> = {
  isDeleted: false
};
