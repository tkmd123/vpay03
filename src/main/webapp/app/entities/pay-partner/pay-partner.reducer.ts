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

import { IPayPartner, defaultValue } from 'app/shared/model/pay-partner.model';

export const ACTION_TYPES = {
  FETCH_PAYPARTNER_LIST: 'payPartner/FETCH_PAYPARTNER_LIST',
  FETCH_PAYPARTNER: 'payPartner/FETCH_PAYPARTNER',
  CREATE_PAYPARTNER: 'payPartner/CREATE_PAYPARTNER',
  UPDATE_PAYPARTNER: 'payPartner/UPDATE_PAYPARTNER',
  DELETE_PAYPARTNER: 'payPartner/DELETE_PAYPARTNER',
  RESET: 'payPartner/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPayPartner>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type PayPartnerState = Readonly<typeof initialState>;

// Reducer

export default (state: PayPartnerState = initialState, action): PayPartnerState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PAYPARTNER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PAYPARTNER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PAYPARTNER):
    case REQUEST(ACTION_TYPES.UPDATE_PAYPARTNER):
    case REQUEST(ACTION_TYPES.DELETE_PAYPARTNER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PAYPARTNER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PAYPARTNER):
    case FAILURE(ACTION_TYPES.CREATE_PAYPARTNER):
    case FAILURE(ACTION_TYPES.UPDATE_PAYPARTNER):
    case FAILURE(ACTION_TYPES.DELETE_PAYPARTNER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PAYPARTNER_LIST):
      const links = parseHeaderForLinks(action.payload.headers.link);
      return {
        ...state,
        links,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links)
      };
    case SUCCESS(ACTION_TYPES.FETCH_PAYPARTNER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PAYPARTNER):
    case SUCCESS(ACTION_TYPES.UPDATE_PAYPARTNER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PAYPARTNER):
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

const apiUrl = 'api/pay-partners';

// Actions

export const getEntities: ICrudGetAllAction<IPayPartner> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PAYPARTNER_LIST,
    payload: axios.get<IPayPartner>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IPayPartner> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PAYPARTNER,
    payload: axios.get<IPayPartner>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPayPartner> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PAYPARTNER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const updateEntity: ICrudPutAction<IPayPartner> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PAYPARTNER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPayPartner> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PAYPARTNER,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
