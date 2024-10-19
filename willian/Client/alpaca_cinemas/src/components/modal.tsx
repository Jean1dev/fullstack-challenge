import React, { useState } from "react";
import Select from "./Select";
import Input from "./Input";

interface ModalProps {
  isVisible: boolean;
  onClose: () => void;
  cadeira: number;
}

const Modal: React.FC<ModalProps> = ({ isVisible, onClose, cadeira }) => {
  if (!isVisible) return null;

  return (
    <dialog open className="modal bg-custom-dark-blue bg-opacity-50">
      <div className="modal-box bg-custom-gray">
        <h3 className="font-bold text-lg">
          Quem vai ocupar a cadeira {cadeira}
        </h3>
        <div className="py-4">
          <form onSubmit={() => {}} className="flex flex-col gap-2">
            <div>
              <Input type={"text"} placeholder={"nome..."} label={"Nome:"} />
            </div>
            <div>
              <Input
                type={"text"}
                placeholder={"000.000.000-00"}
                label={"Documento:"}
              />
            </div>
            <div>
              <Select
                options={[]}
                onChange={() => {}}
                label={"Selecione seu combo:"}
                value={""}
              />
            </div>
          </form>
        </div>
        <div className="modal-action">
          <button className="btn" onClick={onClose}>
            Fechar
          </button>
        </div>
      </div>
    </dialog>
  );
};

export default Modal;
