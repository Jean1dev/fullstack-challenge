import { ProgramacaoState } from "../common/types/ProgramacaoState";

const initialState: ProgramacaoState = {
  programacao: [
    {
      id: 1,
      filme: {
        id: 1,
        title: "Filme 1",
        originalTitle: "Título Original 1",
        releaseDate: new Date(),
        posterPath: "uH6zs1QHQm9U0z4q2XP6wDkWd3N.jpg",
        overview: "Descrição do filme 1"
      },
      sala: {
        id: 1,
        numero: 1,
        cadeiras: []
      },
      horarios: [{ id: 1, horaInicio: "10:00", horaFim: "12:00" }]
    },
    {
      id: 2,
      filme: {
        id: 2,
        title: "Filme 2",
        originalTitle: "Título Original 2",
        releaseDate: new Date(),
        posterPath: "uH6zs1QHQm9U0z4q2XP6wDkWd3N.jpg",
        overview: "Descrição do filme 2"
      },
      sala: {
        id: 2,
        numero: 2,
        cadeiras: []
      },
      horarios: [{ id: 2, horaInicio: "12:00", horaFim: "14:00" }]
    },
    {
      id: 3,
      filme: {
        id: 3,
        title: "Filme 3",
        originalTitle: "Título Original 3",
        releaseDate: new Date(),
        posterPath: "uH6zs1QHQm9U0z4q2XP6wDkWd3N.jpg",
        overview: "Descrição do filme 3"
      },
      sala: {
        id: 3,
        numero: 3,
        cadeiras: []
      },
      horarios: [{ id: 3, horaInicio: "14:00", horaFim: "16:00" }]
    },
    {
      id: 4,
      filme: {
        id: 4,
        title: "Filme 4",
        originalTitle: "Título Original 4",
        releaseDate: new Date(),
        posterPath: "uH6zs1QHQm9U0z4q2XP6wDkWd3N.jpg",
        overview: "Descrição do filme 4"
      },
      sala: {
        id: 4,
        numero: 4,
        cadeiras: []
      },
      horarios: [{ id: 4, horaInicio: "16:00", horaFim: "18:00" }]
    }
  ]
};

const programacaoReducer = (
  state: ProgramacaoState = initialState,
  action: any
): ProgramacaoState => {
  switch (action.type) {
    case "SET_PROGRAMACAO":
      return {
        ...state,
        programacao: action.payload
      };
    case "CLEAR_PROGRAMACAO":
      return {
        ...state,
        programacao: null
      };
    default:
      return state;
  }
};

export default programacaoReducer;
