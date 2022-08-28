import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./Components/Home/Home";



export default () => {





    return<>

      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />



        </Routes>
        
      
      </BrowserRouter>






    </>


};
