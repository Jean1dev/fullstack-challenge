import React from "react";

interface RadioOptionProps {
  label: string;
  value: string;
  isSelected: boolean;
  onRadioChange: (value: string) => void;
  onKeyDown: (
    event: React.KeyboardEvent<HTMLDivElement>,
    value: string
  ) => void;
}

const RadioOption: React.FC<RadioOptionProps> = ({
  label,
  value,
  isSelected,
  onRadioChange,
  onKeyDown
}) => {
  return (
    <div
      role="button"
      tabIndex={0}
      className={`btn flex flex-col botder-0 ${
        isSelected ? "bg-custom-blue text-white" : "bg-gray-200 text-black"
      }`}
      onClick={() => onRadioChange(value)}
      onKeyDown={(event) => onKeyDown(event, value)}
    >
      <p>{label}</p>
      <input
        type="radio"
        name="radio-1"
        className="radio"
        checked={isSelected}
        onChange={() => onRadioChange(value)}
        hidden
      />
    </div>
  );
};

export default RadioOption;
