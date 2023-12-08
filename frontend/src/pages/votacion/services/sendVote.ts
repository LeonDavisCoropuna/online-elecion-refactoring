import { AxiosError } from "axios";
import axiosConfig from "../../../config/axios";
import { Vote } from "../interfaces/vote.interface";
export const sendVote = async (
  vote: Vote
): Promise<{ message: string; status: number }> => {
  try {
    const res = await axiosConfig.post("/auth/save-vote", vote);
    return {
      message: res.data,
      status: 200,
    };
  } catch (error) {
    if (error instanceof AxiosError) {
      if (error.response && error.response.status === 401) {
        return {
          message: "El voto es unico",
          status: 403,
        };
      }
    }
    return {
      message: "!Ups, ocurrio un error",
      status: 500,
    };
  }
};
