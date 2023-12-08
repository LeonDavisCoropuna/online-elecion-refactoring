import { useEffect } from "react";
import { useNavigate } from "react-router-dom";


export const TimeExpiration = (exp: number) => {
    const navigate = useNavigate()
    const handleTimeout = () => {
        navigate("/");
    };

    useEffect(() => {
        const timeoutId = setTimeout(handleTimeout, exp * 1000); // exp está en segundos
        return () => clearTimeout(timeoutId);
    }, [exp, navigate]);
}