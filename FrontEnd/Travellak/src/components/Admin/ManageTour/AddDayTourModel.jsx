import React, { useState, useEffect } from "react";
const AddDayTourModel = ({
  dayTours,
  handleDayTourChange,
  handleAddDayTour,
  handleRemoveDayTour,
}) => {
  const [attractions, setAttractions] = useState([]);
  useEffect(() => {
    fetch(`http://localhost:8080/Travellak/Attractions`)
      .then((response) => response.json())
      .then((data) => {
        setAttractions(Array.isArray(data) ? data : data.result || []);
      })

      .catch((error) => console.error("Error:", error));
  }, []);
  return (
    <>
      <p className="ps-2">Lịch trình</p>
      <div id="authors">
        {dayTours.map((dayTour, index) => (
          <div
            key={index}
            className="input-group mb-2 px-3 d-flex align-items-start"
          >
            <div className="flex-grow-1 me-2 row border border-1 pt-3 rounded-3">
              <div className="col-md-6">
                <label className="mb-1">Ngày </label>
                <input
                  type="text"
                  name="dayNumber"
                  className="form-control mb-2"
                  placeholder="Day number"
                  value={index + 1}
                  onChange={(e) => handleDayTourChange(index, e.target.value)}
                  readOnly
                />
              </div>
              <div className="col-md-6">
                <label className="mb-1">Tên ngày </label>
                <input
                  type="text"
                  name="listAuthorDetail[]"
                  className="form-control"
                  placeholder="Title"
                  value={dayTour.title}
                  onChange={(e) =>
                    handleDayTourChange(index, "title", e.target.value)
                  }
                  required
                />
              </div>
              <div className="col-md-12">
                <div className="row mb-3">
                  <label className="mb-1">Mô tả</label>
                  <div className="px-3">
                    <textarea
                      className="form-control"
                      placeholder="Nhập mô tả chi tiết về ngày tour"
                      rows={3}
                      onChange={(e) =>
                        handleDayTourChange(
                          index,
                          "description",
                          e.target.value
                        )
                      }
                    ></textarea>
                  </div>
                </div>
              </div>
              <div className="col-md-12">
                <div className="row mb-3">
                  <label className="mb-1">Điểm đi</label>
                  <div className="px-3">
                    <select
                      value={dayTour.attractionId}
                      className="form-control"
                      onChange={(e) =>
                        handleDayTourChange(
                          index,
                          "attractionId",
                          e.target.value
                        )
                      }
                    >
                      <option value="">Chọn điểm đến</option>
                      {attractions.map((attraction) => (
                        <option value={attraction.attractionId}>
                          {attraction.attractionsName}
                        </option>
                      ))}
                    </select>
                  </div>
                </div>
              </div>
              <div className="col-md-12 d-flex justify-content-end pb-2">
                <button
                  type="button"
                  className="btn btn-outline-danger"
                  onClick={() => handleRemoveDayTour(index)}
                >
                  <i className="fas fa-minus-circle"></i>
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>

      <button
        type="button"
        onClick={handleAddDayTour}
        className="btn btn-link text-decoration-none"
      >
        <i className="fas fa-plus-circle"></i> Add Tour
      </button>
    </>
  );
};
export default AddDayTourModel;
