import Cookies from "js-cookie";
import { useNavigate } from "react-router-dom";

export const LogoutButton = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    Cookies.remove("token");
    navigate("/");
  };

  return (
    <button className="bg-red-500 px-5 py-1 rounded-md hover:bg-white hover:text-black" onClick={handleLogout}>
      Salir
    </button>
  );
};
