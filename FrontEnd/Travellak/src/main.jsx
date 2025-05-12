import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./index.css";
import App from "./App.jsx";
import ScrollToTop from "./ScrollToTop.jsx";
import TourPage from "./components/TourDetailConponents/TourPage.jsx";
import Chatbot from "./components/ChatBotCopenents/ChatBot.jsx";
import SignUp from "./components/AccountComponents/SignUp/SignUp.jsx";
import Login from "./components/AccountComponents/Login/Login.jsx";
import { AuthProvider } from "./components/Authentication.jsx";
import ProfilePage from "./components/ProfileComponents/Profile.jsx";
import AdminPage from "./components/Admin/admin.jsx";
createRoot(document.getElementById("root")).render(
  // <StrictMode>
  // <StrictMode>
  <Router>
    <AuthProvider>
      <ScrollToTop />
      <Routes>
        <Route path="/" element={<App />} />
        <Route path="/tour/:id" element={<TourPage />} />
        <Route path="/Travellak/Login" element={<Login />} />
        <Route path="/Travellak/SignUp" element={<SignUp />} />
        <Route path="/Travellak/MyInfo" element={<ProfilePage />} />
        <Route path="/AdminPage" element={<AdminPage />} />
      </Routes>
      <Chatbot />
    </AuthProvider>
  </Router>
  // </StrictMode>
  // </StrictMode>
);
