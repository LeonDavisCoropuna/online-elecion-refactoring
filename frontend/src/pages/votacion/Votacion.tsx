import { decodeToken } from "../../utils/decodeToken";
import { useState, useEffect } from "react";
import { Party } from "./interfaces/party.interface";
import { fetchPartys } from "./services/fetchPartys";
import Modal from "./components/Model";
import { useModal } from "./hooks/useModal";
import { Vote } from "./interfaces/vote.interface";
import { MdErrorOutline } from "react-icons/md";
import { GrValidate } from "react-icons/gr";
import { sendVote } from "./services/sendVote";
import { LogoutButton } from "../home/components/Logout";
import { useNavigate } from "react-router-dom";
export const Votacion = () => {
  const navigate = useNavigate()
  const { id_person, sub, exp } = decodeToken();
  const [isOpenModal, openModal, closeModal] = useModal();
  const [isOpenModal2, openModal2, closeModal2] = useModal();
  const [isOpenModal3, openModal3, closeModal3] = useModal();

  const [partys, setPartys] = useState<Party[]>([]);
  const [selectedParty, setSelectedParty] = useState<number>(0);
  const [resultVote, setResultVote] = useState({ status: 0, message: "" })
  useEffect(() => {
    const fetchData = async () => {
      const fetchedCandidates = await fetchPartys();
      setPartys(fetchedCandidates);
    };
    fetchData();
  }, []);
  const handleSubmit = async () => {
    const newVote: Vote = {
      idElector: id_person,
      idPoliticalParty: selectedParty,
      date: new Date()
    }
    closeModal();
    const response = await sendVote(newVote)
    if (response.status === 200) {
      setResultVote({ status: 1, message: response.message })
    }
    else {
      setResultVote({ status: 0, message: response.message })
    }
    openModal2();
  }
  const handleOpenVote = () => {
    if (selectedParty) {
      openModal();
    }
    else {
      openModal3();
    }
  }

  // Función para redirigir al inicio cuando el tiempo expire
  const handleTimeout = () => {
    navigate("/");
  };

  useEffect(() => {
    const timeoutId = setTimeout(handleTimeout, exp * 1000); // exp está en segundos
    return () => clearTimeout(timeoutId);
  }, [exp, navigate]);

  // Función para calcular el tiempo restante
  const calculateRemainingTime = () => {
    const now = Math.floor(Date.now() / 1000); 
    const remainingTime = exp - now;
    if(remainingTime < 0) handleTimeout(); 
    return remainingTime > 0 ? remainingTime : 0;
  };

  return (
    <div className="h-screen px-40 py-20">
      <div className="flex justify-between items-center bg-blue-800 rounded-md py-5 px-5">
        <div>Bienvenido {sub}</div>
        <div>Tiempo restante en la sesión: {calculateRemainingTime()} segundos</div>        
        <div>
          <LogoutButton />
        </div>
      </div>
      <div className="flex w-full justify-center py-10">
        <table className="w-[80em] border-[1px] border-gray-600">
          <thead>
            <tr className="[&>th]:py-3 [&>th]:bg-gray-500">
              <th>idPartido</th>
              <th>Logo</th>
              <th>Marcar</th>
            </tr>
          </thead>
          <tbody>
            {partys.map((party) => (
              <tr key={party.id} className={`${selectedParty === party.id ? " bg-slate-700" : ""} [&>td]:text-center border-b-[1px] border-gray-600`} onClick={() => setSelectedParty(party.id)}
              >
                <td className="w-24">{party.id}</td>
                <td className="flex justify-center">
                  <img
                    src={party.namePoliticalParty}
                    alt="party"
                    style={{ width: "80px", height: "80px" }}
                  />
                </td>
                <td>
                  <button
                    className={`w-10 h-10 rounded-full ${selectedParty === party.id ? "bg-green-100" : "bg-black"
                      }`}
                  ></button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <div>
        <button className="bg-red-600 py-3 px-5 rounded-xl hover:bg-red-400" onClick={handleOpenVote}>Votar</button>
      </div>
      <Modal isOpen={isOpenModal} closeModal={closeModal} widthModal={25} heightModal={20}>
        <div className="flex flex-col h-full items-center justify-center">
          <h2 className=" pb-5 px-5">Usuario <strong>{sub}</strong> esta seguro de votar por: </h2>
          <div className="p-3 flex justify-center items-center">
            <img
              src={
                partys
                  ? (partys.find((e) => e.id === selectedParty) || {}).namePoliticalParty || ""
                  : ""
              }
              alt="party"
              style={{ width: "80px", height: "80px" }}
            />
          </div>
          <div className="flex flex-row justify-between w-full px-10 py-2">
            <button className="w-20 bg-green-400 border-[1px] border-gray-500 hover:bg-green-900 hover:text-white" onClick={handleSubmit}>SI</button>
            <button className="w-20  border-[1px] border-gray-500 hover:bg-gray-300" onClick={() => { closeModal() }}>NO</button>
          </div>
        </div>
      </Modal>
      <Modal isOpen={isOpenModal3} closeModal={closeModal3} heightModal={5} widthModal={10}>
        <div className="p-5">
          <div className="flex justify-center flex-col items-center gap-2">
            <MdErrorOutline size={40} color="red" />
            Debe escoger un partido politico antes de votar
          </div>
        </div>
      </Modal>
      <Modal isOpen={isOpenModal2} closeModal={closeModal2} heightModal={5} widthModal={10}>
        <div className="p-5 flex flex-col justify-center items-center gap-3">
          {resultVote.status ? (
            <GrValidate size={40} color="green" />
          ) : (
            <MdErrorOutline size={40} color="red" />
          )}
          <h2>{resultVote.message}</h2>
        </div>
      </Modal>

    </div>
  );
};
