import { httpClient } from "../httpClient";

export async function getTipoIngresso() {
  return await httpClient.get("v1/tipo-ingresso");
}
