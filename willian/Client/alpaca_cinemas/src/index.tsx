import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./main/App";
import { Provider } from "react-redux";
import store from "./store/store";
import Navbar from "./components/NavBar";
import Footer from "./components/Footer";

const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement
);
root.render(
  <React.StrictMode>
    <Provider store={store}>
      <App />
    </Provider>
  </React.StrictMode>
);
