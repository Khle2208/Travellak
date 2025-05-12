import avatar from "../../../assets/image/avatar.png";
import { useEffect, useState } from "react";
import "./Profile.css";
import InputField from "./InputField";
const InfoForm = () => {
  const [user, setUser] = useState();
  useEffect(() => {
    // Sau khi load trang 300ms thì refresh AOS 1 lần

    const token = localStorage.getItem("token");

    fetch(`http://localhost:8080/Travellak/Users/myInfo`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        setUser(data);
      })
      .catch((error) => console.error("Error:", error));
  }, []);
  const handleEdit = async () => {
    const token = localStorage.getItem("token");
    try {
      if (token) {
        const response = await fetch(
          "http://localhost:8080/Travellak/Users/editProfile",
          {
            method: "PUT",
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify({
              email: user?.email,
              phoneNumber: user?.phoneNumber,
              name: user?.name,
            }),
          }
        );

        // const data = await response.json();

        if (response.ok) {
          alert("Edit sussesful!");
        } else {
          alert("Edit failed!");
        }
      }
    } catch (error) {
      console.error("Logout request failed:", error);
    }
  };
  return (
    <div className="flex-shrink-0" style={{ minWidth: "18rem" }}>
      <div className="text-uppercase text-secondary fw-semibold small mb-3">
        Profile Widget
      </div>
      <nav className="nav flex-column gap-3">
        <form onSubmit={(e) => e.preventDefault()}>
          <InputField
            value={user?.email == null ? "" : user.email}
            fieldName={"Email"}
          ></InputField>
          <InputField
            fieldName={"Phone"}
            value={user?.phoneNumber == null ? "" : user.phoneNumber}
            onChange={(e) => setUser({ ...user, phoneNumber: e.target.value })}
          ></InputField>
          <InputField
            fieldName={"Name"}
            value={user?.name == null ? "" : user.name}
            onChange={(e) => setUser({ ...user, name: e.target.value })}
          ></InputField>
        </form>
        <div className="d-flex justify-content-center pt-2">
          <button className="btn btn-blue " type="button" onClick={handleEdit}>
            Edit
          </button>
        </div>
      </nav>
    </div>
  );
};
export default InfoForm;
