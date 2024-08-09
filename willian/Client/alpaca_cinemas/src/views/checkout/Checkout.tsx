import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../store/store";
import { Cadeira } from "../../common/types/Cadeira";
import CadeiraLivre from "../../assets/cadeira_livre.svg";
import CadeiraSelecionada from "../../assets/cadeira_selecionada.svg";
import CadeiraOcupada from "../../assets/cadeira_ocupada.svg";
import TelaIcon from "../../assets/tela_icon.svg";
import { CriarIngresso } from "../../common/types/CriarIngresso";
import { useParams } from "react-router-dom";
import RadioGroup from "../../components/RadioGroup";
import Select from "../../components/Select";
import CountdownTimer from "../../components/CountdownTimer ";

const Checkout = () => {
  const dispatch = useDispatch();
  const { id } = useParams<{ id: string }>();

  const [selectedRadio, setSelectedRadio] = useState("");
  const [tipoIngresso, setTipoIngresso] = useState("");

  const handleAddData = () => {
    const ingreso: CriarIngresso = {
      comboList: [1],
      programacao: Number(id),
      horario: Number(selectedRadio),
      tipoIngresso: Number(tipoIngresso),
      cadeira: 1,
      nome: "Willian Costa",
      documento: "12365478914"
    };

    dispatch({ type: "SET_INGRESSO", payload: ingreso });

    getData();
  };

  const ingressos = useSelector(
    (state: RootState) => state.criarIngresso.ingresso
  );

  const getData = () => {
    console.log(ingressos);
  };

  const programacoes = useSelector(
    (state: RootState) => state.programacao.programacao
  );

  const radioOptions = [
    { label: "13h00", value: "1" },
    { label: "15h00", value: "2" },
    { label: "18h00", value: "3" },
    { label: "20h00", value: "4" }
  ];
  const selectTipoIngresso = [
    { label: "Ingresso inteira", value: "1" },
    { label: "Ingresso Meia entrada", value: "2" }
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
      <div className="hero-content text-center">
        <div className="">
          <div className="flex justify-between w-full mb-4 p-4 bg-custom-gray rounded-lg flex-wrap gap-6 md:gap-6">
            <div className="flex items-center flex flex-wrap gap-6 md:gap-6">
              <div className="flex gap-2 ">
                <RadioGroup
                  options={radioOptions}
                  selectedValue={selectedRadio}
                  onRadioChange={setSelectedRadio}
                />
              </div>
              <div>
                <Select
                  options={selectTipoIngresso}
                  label={"Tipo de ingresso:"}
                  onChange={setTipoIngresso}
                  value={selectTipoIngresso[0].value}
                />
              </div>
            </div>
            <CountdownTimer />
          </div>

          <div className="hero min-h-screen">
            <div className="hero-content flex-col lg:flex-row-reverse gap-8 md:gap-40 lg:gap-40">
              <div className="max-w-2xl bg-custom-mid-blue shrink-0 shadow-2xl text-center rounded-lg lg:text-left lg:p-24 md:p-24 lg:px-24 md:px-24 p-8 px-8">
                <h1 className="text-2xl p-2 font-bold text-center">
                  Selecione sua cadeira:
                </h1>
                <div className="lg:p-16 md:p-16">
                  <img src={TelaIcon} alt="tela_icon" className="" />
                </div>
                {rows.map((row, rowIndex) => (
                  <div key={rowIndex} className="flex justify-center mb-4 p-2">
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
                          className="mx-2 w-[60px] md:w-[120px] lg:w-[120px]"
                        />
                      );
                    })}
                  </div>
                ))}
              </div>

              <div className="hero flex justify-start">
                <div className="hero-content text-start">
                  <div className="max-w-4xl">
                    <div className="mb-8 md:mb-72 lg:mb-72">
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

                    <div className="card bg-custom-gray p-4 w-full shrink-0 shadow-2xl">
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
                      <button
                        onClick={handleAddData}
                        className="btn w-full bg-white text-custom-dark-blue"
                      >
                        Comprar
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Checkout;
