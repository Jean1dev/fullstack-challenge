import React from "react";
import { CardProps } from "../common/types/CardProps";
import { formatDateToBrazilian } from "../common/utils/DataConverter";
import { useNavigate } from "react-router-dom";

const Card: React.FC<CardProps> = ({ filme, idProgramacao }) => {
  const navigate = useNavigate();
  return (
    <div className="card bg-base-100 w-96 shadow-xl bg-custom-gray">
      <figure>
        <img
          src={`https://media.themoviedb.org/t/p/original/${filme.posterPath}`}
          alt={filme.title}
        />
      </figure>
      <div className="card-body">
        <h2 className="card-title">{filme.title}</h2>
        <p>{formatDateToBrazilian(filme.releaseDate)} - Sala 1</p>
        <div className="card-actions justify-start">
          <button
            onClick={() => navigate(`/checkout/${idProgramacao}`)}
            className="btn btn-primary hover:text-gray-100 bg-custom-blue text-custom-dark-blue border-0"
          >
            Comprar ingresso
          </button>
          <button
            className="btn btn-outline text-white border-white"
            onClick={() => navigate(`/programacao-details/${idProgramacao}`)}
          >
            Ver mais
          </button>
        </div>
      </div>
    </div>
  );
};

export default Card;
