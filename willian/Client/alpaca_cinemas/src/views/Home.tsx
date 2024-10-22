import React from "react";
import CardList from "../components/CardList";

const Home = () => {
  return (
    <div className="bg-custom-dark-blue  justify-center">
      <h1 className="text-6xl  text-center font-bold mb-6">
        Em exibição essa semana
      </h1>
      <CardList />
    </div>
  );
};

export default Home;
