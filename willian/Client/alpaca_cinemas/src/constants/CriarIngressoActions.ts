import { CriarIngresso } from "../common/types/CriarIngresso";

export type CriarIngressoAction =
  | { type: "SET_INGRESSO"; payload: CriarIngresso }
  | { type: "CLEAR_INGRESSO" }
  | { type: "REMOVE_COMBO_ITEM"; payload: number}
