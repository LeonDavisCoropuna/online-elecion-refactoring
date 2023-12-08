import { Candidate } from "../interfaces/candidate.interface";
import axios from "../../../config/axios";

export const fetchCandidates = async (): Promise<Candidate[]> => {
  try {
    const response = await axios.get("/auth/all-candidate");
    const candidates: Candidate[] = response.data;
    return candidates;
  } catch (error) {
    
    return [
      {
        candidateName: "Aldo",
        candidateLastName: "Martinez",
        userName: "aldechi_11",
        job: "Presidente",
        namePoliticalParty: "FreeFap",
        id: 5,
      },
      {
        candidateName: "Aldo",
        candidateLastName: "Martinez",
        userName: "aldechi_11",
        job: "Presidente",
        namePoliticalParty: "FreeFap",
        id: 6,
      },
    ];
  }
};
