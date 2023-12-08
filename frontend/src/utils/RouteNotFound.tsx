import { Routes, Route, Link } from "react-router-dom";
export const RouteNotFound = ({
  children,
}: {
  children: JSX.Element | JSX.Element[];
}) => {
  return (
    <Routes>
      {children}
      <Route path="*" element={<div><h2>NOT FOUND 404</h2><Link to="/">Home</Link></div>}></Route>
    </Routes>
  );
};
