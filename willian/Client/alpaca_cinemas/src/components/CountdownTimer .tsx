import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const CountdownTimer = () => {
  const initialTime = 10 * 60;

  const navigate = useNavigate();

  const [timeLeft, setTimeLeft] = useState(initialTime);

  useEffect(() => {
    if (timeLeft <= 0) {
      navigate("/");
      return;
    }

    const intervalId = setInterval(() => {
      setTimeLeft((prevTime) => prevTime - 1);
    }, 1000);

    return () => clearInterval(intervalId);
  }, [timeLeft]);

  const formatTime = (time: number): string => {
    const minutes = Math.floor(time / 60);
    const seconds = time % 60;
    return `${minutes < 10 ? "0" : ""}${minutes}:${
      seconds < 10 ? "0" : ""
    }${seconds}`;
  };

  return (
    <div className="max-w-xs text-end">
      <div className="label">
        <span className="label-text text-white">Finalize sua compra em:</span>
      </div>
      <p className="text-[24px] font-bold">{formatTime(timeLeft)}</p>
    </div>
  );
};

export default CountdownTimer;
