import React, { useEffect, useState } from "react";
import Card from "./Card";

import { Programacao } from "../common/types/Programacao";
import { getProgramacao } from "../services/Filmes/Request";

const CardList = () => {
  const [programacoes, setProgramacoes] = useState<Programacao[]>();

  const fetchFilmes = async () => {
    const programacao = await getProgramacao();
    setProgramacoes(programacao);
  };

  useEffect(() => {
    fetchFilmes();
  }, []);

  return (
    <div className="flex flex-wrap justify-center gap-4">
      {programacoes?.map((programacao) => (
        <Card
          key={programacao.id}
          filme={programacao.filme}
          programacao={programacao}
        />
      ))}
    </div>
  );
};

export default CardList;
