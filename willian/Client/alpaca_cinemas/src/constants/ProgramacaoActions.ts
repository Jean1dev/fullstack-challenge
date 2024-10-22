import { Programacao } from "../common/types/Programacao";

export type ProgramacaoAction =
  | { type: "SET_PROGRAMACAO"; payload: Programacao }
  | { type: "CLEAR_PROGRAMACAO" };
