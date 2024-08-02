import React from "react";
import { CardProps } from "../common/types/CardProps";
import { formatDateToBrazilian } from "../common/utils/DataConverter";

const Card: React.FC<CardProps> = ({ filme }) => {
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
          <button className="btn btn-primary hover:text-gray-100 bg-custom-blue text-custom-dark-blue border-0">
            Comprar ingresso
          </button>
          <button className="btn btn-outline text-white border-white">
            Ver mais
          </button>
        </div>
      </div>
    </div>
  );
};

export default Card;
