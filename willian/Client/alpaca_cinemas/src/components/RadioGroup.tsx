import React from "react";
import RadioOption from "./RadioOption";

interface RadioGroupProps {
  options: { label: string; value: string }[];
  selectedValue: string;
  onRadioChange: (value: string) => void;
}

const RadioGroup: React.FC<RadioGroupProps> = ({
  options,
  selectedValue,
  onRadioChange
}) => {
  const handleKeyDown = (
    event: React.KeyboardEvent<HTMLDivElement>,
    value: string
  ) => {
    if (event.key === "Enter" || event.key === " ") {
      onRadioChange(value);
    }
  };

  return (
    <div className="flex flex-col">
      <div className="label">
        <span className="label-text text-white">Horario:</span>
      </div>
      <div className="flex gap-2 md:gap-2">
        {options.map((option) => (
          <RadioOption
            key={option.value}
            label={option.label}
            value={option.value}
            isSelected={selectedValue === option.value}
            onRadioChange={onRadioChange}
            onKeyDown={handleKeyDown}
          />
        ))}
      </div>
    </div>
  );
};

export default RadioGroup;
