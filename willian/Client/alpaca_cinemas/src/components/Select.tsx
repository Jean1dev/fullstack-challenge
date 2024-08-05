import React from "react";

interface SelectProps {
  options: { label: string; value: string }[];
  onChange: (value: string) => void;
  label: string;
  value: string;
}

const Select: React.FC<SelectProps> = ({ options, onChange, label, value }) => {
  return (
    <label className="form-control w-full max-w-xs">
      <div className="label">
        <span className="label-text text-white">{label}</span>
      </div>
      <select
        className="select select-bordered text-custom-dark-blue"
        onChange={(e) => onChange(e.target.value)}
      >
        {options.map((option, index) => (
          <option key={index} value={option.value}>
            {option.label}
          </option>
        ))}
      </select>
    </label>
  );
};

export default Select;
