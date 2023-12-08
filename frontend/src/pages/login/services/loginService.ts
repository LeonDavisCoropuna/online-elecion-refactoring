import { Auth } from "../interfaces/auth.interface";
import axios from "../../../config/axios";
import Cookies from "js-cookie";
import { AxiosError } from "axios";

export const loginService = async (user: Auth): Promise<string | null> => {
  try {
    const response = await axios.post("/auth/login", user);
    const token = response.data.token;
    if (token) {
      Cookies.set("token", token);
      return null;
    }
    return "Error en la solicitud de inicio de sesión";
  } catch (error) {
    if (error instanceof AxiosError) {
      if (error.response && error.response.status === 401) {
        return "Credenciales inválidas";
      }
    }
    return "Error en la solicitud de inicio de sesión";
  }
};
