import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { RootState } from "../store/store";
import { formatDateToBrazilian } from "../common/utils/DataConverter";
import { Programacao } from "../common/types/Programacao";
import { useNavigate, useParams } from "react-router-dom";

const ProgramacaoDetail = () => {
  const [programacao, setProgramacao] = useState<Programacao>();
  const { id } = useParams<string>();
  const navigate = useNavigate();

  const programacoes = useSelector(
    (state: RootState) => state.programacao.programacao
  );
  const fetchProgramacao = (id: number): void => {
    setProgramacao(programacoes!.find((programacao) => programacao.id === id));
  };

  useEffect(() => {
    fetchProgramacao(Number(id));
  }, []);

  return (
    <div className="hero bg-custom-dark-blue min-h-screen">
      <div className="hero-content flex-col lg:flex-row">
        {programacao ? (
          <>
            <img
              src={`https://media.themoviedb.org/t/p/original/${
                programacao!.filme.posterPath
              }`}
              className="max-w-sm rounded-lg shadow-2xl"
            />
            <div>
              <h1 className="text-5xl font-bold">{programacao!.filme.title}</h1>
              <p>
                Lançamento{" "}
                {formatDateToBrazilian(programacao!.filme.releaseDate)}
              </p>
              <div className="my-2">
                <h4 className="text-[24px] font-bold">Sinopse</h4>
                <p className="">{programacao!.filme.overview}</p>
              </div>
              <div className="my-2">
                <h4 className="text-[24px] font-bold">
                  Em exibição na sala {programacao!.sala.numero}
                </h4>
              </div>
              <div className="my-2">
                <h4 className="text-[24px] font-bold">Horários:</h4>
                {programacao!.horarios.map((horario) => (
                  <div className="mx-1 badge badge-accent bg-custom-gray border-0">
                    {horario.horaInicio}
                  </div>
                ))}
              </div>

              <button
                onClick={() => navigate(`/checkout/${id}`)}
                className="btn btn-primary hover:text-gray-100 bg-custom-blue text-custom-dark-blue border-0"
              >
                Comprar ingresso
              </button>
            </div>
          </>
        ) : (
          <p>Nenhuma programação encontrada</p>
        )}
      </div>
    </div>
  );
};

export default ProgramacaoDetail;
