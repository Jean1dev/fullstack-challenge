import React, { useState } from "react";
import { useSelector } from "react-redux";
import { RootState } from "../store/store";
import { Cadeira } from "../common/types/Cadeira";
import CadeiraLivre from "../assets/cadeira_livre.svg";
import CadeiraSelecionada from "../assets/cadeira_selecionada.svg";
import CadeiraOcupada from "../assets/cadeira_ocupada.svg";
import CheckoutOptions from "../components/ChekoutOptions";

const Checkout = () => {
  const [selectedRadio, setSelectedRadio] = useState("seg");
  const [tipoIngresso, setTipoIngresso] = useState("");

  const programacoes = useSelector(
    (state: RootState) => state.programacao.programacao
  );

  const radioOptions = [
    { label: "13h00", value: "seg" },
    { label: "15h00", value: "ter" },
    { label: "18h00", value: "qua" },
    { label: "20h00", value: "qui" }
  ];
  const selectTipoIngresso = [
    { label: "Ingresso inteira", value: "INTEIRA" },
    { label: "Ingresso Meia entrada", value: "MEIA" }
  ];

  const cadeiras: Cadeira[] = [
    { id: 1, numero: 1, status: "livre" },
    { id: 2, numero: 1, status: "Ocupada" },
    { id: 3, numero: 1, status: "livre" },
    { id: 4, numero: 1, status: "livre" },
    { id: 5, numero: 1, status: "selecionada" },
    { id: 6, numero: 1, status: "livre" },
    { id: 7, numero: 1, status: "livre" },
    { id: 8, numero: 1, status: "livre" },
    { id: 9, numero: 1, status: "livre" },
    { id: 10, numero: 1, status: "livre" }
  ];

  const chunkArray = (arr: any[], size: number) => {
    const result = [];
    for (let i = 0; i < arr.length; i += size) {
      result.push(arr.slice(i, i + size));
    }
    return result;
  };

  const rows = chunkArray(cadeiras, 3);

  return (
    <div className="hero bg-custom-dark-blue min-h-screen">
      <div className="">
        <CheckoutOptions
          radioOptions={radioOptions}
          selectedRadio={selectedRadio}
          setSelectedRadio={setSelectedRadio}
          selectTipoIngresso={selectTipoIngresso}
          setTipoIngresso={setTipoIngresso}
        />
        <div className="hero bg-custom-dark-blue min-h-screen">
          <div className="hero-content flex-col lg:flex-row">
            <div className="max-w-sm">
              <div>
                <p>Filme:</p>
                <h1 className="text-2xl font-bold">
                  {programacoes![0].filme.title}
                </h1>
                <p>Cadeiras:</p>
                <h1 className="text-2xl font-bold">01, 02, 03</h1>
                <p>Horario:</p>
                <h1 className="text-2xl font-bold">16H00</h1>
                <p>Combos:</p>
                <h1 className="text-2xl font-bold">Chocolate</h1>
              </div>
              <div className="bg-custom-gray rounded flex flex-col p-4 gap-2 md:gap-2">
                <table className="table">
                  <tbody>
                    <tr>
                      <th>Ingressos inteira x2</th>
                      <td>R$ 20.00</td>
                    </tr>
                    <tr>
                      <th>Ingressos meia x1</th>
                      <td>R$ 20.00</td>
                    </tr>
                    <tr>
                      <th>Combos x1</th>
                      <td>R$ 10.00</td>
                    </tr>
                  </tbody>
                  <tfoot>
                    <tr className="text-white bold text-[16px]">
                      <th>Total</th>
                      <td>R$ 50.00</td>
                    </tr>
                  </tfoot>
                </table>
                <button className="btn w-full bg-white text-custom-dark-blue">
                  Comprar
                </button>
              </div>
            </div>

            <div className="bg-custon-mid-blue rounded flex flex-col p-16 gap-6 md:gap-6">
              <h1 className="text-2xl font-bold">Selecione sua cadeira:</h1>
              {rows.map((row, rowIndex) => (
                <div key={rowIndex} className="flex justify-center mb-4">
                  {row.map((cadeira) => {
                    let src;
                    if (cadeira.status === "Ocupada") {
                      src = CadeiraOcupada;
                    } else if (cadeira.status === "selecionada") {
                      src = CadeiraSelecionada;
                    } else {
                      src = CadeiraLivre;
                    }

                    return (
                      <img
                        key={cadeira.id}
                        src={src}
                        alt="cadeira"
                        className="mx-2"
                      />
                    );
                  })}
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Checkout;
