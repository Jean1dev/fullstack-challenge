import React, { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { Cadeira } from "../../common/types/Cadeira";
import CadeiraLivre from "../../assets/cadeira_livre.svg";
import CadeiraSelecionada from "../../assets/cadeira_selecionada.svg";
import CadeiraOcupada from "../../assets/cadeira_ocupada.svg";
import TelaIcon from "../../assets/tela_icon.svg";
import { CriarIngresso } from "../../common/types/CriarIngresso";
import { useParams } from "react-router-dom";
import RadioGroup, { TRadioOption } from "../../components/RadioGroup";
import Select from "../../components/Select";
import CountdownTimer from "../../components/CountdownTimer ";
import { Programacao } from "../../common/types/Programacao";
import Modal from "../../components/modal";
import { getProgramacaoById } from "../../services/Filmes/Request";
import { Horario } from "../../common/types/Horario";
import { getTipoIngresso } from "../../services/Ingressos/Request";
import { getCadeiras } from "../../services/Salas/request";

type TTipoIngresso = {
  id: number;
  preco: number;
  tipo_ingresso: string;
};

const Checkout = () => {
  const dispatch = useDispatch();
  const { id } = useParams<string>();
  const [programacao, setProgramacao] = useState<Programacao | null>();

  const [selectedRadio, setSelectedRadio] = useState("");
  const [tipoIngresso, setTipoIngresso] = useState("");
  const [cadeira, setCadeira] = useState<number>();
  const [horarios, setHorarios] = useState<TRadioOption[]>([]);
  const [selectTipoIngresso, setSelectTipoIngresso] = useState<TRadioOption[]>(
    []
  );
  const [cadeiras, setCadeiras] = useState<Cadeira[]>([]);

  const [isModalVisible, setModalVisible] = useState<boolean>(false);

  const showModal = () => setModalVisible(true);
  const hideModal = () => setModalVisible(false);

  const fetchProgramacao = async (id: string) => {
    const data = await getProgramacaoById(id);
    if (data.message) {
      setProgramacao(null);
      return;
    }
    fetchCadeiras(data.sala.id);
    handleSetHorario(data.horarios);
    setProgramacao(data);
  };

  const fetchCadeiras = async (id: number) => {
    const data = await getCadeiras(id);
    setCadeiras(data);
  };

  const handleAddData = () => {
    const ingreso: CriarIngresso = {
      comboList: [1],
      programacao: Number(id),
      horario: Number(selectedRadio),
      tipoIngresso: Number(tipoIngresso),
      cadeira: cadeira!,
      nome: "Willian Costa",
      documento: "12365478914"
    };

    dispatch({ type: "SET_INGRESSO", payload: ingreso });
  };

  const handleSetHorario = (data: Horario[]) => {
    const options: TRadioOption[] = [];
    data.forEach((item) => {
      options.push({ label: item.horaInicio, value: item.id.toString() });
    });

    setHorarios(options);
  };

  const handleSelectCadeira = (cadeira: number) => {
    showModal();
    setCadeira(cadeira);
  };

  const handleSelectTipoIngresso = (data: TTipoIngresso[]) => {
    const filteredData: TRadioOption[] = [];

    data.forEach((element: TTipoIngresso) => {
      filteredData.push({
        label: element.tipo_ingresso,
        value: element.id.toString()
      });
    });

    setSelectTipoIngresso(filteredData);
  };

  const fetchTipoIngresso = async () => {
    const data = await getTipoIngresso();

    handleSelectTipoIngresso(data);
  };

  const chunkArray = (arr: any[], size: number) => {
    const result = [];
    for (let i = 0; i < arr.length; i += size) {
      result.push(arr.slice(i, i + size));
    }
    return result;
  };

  const rows = chunkArray(cadeiras, 3);

  useEffect(() => {
    if (id) {
      fetchProgramacao(id);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [id]);

  useEffect(() => {
    fetchTipoIngresso();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <div className="hero bg-custom-dark-blue min-h-screen">
      <div className="hero-content text-center">
        <div className="">
          <div className="flex justify-between w-full mb-4 p-4 bg-custom-gray rounded-lg flex-wrap gap-6 md:gap-6">
            <div className="flex items-center flex flex-wrap gap-6 md:gap-6">
              <div className="flex gap-2 ">
                <RadioGroup
                  options={horarios}
                  selectedValue={selectedRadio}
                  onRadioChange={setSelectedRadio}
                />
              </div>
              <div>
                <Select
                  options={selectTipoIngresso}
                  label={"Tipo de ingresso:"}
                  onChange={setTipoIngresso}
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
                        <button
                          onClick={() => handleSelectCadeira(cadeira.numero)}
                          key={cadeira.id}
                          disabled={cadeira.status === "Ocupada"}
                        >
                          <img
                            src={src}
                            alt="cadeira"
                            className="mx-2 w-[60px] md:w-[120px] lg:w-[120px]"
                          />
                        </button>
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
                        {programacao?.filme.title}
                      </h1>
                      <p>Cadeiras:</p>
                      <h1 className="text-2xl font-bold">01, 02, 03</h1>
                      <p>Horario:</p>

                      <h1 className="text-2xl font-bold">
                        {horarios.map((horario) => {
                          return horario.value === selectedRadio
                            ? horario.label
                            : "";
                        })}
                      </h1>
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

      <Modal
        isVisible={isModalVisible}
        onClose={hideModal}
        cadeira={cadeira!}
      />
    </div>
  );
};

export default Checkout;
