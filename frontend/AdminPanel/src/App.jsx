import { Routes, Route } from "react-router-dom";
import Auth from "./pages/Auth/Auth.jsx";
import Panel from "./pages/Panel/Panel.jsx";

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={ <Auth/> } />
        <Route path="/panel/*" element={ <Panel/> } />
      </Routes>
    </>
  )
}

export default App
