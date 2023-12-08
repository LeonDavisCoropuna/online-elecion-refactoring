export interface User {
  id_person: number;
  sub: string;
  roles: string[];
  exp: number;
}

export const userEmpty: User = {
  id_person: 0,
  sub: "",
  roles: [],
  exp: 0,
};
