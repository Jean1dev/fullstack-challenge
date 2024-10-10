import api from "../libs/axios";

export const httpClient = {
  async get(url: string) {
    try {
      return await api
        .get(url)
        .then((response) => response)
        .then(({ data }) => data);
    } catch (error) {
      console.log("Erro na requisição:", error);
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

function handleError(error: unknown): never {
  if (error instanceof Error) {
    console.error("API Error:", error.message);
    throw new Error(`Erro ao fazer a requisição: ${error.message}`);
  }
  throw new Error("Erro desconhecido ao fazer a requisição");
}
