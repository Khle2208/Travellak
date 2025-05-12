import React, { useState, useEffect } from "react";
import TourModal from "./AddTourModal";

const ManageTour = () => {
  const [showModal, setShowModal] = useState(false);
  const [tours, setTours] = useState([
    {
      name: "Khám phá Seoul 5N4Đ",
      destination: "Seoul, Hàn Quốc",
      price: "15,500,000 ₫",
      status: "active",
    },
  ]);

  const fetchTours = () => {
    fetch(`http://localhost:8080/Travellak/Tours`)
      .then((response) => response.json())
      .then((data) => {
        setTours(data.result); // Cập nhật lại danh sách tours
      })
      .catch((error) => console.error("Error:", error));
  };
  const handleDeleteTour = async (tourId) => {
    const token = localStorage.getItem("token");
    try {
      const url = `http://localhost:8080/Travellak/Tours/${tourId}`;
      const response = await fetch(url, {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      });

      console.log("Response:", response); // Log response status to check

      if (response.ok) {
        fetchTours();
        alert("Xoá thành công");
      } else {
        const data = await response.json();
        alert(data.message || "Sign up failed!");
      }
    } catch (error) {
      console.error("Error:", error); // Log error details
      alert("An error occurred!");
    }
  };

  useEffect(() => {
    fetchTours();
  }, []);

  const openModal = () => setShowModal(true);
  const closeModal = () => setShowModal(false);

  const addTour = (newTour) => {
    // format giá
    const formatted = {
      ...newTour,
      price: Number(newTour.price).toLocaleString("vi-VN") + " ₫",
    };
    setTours([formatted, ...tours]);
  };

  return (
    <>
      <div className="content-section">
        <div className="section-header">
          <div className="section-title">Danh sách Tour gần đây</div>
          <button className="btn btn-primary" onClick={openModal}>
            + Thêm Tour mới
          </button>
        </div>
        <table>
          <thead>
            <tr>
              <th>Tên Tour</th>
              <th>Điểm đến</th>
              <th>Giá người lớn</th>
              <th>Giá trẻ em</th>
              <th>Giá trẻ nhỏ</th>
              <th>Trạng thái</th>
              <th>Hành động</th>
            </tr>
          </thead>
          <tbody>
            {tours.map((tour, idx) => (
              <tr key={idx}>
                <td>{tour.tourName}</td>
                <td>{tour.destinationCity?.cityName}</td>
                <td>{tour.priceAdult}</td>
                <td>{tour.priceChild}</td>
                <td>{tour.priceBaby}</td>
                <td>
                  <span className={`status status-${tour.status}`}>
                    {tour.status === "active"
                      ? "Đang hoạt động"
                      : tour.status === "pending"
                      ? "Chờ duyệt"
                      : "Đã hủy"}
                  </span>
                </td>
                <td>
                  <span className="action-btn">
                    <i className="fas fa-edit"></i>
                  </span>
                  <span className="action-btn">
                    <i className="fas fa-eye"></i>
                  </span>
                  <span
                    className="action-btn"
                    onClick={() => handleDeleteTour(tour.tourId)}
                  >
                    <i className="fas fa-trash"></i>
                  </span>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <TourModal
        show={showModal}
        setShow={setShowModal}
        fetchTours={fetchTours}
        onClose={closeModal}
        onSave={addTour}
      />
    </>
  );
};

export default ManageTour;
