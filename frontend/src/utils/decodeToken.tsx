import Cookies from "js-cookie";
import { jwtDecode } from "jwt-decode";
import { User, userEmpty } from "../interfaces/user.interface";

export const decodeToken = (): User => {
  const token = Cookies.get("token");
  if (token) {
    const payload = jwtDecode(token) as User;
    return payload;
  }
  return userEmpty;
};
