import { httpClient } from "../httpClient";

export async function getSalaPorId(id: number) {
  return await httpClient.get(`/v1/salas/${id}`);
}
export async function getCadeiras(id: number) {
  return await httpClient.get(`/v1/salas/${id}/cadeiras`);
}
