import React from "react";
import { jwtDecode } from "jwt-decode";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { useAuth } from "../../Authentication";
const LoginForm = () => {
  const { login } = useAuth();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const goToLink = (link) => {
    navigate(link);
  };

  const handleLogin = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/Travellak/Authentication/Login`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            email: email,
            password: password,
          }),
        }
      );

      console.log("Response:", response);

      if (response.ok) {
        const data = await response.json();
        const decodedToken = jwtDecode(data.result.token);
        const roles = decodedToken.role;
        const firstRoleName = roles[0]?.roleName;
        login(data.result.token);
        if (firstRoleName == "ADMIN") {
          navigate("/AdminPage");
        } else {
          navigate("/");
        }
        // console.log(data.result.token);
        // login(data.result.token);
        // navigate("/");
      } else {
        const data = await response.json();
        alert(data.message || "Sign up failed!");
      }
    } catch (error) {
      console.error("Error:", error); // Log error details
      alert("An error occurred!");
    }
  };
  return (
    <div
      className="d-flex flex-column justify-content-center align-items-center bg-white p-4 p-md-5 w-100"
      data-aos="fade-right"
    >
      <h1 className="mb-3 fw-bold text-center text-md-start">
        Welcome Back 👋
      </h1>
      <p className="text-secondary mb-4 text-center">
        Today is a new day. It's your day. You shape it.
        <br />
        Sign in to start managing your projects.
      </p>

      <form
        className="w-100"
        style={{ maxWidth: "400px" }}
        onSubmit={(e) => e.preventDefault()}
      >
        <div className="mb-3">
          <label htmlFor="email" className="form-label fw-bold">
            Email
          </label>
          <input
            type="email"
            className="form-control"
            id="email"
            onChange={(e) => setEmail(e.target.value)}
            placeholder="Example@email.com"
          />
        </div>
        <div className="mb-3">
          <label htmlFor="password" className="form-label fw-bold">
            Password
          </label>
          <input
            type="password"
            className="form-control"
            id="password"
            onChange={(e) => setPassword(e.target.value)}
            placeholder="At least 8 characters"
          />
          <div className="mt-1">
            <button
              type="button"
              className="text-decoration-none btn text-primary small"
            >
              Forgot Password?
            </button>
          </div>
        </div>
        <div className="d-grid mb-3">
          <button type="button" className="btn btn-dark" onClick={handleLogin}>
            Sign in
          </button>
        </div>
        <div className="d-flex align-items-center my-3">
          <hr className="flex-grow-1" />
          <span className="mx-2 text-secondary">Or</span>
          <hr className="flex-grow-1" />
        </div>
        <div className="d-grid gap-2">
          <button
            type="button"
            className="btn btn-outline-secondary d-flex align-items-center justify-content-center"
          >
            <i className="fa-brands fa-google" style={{ color: "#ff2600" }}></i>
            <span className="ms-2">Sign in with Google</span>
          </button>
          <button
            type="button"
            className="btn btn-outline-secondary d-flex align-items-center justify-content-center"
          >
            <i
              className="fa-brands fa-facebook"
              style={{ color: "#0061ff" }}
            ></i>
            <span className="ms-2">Sign in with Facebook</span>
          </button>
        </div>
        <p className="text-center text-secondary small mt-3">
          Don't have an account?{" "}
          <button
            type="button"
            onClick={() => goToLink("/Travellak/SignUp")}
            className="text-primary btn border-0"
          >
            Sign up
          </button>
        </p>
      </form>

      <p className="text-center text-muted small mt-4">
        © 2023 ALL RIGHTS RESERVED
      </p>
    </div>
  );
};

export default LoginForm;
