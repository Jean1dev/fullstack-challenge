import React from "react";

interface Props {
  type: string;
  placeholder?: string;
  label: string;
  error?: string;
}

const Input: React.FC<Props> = ({ type, placeholder, label, error }) => {
  return (
    <label className="form-control w-full max-w-xs">
      <div className="label">
        <span className="label-text text-white">{label}</span>
      </div>
      <input
        type={type}
        placeholder={placeholder}
        className="input input-bordered w-full max-w-xs"
      />
      {error && (
        <div className="label">
          <span className="label-text-alt text-white">{error}</span>
        </div>
      )}
    </label>
  );
};

export default Input;
