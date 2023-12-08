import React, { useState } from "react";
import { Auth } from "./interfaces/auth.interface";
import { loginService } from "./services/loginService";
import { useNavigate } from "react-router-dom";
import { decodeToken } from "../../utils/decodeToken";

export const Login = () => {
  const navigate = useNavigate();
  const [auth, setAuth] = useState<Auth>({ password: "", userName: "" });
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setAuth({ ...auth, [e.target.name]: e.target.value });
    setError(null);
  };

  const [error, setError] = useState<string | null>(null);

  const handleSubmit = async () => {
    if (!auth.password || !auth.userName) {
      setError("Todos los campos son obligatorios");
      return;
    }

    const loginError = await loginService(auth);

    if (loginError) {
      setError(loginError);
    } else {
      const rol = decodeToken()
      if(rol.roles.includes("ROLE_USER")) {
        navigate("/votacion")
      }
      else{
        navigate("/resultado")
      }
    }
  };
  return (
    <div className="flex justify-center items-center h-screen">
      <div className="flex flex-col gap-5 bg-gray-800 p-10 rounded-2xl shadow-md shadow-slate-950">
        <div className="flex">
          <h1 className="w-24">Username:</h1>
          <input
            className="w-48 rounded-md py-1 text-black pl-2"
            value={auth.userName}
            onChange={handleChange}
            name="userName"
          />
        </div>
        <div className="flex">
          <h1 className="w-24">Password:</h1>
          <input
            className="w-48 rounded-md py-1 text-black pl-2"
            value={auth.password}
            onChange={handleChange}
            name="password"
            type="password"
          />
        </div>
        {error && <h1 className="text-red-500 font-light">{error}</h1>}
        <div>
          <button
            className="bg-blue-700 rounded-md hover:bg-sky-300 hover:text-black w-72 py-2"
            onClick={handleSubmit}
          >
            Login
          </button>
        </div>
      </div>
    </div>
  );
};
