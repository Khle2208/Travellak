import { useNavigate } from "react-router-dom";
import { useState } from "react";

const SignUpForm = () => {
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [email, setEmail] = useState("");
  const navigate = useNavigate();
  const goToLink = (link) => {
    navigate(link);
  };

  const handleSignUp = async () => {
    if (password !== confirmPassword) {
      alert("Passwords do not match!");
      return;
    }

    try {
      const response = await fetch(`http://localhost:8080/Travellak/Users`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          email: email,
          password: password,
        }),
      });

      console.log("Response:", response); // Log response status to check

      if (response.ok) {
        alert("Sign up successful!");
        navigate("/Travellak/Login");
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
      data-aos="fade-left"
    >
      <h1 className="mb-3 fw-bold text-center text-md-start">Sign Up 👋</h1>
      <p className="text-secondary mb-4 text-center">
        Welcome to a new journey. It's your time to shine.
        <br />
        Sign up now to start creating your projects.
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
            placeholder="Example@email.com"
            onChange={(e) => setEmail(e.target.value)}
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
            placeholder="At least 8 characters"
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <div className="mb-3">
          <label htmlFor="password" className="form-label fw-bold">
            Confirm Password
          </label>
          <input
            type="password"
            className="form-control"
            id="confirmPassword"
            placeholder="At least 8 characters"
            onChange={(e) => setConfirmPassword(e.target.value)}
          />
        </div>
        <div className="d-grid mb-3">
          <button type="button" className="btn btn-dark" onClick={handleSignUp}>
            Sign Up
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
            <span className="ms-2">Sign up with Google</span>
          </button>
          <button
            type="button"
            className="btn btn-outline-secondary d-flex align-items-center justify-content-center"
          >
            <i
              className="fa-brands fa-facebook"
              style={{ color: "#0061ff" }}
            ></i>
            <span className="ms-2">Sign up with Facebook</span>
          </button>
        </div>
        <p className="text-center text-secondary small mt-3">
          <button
            type="button"
            onClick={() => goToLink("/Travellak/Login")}
            className="text-primary btn border-0"
          >
            Sign in
          </button>
        </p>
      </form>

      <p className="text-center text-muted small mt-4">
        © 2023 ALL RIGHTS RESERVED
      </p>
    </div>
  );
};
export default SignUpForm;
