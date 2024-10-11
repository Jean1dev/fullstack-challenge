import { ErrorHandler } from "../common/types/ErrorHandler";
import api from "../libs/axios";

export const httpClient = {
  async get(url: string) {
    try {
      return await api
        .get(url)
        .then((response) => response)
        .then(({ data }) => data);
    } catch (error) {
      return handleError(error);
    }
  },

  async post(url: string, payload?: object): Promise<any> {
    try {
      const response = await api.post(url, payload);
      console.log("Resposta da API:", response.data);
      return response.data;
    } catch (error) {
      return handleError(error);
    }
  },

  async put(url: string, payload?: object): Promise<any> {
    try {
      const response = await api.put(url, payload);
      console.log("Resposta da API:", response.data);
      return response.data;
    } catch (error) {
      return handleError(error);
    }
  }
};

const handleError = (error: unknown): ErrorHandler => {
  if (error instanceof Error) {
    return {
      message: `Erro ao fazer a requisição: ${error.message}`,
      success: false
    };
  }
  return { message: "Erro desconhecido ao fazer a requisição", success: false };
};
