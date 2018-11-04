import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IProductTypeWallet, defaultValue } from 'app/shared/model/product-type-wallet.model';

export const ACTION_TYPES = {
  FETCH_PRODUCTTYPEWALLET_LIST: 'productTypeWallet/FETCH_PRODUCTTYPEWALLET_LIST',
  FETCH_PRODUCTTYPEWALLET: 'productTypeWallet/FETCH_PRODUCTTYPEWALLET',
  CREATE_PRODUCTTYPEWALLET: 'productTypeWallet/CREATE_PRODUCTTYPEWALLET',
  UPDATE_PRODUCTTYPEWALLET: 'productTypeWallet/UPDATE_PRODUCTTYPEWALLET',
  DELETE_PRODUCTTYPEWALLET: 'productTypeWallet/DELETE_PRODUCTTYPEWALLET',
  RESET: 'productTypeWallet/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IProductTypeWallet>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ProductTypeWalletState = Readonly<typeof initialState>;

// Reducer

export default (state: ProductTypeWalletState = initialState, action): ProductTypeWalletState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PRODUCTTYPEWALLET_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PRODUCTTYPEWALLET):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PRODUCTTYPEWALLET):
    case REQUEST(ACTION_TYPES.UPDATE_PRODUCTTYPEWALLET):
    case REQUEST(ACTION_TYPES.DELETE_PRODUCTTYPEWALLET):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PRODUCTTYPEWALLET_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PRODUCTTYPEWALLET):
    case FAILURE(ACTION_TYPES.CREATE_PRODUCTTYPEWALLET):
    case FAILURE(ACTION_TYPES.UPDATE_PRODUCTTYPEWALLET):
    case FAILURE(ACTION_TYPES.DELETE_PRODUCTTYPEWALLET):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PRODUCTTYPEWALLET_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PRODUCTTYPEWALLET):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PRODUCTTYPEWALLET):
    case SUCCESS(ACTION_TYPES.UPDATE_PRODUCTTYPEWALLET):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PRODUCTTYPEWALLET):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/product-type-wallets';

// Actions

export const getEntities: ICrudGetAllAction<IProductTypeWallet> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PRODUCTTYPEWALLET_LIST,
  payload: axios.get<IProductTypeWallet>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IProductTypeWallet> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PRODUCTTYPEWALLET,
    payload: axios.get<IProductTypeWallet>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IProductTypeWallet> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PRODUCTTYPEWALLET,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IProductTypeWallet> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PRODUCTTYPEWALLET,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IProductTypeWallet> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PRODUCTTYPEWALLET,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
