import axios, { AxiosRequestConfig, AxiosError } from "axios";
import Cookies from "js-cookie";

interface MyHeaders {
  Accept?: string;
  "Content-Length"?: string;
  "User-Agent"?: string;
  "Content-Encoding"?: string;
  Authorization?: string;
  [key: string]: string | undefined;
}

interface MyAxiosRequestConfig extends AxiosRequestConfig {
  headers?: MyHeaders;
}

const axiosConfig = axios.create({
  baseURL: "http://localhost:8083/",
  timeout: 3000,
  withCredentials: true,
});

axiosConfig.interceptors.request.use(
  (config: MyAxiosRequestConfig | any) => {
    const token = Cookies.get("token");

    if (token) {
      config.headers = {
        ...config.headers,
        Authorization: `Bearer ${token}`,
      };
    }

    return config;
  },
  (error: AxiosError) => {
    return Promise.reject(error);
  }
);

export default axiosConfig;
