import { httpClient } from "../httpClient";

export async function getProgramacao() {
  return await httpClient.get("/v1/programacao");
}

export async function getProgramacaoById(id: string) {
  return await httpClient.get(`/v1/programacao/${id}`);
}
