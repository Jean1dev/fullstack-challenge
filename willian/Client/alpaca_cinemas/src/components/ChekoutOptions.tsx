import React from "react";
import RadioGroup from "./RadioGroup"; 
import Select from "./Select"; 
import CountdownTimer from "./CountdownTimer ";


interface CheckoutOptionsProps {
  radioOptions: { value: string; label: string }[];
  selectedRadio: string;
  setSelectedRadio: (value: string) => void;
  selectTipoIngresso: { value: string; label: string }[];
  setTipoIngresso: (value: string) => void;
}

const CheckoutOptions: React.FC<CheckoutOptionsProps> = ({
  radioOptions,
  selectedRadio,
  setSelectedRadio,
  selectTipoIngresso,
  setTipoIngresso
}) => {
  return (
    <div className="p-4 bg-custom-gray rounded-lg flex flex-wrap gap-6 md:gap-6">
      <div className="w-full md:w-auto gap-2 md:gap-2 text-sm md:text-base min-w-[150px]">
        <RadioGroup
          options={radioOptions}
          selectedValue={selectedRadio}
          onRadioChange={setSelectedRadio}
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
      <div className="w-full md:w-auto flex gap-2 md:gap-2 text-sm md:text-base min-w-[150px]">
        <CountdownTimer />
      </div>
    </div>
  );
};

export default CheckoutOptions;
