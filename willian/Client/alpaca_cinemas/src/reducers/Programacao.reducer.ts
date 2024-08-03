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
        overview:
          "Nesta aguardada sequência de X - A Marca da Morte (2022) e Pearl (2022), Mia Goth reprisa seu papel como a determinada aspirante a atriz, Maxine Minx. Como única sobrevivente de uma filmagem pornô que deu errado há alguns anos, ela decide seguir sua jornada rumo à fama mesmo depois do acontecido. Agora, na década de 1980, em Hollywood, a estrela de cinema adulto começa a fazer testes para papéis no cinema e consegue uma vaga em uma sequência de terror de baixo orçamento, agarrando a oportunidade com todas as suas forças. Mas enquanto isso, Maxine se torna alvo da investigação de um detetive particular e um misterioso assassino, conhecido como Night Stalker, persegue as estrelas de Hollywood, deixando um rastro de sangue que ameaça revelar o passado sinistro de Maxine. Porém, ela não pretende deixar que esses problemas impactem sua bela carreira."
      },
      sala: {
        id: 1,
        numero: 1,
        cadeiras: []
      },
      horarios: [
        { id: 1, horaInicio: "10:00", horaFim: "12:00" },
        { id: 1, horaInicio: "10:00", horaFim: "12:00" },
        { id: 1, horaInicio: "10:00", horaFim: "12:00" },
        { id: 1, horaInicio: "10:00", horaFim: "12:00" }
      ]
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
