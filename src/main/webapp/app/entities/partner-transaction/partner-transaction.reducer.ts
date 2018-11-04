import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPartnerTransaction, defaultValue } from 'app/shared/model/partner-transaction.model';

export const ACTION_TYPES = {
  FETCH_PARTNERTRANSACTION_LIST: 'partnerTransaction/FETCH_PARTNERTRANSACTION_LIST',
  FETCH_PARTNERTRANSACTION: 'partnerTransaction/FETCH_PARTNERTRANSACTION',
  CREATE_PARTNERTRANSACTION: 'partnerTransaction/CREATE_PARTNERTRANSACTION',
  UPDATE_PARTNERTRANSACTION: 'partnerTransaction/UPDATE_PARTNERTRANSACTION',
  DELETE_PARTNERTRANSACTION: 'partnerTransaction/DELETE_PARTNERTRANSACTION',
  RESET: 'partnerTransaction/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPartnerTransaction>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type PartnerTransactionState = Readonly<typeof initialState>;

// Reducer

export default (state: PartnerTransactionState = initialState, action): PartnerTransactionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PARTNERTRANSACTION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PARTNERTRANSACTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PARTNERTRANSACTION):
    case REQUEST(ACTION_TYPES.UPDATE_PARTNERTRANSACTION):
    case REQUEST(ACTION_TYPES.DELETE_PARTNERTRANSACTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PARTNERTRANSACTION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PARTNERTRANSACTION):
    case FAILURE(ACTION_TYPES.CREATE_PARTNERTRANSACTION):
    case FAILURE(ACTION_TYPES.UPDATE_PARTNERTRANSACTION):
    case FAILURE(ACTION_TYPES.DELETE_PARTNERTRANSACTION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PARTNERTRANSACTION_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PARTNERTRANSACTION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PARTNERTRANSACTION):
    case SUCCESS(ACTION_TYPES.UPDATE_PARTNERTRANSACTION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PARTNERTRANSACTION):
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

const apiUrl = 'api/partner-transactions';

// Actions

export const getEntities: ICrudGetAllAction<IPartnerTransaction> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PARTNERTRANSACTION_LIST,
    payload: axios.get<IPartnerTransaction>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IPartnerTransaction> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PARTNERTRANSACTION,
    payload: axios.get<IPartnerTransaction>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPartnerTransaction> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PARTNERTRANSACTION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPartnerTransaction> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PARTNERTRANSACTION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPartnerTransaction> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PARTNERTRANSACTION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
