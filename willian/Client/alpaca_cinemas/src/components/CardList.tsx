import React from "react";
import { useSelector } from "react-redux";
import { RootState } from "../store/store";
import Card from "./Card";

const CardList = () => {
  const programacoes = useSelector(
    (state: RootState) => state.programacao.programacao
  );

  return (
    <div className="flex flex-wrap justify-center gap-4">
      {programacoes!.map((programacao) => (
        <Card key={programacao.id} filme={programacao.filme} />
      ))}
    </div>
  );
};

export default CardList;
