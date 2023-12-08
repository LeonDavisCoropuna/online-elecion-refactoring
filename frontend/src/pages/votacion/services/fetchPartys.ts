import { EmptyParty, Party } from "../interfaces/party.interface";
import axios from "../../../config/axios";

export const fetchPartys = async (): Promise<Party[]> => {
  try {
    const response = await axios.get("/auth/all-political-party");
    const candidates: Party[] = response.data;
    console.log(response.data);
    return candidates;
  } catch (error) {
    return EmptyParty;
  } 
};
