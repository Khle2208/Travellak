import { createContext, useContext, useState, useEffect } from "react";
const AuthContext = createContext();
import { useNavigate } from "react-router-dom";
export const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [isLoading, setIsLoading] = useState(true);

  const updateAuthStatus = () => {
    const token = localStorage.getItem("token");
    setIsAuthenticated(!!token);
  };

  const navigate = useNavigate();
  const goToLink = (link) => {
    navigate(link);
  };

  useEffect(() => {
    const token = localStorage.getItem("token");

    const validateToken = async () => {
      try {
        if (!token) {
          setIsAuthenticated(false);
          return;
        }
        const response = await fetch(
          "http://localhost:8080/Travellak/Authentication/Introspect",
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({ token }),
          }
        );

        if (!response.ok) {
          throw new Error("Request failed");
        }

        const data = await response.json();
        if (data.result.valid) {
          setIsAuthenticated(true);
        } else {
          if (confirm("Phiên đăng nhập đã hết hạn. Vui lòng đăng nhập lại.")) {
            goToLink("/Travellak/Login");
          }
          localStorage.removeItem("token");
          setIsAuthenticated(false);
        }
      } catch (error) {
        console.error("Token validation failed:", error);
        localStorage.removeItem("token");
        setIsAuthenticated(false);
      } finally {
        setIsLoading(false);
      }
    };

    validateToken();

    // Lắng nghe sự kiện thay đổi của localStorage
    const handleStorageChange = () => {
      console.log("localStorage đã thay đổi ở một tab khác");
      updateAuthStatus();
    };

    window.addEventListener("storage", handleStorageChange);

    // Dọn dẹp bộ lắng nghe sự kiện khi component bị unmount
    return () => {
      window.removeEventListener("storage", handleStorageChange);
    };
  }, []); // Chỉ chạy một lần khi component mount

  const login = (token) => {
    console.log(token);
    localStorage.setItem("token", token);
    setIsAuthenticated(true);
  };

  const logout = async () => {
    const token = localStorage.getItem("token");
    try {
      if (token) {
        await fetch("http://localhost:8080/Travellak/Authentication/Logout", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ token }),
        });
      }
    } catch (error) {
      console.error("Logout request failed:", error);
    } finally {
      localStorage.removeItem("token");
      setIsAuthenticated(false);
    }
  };

  return (
    <AuthContext.Provider value={{ isAuthenticated, isLoading, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
