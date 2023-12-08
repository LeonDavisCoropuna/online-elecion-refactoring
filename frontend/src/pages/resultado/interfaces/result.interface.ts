export interface ResultPartys {
  idCandidate: number;
  nameCandidate: string;
  lastNameCandidate: string;
  jobCandidate: string;
  idPoliticalParty: number;
  namePoliticalParty: string;
  numVotes: number;
}

export const EmptyResultPartys: ResultPartys[] = [
  {
    idCandidate: 0,
    idPoliticalParty: 1,
    jobCandidate: "Presssiii",
    lastNameCandidate: "Martinez",
    nameCandidate: "Aldo",
    namePoliticalParty: "Bananas Cortp",
    numVotes: 666,
  },
];
