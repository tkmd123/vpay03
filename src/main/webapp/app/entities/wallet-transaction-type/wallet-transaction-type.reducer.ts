import axios from 'axios';
import {
  parseHeaderForLinks,
  loadMoreDataWhenScrolled,
  ICrudGetAction,
  ICrudGetAllAction,
  ICrudPutAction,
  ICrudDeleteAction
} from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IWalletTransactionType, defaultValue } from 'app/shared/model/wallet-transaction-type.model';

export const ACTION_TYPES = {
  FETCH_WALLETTRANSACTIONTYPE_LIST: 'walletTransactionType/FETCH_WALLETTRANSACTIONTYPE_LIST',
  FETCH_WALLETTRANSACTIONTYPE: 'walletTransactionType/FETCH_WALLETTRANSACTIONTYPE',
  CREATE_WALLETTRANSACTIONTYPE: 'walletTransactionType/CREATE_WALLETTRANSACTIONTYPE',
  UPDATE_WALLETTRANSACTIONTYPE: 'walletTransactionType/UPDATE_WALLETTRANSACTIONTYPE',
  DELETE_WALLETTRANSACTIONTYPE: 'walletTransactionType/DELETE_WALLETTRANSACTIONTYPE',
  RESET: 'walletTransactionType/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IWalletTransactionType>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type WalletTransactionTypeState = Readonly<typeof initialState>;

// Reducer

export default (state: WalletTransactionTypeState = initialState, action): WalletTransactionTypeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_WALLETTRANSACTIONTYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_WALLETTRANSACTIONTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_WALLETTRANSACTIONTYPE):
    case REQUEST(ACTION_TYPES.UPDATE_WALLETTRANSACTIONTYPE):
    case REQUEST(ACTION_TYPES.DELETE_WALLETTRANSACTIONTYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_WALLETTRANSACTIONTYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_WALLETTRANSACTIONTYPE):
    case FAILURE(ACTION_TYPES.CREATE_WALLETTRANSACTIONTYPE):
    case FAILURE(ACTION_TYPES.UPDATE_WALLETTRANSACTIONTYPE):
    case FAILURE(ACTION_TYPES.DELETE_WALLETTRANSACTIONTYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_WALLETTRANSACTIONTYPE_LIST):
      const links = parseHeaderForLinks(action.payload.headers.link);
      return {
        ...state,
        links,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links)
      };
    case SUCCESS(ACTION_TYPES.FETCH_WALLETTRANSACTIONTYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_WALLETTRANSACTIONTYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_WALLETTRANSACTIONTYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_WALLETTRANSACTIONTYPE):
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

const apiUrl = 'api/wallet-transaction-types';

// Actions

export const getEntities: ICrudGetAllAction<IWalletTransactionType> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_WALLETTRANSACTIONTYPE_LIST,
    payload: axios.get<IWalletTransactionType>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IWalletTransactionType> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_WALLETTRANSACTIONTYPE,
    payload: axios.get<IWalletTransactionType>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IWalletTransactionType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_WALLETTRANSACTIONTYPE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const updateEntity: ICrudPutAction<IWalletTransactionType> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_WALLETTRANSACTIONTYPE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IWalletTransactionType> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_WALLETTRANSACTIONTYPE,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
