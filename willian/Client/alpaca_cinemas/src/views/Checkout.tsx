import React, { useState } from "react";
import RadioGroup from "../components/RadioGroup";
import Select from "../components/Select";

const Checkout = () => {
  const [selectedRadio, setSelectedRadio] = useState("seg");
  const [tipoIngresso, setTipoIngresso] = useState("");

  const radioOptions = [
    { label: "Seg", value: "seg", day: 19 },
    { label: "Ter", value: "ter", day: 20 },
    { label: "Qua", value: "qua", day: 21 },
    { label: "Qui", value: "qui", day: 22 },
    { label: "Sex", value: "sex", day: 23 }
  ];
  const selectTipoIngresso = [
    { label: "Ingresso inteira", value: "INTEIRA" },
    { label: "Ingresso Meia entrada", value: "MEIA" }
  ];

  return (
    <div className="hero bg-custom-dark-blue min-h-screen">
      <div className="hero-content text-center">
        <div className="max-w-md w-full p-4 bg-custom-gray rounded-lg flex flex-wrap gap-6 md:gap-6">
          {/* <div className="w-full md:w-auto flex gap-2 md:gap-2 text-sm md:text-base min-w-[150px]">
            Data:
            <RadioGroup
              options={radioOptions}
              selectedValue={selectedRadio}
              onRadioChange={setSelectedRadio}
            />
          </div> */}
          <div className="w-full md:w-auto flex gap-2 md:gap-2 text-sm md:text-base min-w-[150px]">
            <Select
              options={selectTipoIngresso}
              label={"Selecione o horário:"}
              onChange={setTipoIngresso}
              value={selectTipoIngresso[0].value}
            />
          </div>
          <div className="w-full md:w-auto flex gap-2 md:gap-2 text-sm md:text-base min-w-[150px]">
            <Select
              options={selectTipoIngresso}
              label={"Tipo de ingresso:"}
              onChange={setTipoIngresso}
              value={selectTipoIngresso[0].value}
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Checkout;
