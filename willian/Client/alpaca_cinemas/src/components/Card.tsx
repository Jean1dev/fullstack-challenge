import { Filme } from "common/types/Filme";
import { Programacao } from "common/types/Programacao";
import React from "react";
import { useNavigate } from "react-router-dom";

interface CardProps {
  filme: Filme;
  programacao: Programacao;
}

const Card: React.FC<CardProps> = ({ filme, programacao }) => {
  const navigate = useNavigate();
  console.log(filme);
  return (
    <div className="card bg-base-100 w-96 shadow-xl bg-custom-gray">
      <figure>
        <img
          src={`https://media.themoviedb.org/t/p/original/${filme.poster_path}`}
          alt={filme.title}
        />
      </figure>
      <div className="card-body">
        <h2 className="card-title">{filme.title}</h2>
        <p>
          {filme.release_date} - sala {programacao.sala.numero}
        </p>
        <div className="card-actions justify-start">
          <button
            onClick={() => navigate(`/checkout/${programacao.id}`)}
            className="btn btn-primary hover:text-gray-100 bg-custom-blue text-custom-dark-blue border-0"
          >
            Comprar ingresso
          </button>
          <button
            className="btn btn-outline text-white border-white"
            onClick={() => navigate(`/programacao-details/${programacao.id}`)}
          >
            Ver mais
          </button>
        </div>
      </div>
    </div>
  );
};

export default Card;
