import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IWalletRuleRate, defaultValue } from 'app/shared/model/wallet-rule-rate.model';

export const ACTION_TYPES = {
  FETCH_WALLETRULERATE_LIST: 'walletRuleRate/FETCH_WALLETRULERATE_LIST',
  FETCH_WALLETRULERATE: 'walletRuleRate/FETCH_WALLETRULERATE',
  CREATE_WALLETRULERATE: 'walletRuleRate/CREATE_WALLETRULERATE',
  UPDATE_WALLETRULERATE: 'walletRuleRate/UPDATE_WALLETRULERATE',
  DELETE_WALLETRULERATE: 'walletRuleRate/DELETE_WALLETRULERATE',
  RESET: 'walletRuleRate/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IWalletRuleRate>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type WalletRuleRateState = Readonly<typeof initialState>;

// Reducer

export default (state: WalletRuleRateState = initialState, action): WalletRuleRateState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_WALLETRULERATE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_WALLETRULERATE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_WALLETRULERATE):
    case REQUEST(ACTION_TYPES.UPDATE_WALLETRULERATE):
    case REQUEST(ACTION_TYPES.DELETE_WALLETRULERATE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_WALLETRULERATE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_WALLETRULERATE):
    case FAILURE(ACTION_TYPES.CREATE_WALLETRULERATE):
    case FAILURE(ACTION_TYPES.UPDATE_WALLETRULERATE):
    case FAILURE(ACTION_TYPES.DELETE_WALLETRULERATE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_WALLETRULERATE_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_WALLETRULERATE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_WALLETRULERATE):
    case SUCCESS(ACTION_TYPES.UPDATE_WALLETRULERATE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_WALLETRULERATE):
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

const apiUrl = 'api/wallet-rule-rates';

// Actions

export const getEntities: ICrudGetAllAction<IWalletRuleRate> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_WALLETRULERATE_LIST,
    payload: axios.get<IWalletRuleRate>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IWalletRuleRate> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_WALLETRULERATE,
    payload: axios.get<IWalletRuleRate>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IWalletRuleRate> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_WALLETRULERATE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IWalletRuleRate> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_WALLETRULERATE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IWalletRuleRate> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_WALLETRULERATE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
