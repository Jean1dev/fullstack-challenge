import React, { useEffect, useState } from "react";
import { Programacao } from "../common/types/Programacao";
import { useNavigate, useParams } from "react-router-dom";
import { getProgramacaoById } from "../services/Filmes/Request";

const ProgramacaoDetail = () => {
  const [programacao, setProgramacao] = useState<Programacao | null>();
  const { id } = useParams<string>();
  const navigate = useNavigate();

  const fetchProgramacao = async (id: string) => {
    const data = await getProgramacaoById(id);
    if (data.message) {
      setProgramacao(null);
      return;
    }

    setProgramacao(data);
  };

  useEffect(() => {
    id && fetchProgramacao(id);
  }, [id]);

  return (
    <div className="hero bg-custom-dark-blue min-h-screen">
      <div className="hero-content flex-col lg:flex-row">
        {programacao ? (
          <>
            <img
              src={`https://media.themoviedb.org/t/p/original/${programacao.filme.poster_path}`}
              alt={programacao.filme.title}
              className="max-w-sm rounded-lg shadow-2xl"
            />

            <div>
              <h1 className="text-5xl font-bold">{programacao.filme.title}</h1>

              <p className="pt-4">
                Lançamento {programacao.filme.release_date}
              </p>

              <div className="my-2">
                <h4 className="text-[24px] font-bold">Sinopse</h4>

                <p className="">{programacao.filme.overview}</p>
              </div>

              <div className="my-2">
                <h4 className="text-[24px] font-bold">
                  Em exibição na sala {programacao.sala.numero}
                </h4>
              </div>

              <div className="my-2">
                <h4 className="text-[24px] font-bold">Horários:</h4>

                {programacao.horarios.map((horario) => (
                  <div
                    key={horario.id}
                    className="mx-1 badge badge-accent bg-custom-gray border-0"
                  >
                    <p className="text-white">{horario.horaInicio}</p>
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
