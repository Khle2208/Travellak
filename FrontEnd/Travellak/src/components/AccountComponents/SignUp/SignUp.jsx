import React, { useEffect } from "react";
import AOS from "aos";
import "aos/dist/aos.css";
import Art from "../../../assets/image/Art.png";
import SignUpForm from "./SignUpForm";
const SignUp = () => {
  useEffect(() => {
    AOS.init({
      duration: 800,
      once: false, // Cho phép animation chạy lại mỗi khi phần tử render
      offset: 200,
    });
  }, []);
  return (
    <div className="container-fluid vh-100 d-flex flex-column flex-md-row p-0 position-relative">
      <div className="col-12 col-md-6 d-flex justify-content-center align-items-center bg-white p-4">
        <img
          src={Art}
          alt="A detailed painting of a bouquet of flowers in a vase"
          className="img-fluid h-auto object-fit-cover rounded"
          style={{ maxHeight: "90vh" }}
          data-aos="fade-right"
        />
      </div>
      <div className="col-12 col-md-6 d-flex justify-content-center align-items-center bg-white p-4">
        <SignUpForm />
      </div>
    </div>
  );
};
export default SignUp;
