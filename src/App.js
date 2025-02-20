import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { AuthProvider } from "./context/AuthContext";
import Login from "./pages/Login";
import Dashboard from "./pages/Dashboard";
import BookingForm from "./Components/Booking/BookingForm";
import PrivateRoute from "./context/PrivateRoute";
import ServiceForm from "./Components/Service/ServiceForm";
import MastersTable from "./Components/Masters/MastersTable";
import MasterList from "./Components/Masters/MasterList";

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/login" element={<Login />} />
         
          <Route path="/" element={<BookingForm />}>
       
            <Route path="services" element={<ServiceForm />} />
            <Route path="/masters/:serviceId" element={<MasterList />} /> 
            <Route path="masters" element={<MastersTable />} />
          </Route>
          <Route
            path="/dashboard"
            element={
              <PrivateRoute>
                <Dashboard />
              </PrivateRoute>
            }
          />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
