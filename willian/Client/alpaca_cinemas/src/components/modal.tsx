import React, { useState } from "react";

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
        <h3 className="font-bold text-lg">Quem vai ocupar a cadeira {cadeira}</h3>
        <div className="py-4">Olá</div>
        <div className="modal-action">
          <h1></h1>
          <button className="btn" onClick={onClose}>
            Fechar
          </button>
        </div>
      </div>
    </dialog>
  );
};

export default Modal;
