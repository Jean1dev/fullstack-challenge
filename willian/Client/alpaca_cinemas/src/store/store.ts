import CriarIngressoReducer from "../reducers/CriarIngresso.reducer";
import programacaoReducer from "../reducers/Programacao.reducer";
import { configureStore } from "@reduxjs/toolkit";

const store = configureStore({
  reducer: {
    programacao: programacaoReducer,
    criarIngresso: CriarIngressoReducer
  }
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;

export default store;
