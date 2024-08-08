import { CriarIngressoState } from "../common/types/CriarIngressoState";

const INITIAL_STATE: CriarIngressoState = {
  ingresso: []
};

const CriarIngressoReducer = (
  state = INITIAL_STATE,
  action: any
): CriarIngressoState => {
  switch (action.type) {
    case "SET_INGRESSO":
      return {
        ...state,
        ...state,
        ingresso: state.ingresso
          ? [...state.ingresso, action.payload]
          : [action.payload]
      };
    case "CLEAR_INGRESSO":
      return {
        ingresso: []
      };
    case "REMOVE_COMBO_ITEM":
      return {
        ...state,
        ingresso: state.ingresso
          ? state.ingresso.map((ingresso) => ({
              ...ingresso,
              comboList: ingresso.comboList.filter(
                (item) => item !== action.payload
              )
            }))
          : null
      };
    default:
      return state;
  }
};

export default CriarIngressoReducer;
