import React, { useState, useEffect } from "react";
import InputTourModel from "./InputTourModel";
import AddDayTourModel from "./AddDayTourModel";
const TourModal = ({ show, onClose, fetchTours, setShow }) => {
  const [newTour, setNewTour] = useState({
    tourName: "",
    description: "",
    priceAdult: "",
    priceChild: "",
    priceBaby: "",
    imageData: null,
    departureCityId: "",
    destinationCityId: "",
  });

  const [dayTours, setDayTours] = useState([
    { dayNumber: "", title: "", description: "", attractionId: "" },
  ]);

  const initialTour = {
    tourName: "",
    description: "",
    priceAdult: "",
    priceChild: "",
    priceBaby: "",
    imageData: null,
    departureCityId: "",
    destinationCityId: "",
  };

  const initialDayTours = [
    { dayNumber: "", title: "", description: "", attractionId: "" },
  ];
  const [errorMessage, setErrorMessage] = useState({
    tourName: "",
    description: "",
    priceAdult: "",
    priceChild: "",
    priceBaby: "",
    imageData: "",
    departureCityId: "",
    destinationCityId: "",
  });
  const handleClose = () => {
    setNewTour(initialTour);
    setDayTours(initialDayTours);
    setErrorMessage({
      tourName: "",
      description: "",
      priceAdult: "",
      priceChild: "",
      priceBaby: "",
      departureCityId: "",
      destinationCityId: "",
    });
    onClose();
  };

  const [modelState, setModelState] = useState("Add");
  const [cities, setCities] = useState([]);

  const handleChange = (e) => {
    const { name, value, files } = e.target;
    setNewTour((prev) => ({
      ...prev,
      [name]: files ? files[0] : value,
    }));

    setErrorMessage((prev) => ({
      ...prev,
      [name]: "",
    }));
  };
  const handleAddTour = async () => {
    try {
      setErrorMessage({
        tourName: "",
        description: "",
        priceAdult: "",
        priceChild: "",
        priceBaby: "",
        departureCityId: "",
        destinationCityId: "",
      });

      if (dayTours.length < 1) {
        alert("Phải có ít nhất một ngày tour!");
        return;
      }
      const formData = new FormData();
      formData.append("tourName", newTour.tourName);
      formData.append("description", newTour.description);
      formData.append("priceAdult", newTour.priceAdult);
      formData.append("priceChild", newTour.priceChild);
      formData.append("priceBaby", newTour.priceBaby);
      formData.append("departureCityId", newTour.departureCityId);
      formData.append("destinationCityId", newTour.destinationCityId);
      // formData.append("dayTours", JSON.stringify(dayTours));
      if (newTour.imageData) {
        formData.append("imageData", newTour.imageData);
      }
      const token = localStorage.getItem("token");
      const method = "POST";
      const url =
        "http://localhost:8080/Travellak/Tours" +
        (method === "PUT" ? `/${newTour.id}` : "");
      const response = await fetch(url, {
        method: modelState === "Add" ? "POST" : "PUT",
        headers: {
          Authorization: `Bearer ${token}`,
        },
        body: formData,
      });

      console.log("Response:", response); // Log response status to check
      const data = await response.json();
      if (!response.ok) {
        setErrorMessage(data); // Lưu lỗi trả về vào state errorMessage
        return;
      }
      if (response.ok && data.result.tourId) {
        // let tourId = data.result.tourId;
        console.log(token);
        const resDay = await fetch(
          `http://localhost:8080/Travellak/DayTour/AddListDayTour`,
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify(
              dayTours.map((d, idx) => ({
                dayNumber: idx + 1,
                tourId: data.result.tourId,
                title: d.title,
                description: d.description,
                attractionId: d.attractionId,
              }))
            ),
          }
        );
        const data = await response.json();
        if (resDay.ok) {
          if (confirm("Thành công")) {
            fetchTours();
            setShow(false);
          }
        } else {
          setErrorMessage(data);
          alert("Thêm ngày tour thất bại!");
        }
      } else {
        alert(data.message);
      }
    } catch (error) {
      console.error("Error:", error);
      alert("An error occurred!");
    }
  };

  const handleRemoveDayTour = (index) => {
    if (dayTours.length <= 1) {
      alert("Phải có ít nhất một ngày tour!");
      return;
    }
    const updated = [...dayTours];
    updated.splice(index, 1);
    setDayTours(updated);
  };

  const handleDayTourChange = (index, field, value) => {
    const updated = [...dayTours];
    updated[index][field] = value;
    setDayTours(updated);
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    handleAddTour();
  };

  const handleAddDayTour = () => {
    // setDayTours([...dayTours, ""]);
    setDayTours([
      ...dayTours,
      { title: "", description: "", attractionId: "" },
    ]);
  };

  useEffect(() => {
    fetch(`http://localhost:8080/Travellak/Cities`)
      .then((response) => response.json())
      .then((data) => {
        setCities(Array.isArray(data) ? data : data.result || []);
      })

      .catch((error) => console.error("Error:", error));
  }, []);

  if (!show) return null;

  return (
    <div className="modal-overlay">
      <div className="modal-content">
        <span className="modal-close" onClick={onClose}>
          &times;
        </span>
        <h2 className="section-title">Thêm Tour mới</h2>

        <form
          id="tour-form"
          onSubmit={handleSubmit}
          className="mt-4 space-y-4"
          style={{
            maxHeight: "70vh",
            overflowY: "auto",
            overflowX: "hidden",
          }}
        >
          <div className="row">
            <div className="col-md-12">
              <div className="row mb-3">
                <label className="mb-1">Tên tour</label>
                <div className="px-3">
                  <input
                    type="text"
                    name="tourName"
                    value={newTour.tourName}
                    onChange={handleChange}
                    className="form-control"
                    // required
                  />
                  {errorMessage.tourName && (
                    <div className="text-danger">{errorMessage.tourName}</div>
                  )}
                </div>
              </div>
            </div>
            <div className="col-md-6">
              <div className="row mb-3">
                <label className="mb-1">Điểm đi</label>
                <div className="px-3">
                  <select
                    name="departureCityId"
                    value={newTour.departureCityId}
                    onChange={handleChange}
                    className="form-control"
                    // required
                  >
                    <option value={""}>Chọn điểm đi</option>
                    {cities.map((city, index) => (
                      <option key={index} value={city.cityId}>
                        {city.cityName}
                      </option>
                    ))}
                  </select>
                  {errorMessage.departureCityId && (
                    <div className="text-danger">
                      {errorMessage.departureCityId}
                    </div>
                  )}
                </div>
              </div>
            </div>
            <div className="col-md-6">
              <div className="row mb-3">
                <label className="mb-1">Điểm đến</label>
                <div className="px-3">
                  <select
                    name="destinationCityId"
                    value={newTour.destinationCityId}
                    onChange={handleChange}
                    className="form-control"
                    // required
                  >
                    <option value={""}>Chọn điểm đến</option>
                    {cities.map((city, index) => (
                      <option key={index} value={city.cityId}>
                        {city.cityName}
                      </option>
                    ))}
                  </select>
                  {errorMessage.destinationCityId && (
                    <div className="text-danger">
                      {errorMessage.destinationCityId}
                    </div>
                  )}
                </div>
              </div>
            </div>
            <div className="col-md-6">
              <div className="row mb-3">
                <label className="mb-1">Giá người lớn (VND)</label>
                <div className="px-3">
                  <input
                    type="text"
                    name="priceAdult"
                    value={newTour.priceAdult}
                    onChange={handleChange}
                    className="form-control"
                    // required
                  />
                  {errorMessage.priceAdult && (
                    <div className="text-danger">{errorMessage.priceAdult}</div>
                  )}
                </div>
              </div>
            </div>
            <div className="col-md-6">
              <div className="row mb-3">
                <label className="mb-1">Giá trẻ em (VND)</label>
                <div className="px-3">
                  <input
                    type="text"
                    name="priceChild"
                    value={newTour.priceChild}
                    onChange={handleChange}
                    className="form-control"
                    // required
                  />
                  {errorMessage.priceChild && (
                    <div className="text-danger">{errorMessage.priceChild}</div>
                  )}
                </div>
              </div>
            </div>
            <div className="col-md-6">
              <div className="row mb-3">
                <label className="mb-1">Giá trẻ nhỏ (VND)</label>
                <div className="px-3">
                  <input
                    type="text"
                    name="priceBaby"
                    value={newTour.priceBaby}
                    onChange={handleChange}
                    className="form-control"
                    // required
                  />
                  {errorMessage.priceBaby && (
                    <div className="text-danger">{errorMessage.priceBaby}</div>
                  )}
                </div>
              </div>
            </div>
            <div className="col-md-6">
              <div className="row mb-3">
                <label className="mb-1">Hình đại diện</label>
                <div className="px-3">
                  <input
                    type="file"
                    name="imageData"
                    // value={newTour.imageData}
                    onChange={handleChange}
                    className="form-control"
                    // required
                  />
                  {errorMessage.imageData && (
                    <div className="text-danger">{errorMessage.imageData}</div>
                  )}
                </div>
              </div>
            </div>
            <div className="col-md-12">
              <div className="row mb-3">
                <label className="mb-1">Mô tả</label>
                <div className="px-3">
                  <textarea
                    name="description"
                    value={newTour.description}
                    onChange={handleChange}
                    className="form-control"
                    rows={3}
                  ></textarea>
                </div>
              </div>
            </div>
            {/* <div className="col-md-12">
              <InputTourModel title="Hình mô tả" typeInput="file" />
            </div> */}
            <div className="col-md-12">
              <AddDayTourModel
                dayTours={dayTours}
                handleAddDayTour={handleAddDayTour}
                handleDayTourChange={handleDayTourChange}
                handleRemoveDayTour={handleRemoveDayTour}
              ></AddDayTourModel>
            </div>
          </div>
        </form>
        <div className="d-flex justify-content-evenly">
          <button
            type="button"
            onClick={handleClose}
            className="btn btn-outline mr-2"
          >
            Hủy
          </button>
          <button type="submit" className="btn btn-primary" form="tour-form">
            {modelState === "Add" ? "Lưu" : "Cập nhật"}
          </button>
        </div>
      </div>
    </div>
  );
};

export default TourModal;

TourModal.js;
