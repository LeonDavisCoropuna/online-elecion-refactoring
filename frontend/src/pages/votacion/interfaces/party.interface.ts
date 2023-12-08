export interface Party {
  id: number;
  namePoliticalParty: string;
}

export const EmptyParty: Party[] = [
  {
    id: 3,
    namePoliticalParty:
      "data:image/jpeg;base64,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",
  },
  {
    id: 6,
    namePoliticalParty:
      "data:image/jpeg;base64,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",
  },
  {
    id: 8,
    namePoliticalParty:
      "data:image/jpeg;base64,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",
  },
  {
    id: 9,
    namePoliticalParty:
      "data:image/jpeg;base64,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",
  },
];
