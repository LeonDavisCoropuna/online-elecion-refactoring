import axios from "../../../config/axios";
import { EmptyResultPartys, ResultPartys } from "../interfaces/result.interface";


export const fetchResultPartys = async () : Promise<ResultPartys[]> => {
   try{
    const response = await axios.get("/auth/online-results");
    console.log(response)
    const results: ResultPartys[] = response.data
    return results
   } catch(error){
    console.log(error)
    return EmptyResultPartys;
   }
}
