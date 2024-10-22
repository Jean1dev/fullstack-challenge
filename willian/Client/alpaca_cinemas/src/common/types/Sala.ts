import { Cadeira } from "./Cadeira";

export interface Sala {
  id: number;
  numero: number;
  cadeiras: Cadeira[];
}
