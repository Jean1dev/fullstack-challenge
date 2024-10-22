import React, { lazy, Suspense } from "react";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import Navbar from "../components/NavBar";
import Footer from "../components/Footer";

const Home = lazy(() => import("../views/Home"));
const ProgramacaoDetails = lazy(() => import("../views/ProgramacaoDetails"));
const Checkout = lazy(() => import("../views/checkout/Checkout"));

const Router = () => {
  return (
    <BrowserRouter>
      <Navbar />
      <Suspense fallback={<div>Loading...</div>}>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route
            path="/programacao-details/:id"
            element={<ProgramacaoDetails />}
          />
          <Route path="/checkout/:id" element={<Checkout />} />
          <Route path="*" element={<Navigate to="/" />} />
        </Routes>
      </Suspense>
      <Footer />
    </BrowserRouter>
  );
};

export default Router;
