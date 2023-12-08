import { Table } from "./components/Table";
import { Candidate } from "./interfaces/candidate.interface";
import { fetchCandidates } from "./services/fetchCandidates";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { TbPointFilled } from "react-icons/tb";
export const Home = () => {
  const [candidates, setCandidates] = useState<Candidate[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      const fetchedCandidates = await fetchCandidates();
      setCandidates(fetchedCandidates);
    };

    fetchData();
  }, []);

  return (
    <div className="flex flex-col">
      <div className="flex flex-row justify-between px-10">
        <div className="flex flex-row  justify-between w-full px-10 py-10">
          <h1 >Bienvenido a "Elecciones Online"</h1>
          <Link
            to="/login"
            className=" text-white p-5 bg-blue-700 rounded-lg underline hover:bg-gray-300 hover:text-black"
          >
            Iniciar Sesion
          </Link>
        </div>

      </div>
      <div className="flex flex-col justify-center items-center my-20">

        <Table candidates={candidates} />
        <div className="pt-20 w-full px-20">
          <h1>Integrantes: </h1>
          <ul className="[&>li]:flex [&>li]:items-center justify-center py-2">
            <li><TbPointFilled />  Leon Davis Coropuna</li>
            <li><TbPointFilled />  Avelino Lupo Condori</li>
            <li><TbPointFilled />  Owen Roque Soza</li>
            <li><TbPointFilled />  Aldo Martinez Choque</li>
            <li><TbPointFilled />  Gustavo Ccama Marron</li>
          </ul>
        </div>
      </div>
    </div>
  );
};
