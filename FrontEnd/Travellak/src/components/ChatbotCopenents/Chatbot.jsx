import { useEffect } from "react";
import { useLocation } from "react-router-dom";

const Chatbot = () => {
  const location = useLocation();

  useEffect(() => {
    if (!document.getElementById("df-script")) {
      const script = document.createElement("script");
      script.id = "df-script";
      script.src =
        "https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1";
      script.async = true;
      document.body.appendChild(script);
    }
  }, []);

  // Nếu đang ở /login thì không hiển thị chatbot
  if (
    [
      "/Travellak/Login",
      "/Travellak/SignUp",
      "/checkout",
      "/AdminPage",
    ].includes(location.pathname)
  ) {
    return null;
  }

  return (
    <df-messenger
      intent="WELCOME"
      chat-title="BookStoreChat"
      agent-id="f3c9cc03-bf4e-436a-a90e-a4b4d32a0e1b"
      language-code="vi"
    ></df-messenger>
  );
};

export default Chatbot;
