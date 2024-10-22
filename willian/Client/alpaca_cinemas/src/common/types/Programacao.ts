import { Filme } from "./Filme";
import { Horario } from "./Horario";
import { Sala } from "./Sala";

export interface Programacao {
  id: number;
  filme: Filme;
  sala: Sala;
  horarios: Horario[];
}
