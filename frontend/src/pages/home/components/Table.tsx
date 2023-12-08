import { Candidate } from "../interfaces/candidate.interface";
import { TableRow } from "./TableRow";
export const Table = ({ candidates }: { candidates: Candidate[] }) => {
  return (
    <table className=" w-[80em] ">
      <thead className=" border-b-[1px] border-b-gray-300 [&>tr>th]:text-start [&>tr>th]:pb-4 [&>tr>th]:px-2">
        <tr>
          <th>Nombre</th>
          <th>Apellido</th>
          <th>Cargo</th>
          <th>Partido</th>
          <th className="w-32">Logo</th>
        </tr>
      </thead>
      <tbody>
        {candidates.map((candidate) =>
          candidate.job === "Presidente" ? (
            <TableRow candidate={candidate} key={candidate.id} />
          ) : null
        )}
      </tbody>  
    </table>
  );
};
