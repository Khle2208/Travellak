import React from "react";
import "./Popup.css";

// Utility functions
const formatDate = (dateStr) => {
  if (!dateStr) return "";
  const options = { year: "numeric", month: "long", day: "numeric" };
  return new Date(dateStr).toLocaleDateString(undefined, options);
};

const formatPrice = (price) => {
  if (price == null) return "";
  return price.toLocaleString("vi-VN", {
    style: "currency",
    currency: "VND",
  });
};

const BookingDetail = ({ booking, onClose, onPay }) => {
  return (
    <div className="modal-wrapper my-4">
      <div className="modal-content">
        <button
          type="button"
          className="close-btn btn bo"
          onClick={onClose}
          aria-label="Close"
        >
          &times;
        </button>

        <h1 className="modal-title">Chi tiết đặt tour</h1>

        <div className="text-center mb-3">
          <p className="fs-5">
            <strong>Mã đặt chỗ:</strong> {booking.bookingId}
          </p>
          <p className="fs-5">
            <strong>Trạng thái:</strong> {booking.status}
          </p>
        </div>

        <div className="row section d-flex justify-content-evenly">
          <div className="col-md-6">
            <div className="border border-1 border-grey w-100 mb-2"></div>
            <h3>Thông tin tour</h3>
            <p className="fs-6">
              <strong>Tên tour:</strong> {booking.tour?.tourName}
            </p>
            <p className="fs-6">
              <strong>Điểm xuất phát:</strong>{" "}
              {booking.tour?.departureCity.cityName}
            </p>
            <p className="fs-6">
              <strong>Điểm đến:</strong>{" "}
              {booking.tour?.destinationCity.cityName}
            </p>
          </div>
          <div className="col-md-6">
            <div className="border border-1 border-grey w-100 mb-2"></div>
            <h3>Thời gian</h3>
            <p className="fs-6">
              <strong>Ngày đặt:</strong> {formatDate(booking.bookingDate)}
            </p>
            <p className="fs-6">
              <strong>Bắt đầu:</strong> {formatDate(booking.startDate)}
            </p>
            <p className="fs-6">
              <strong>Kết thúc:</strong> {formatDate(booking.endDate)}
            </p>
          </div>
        </div>

        {/* <div className="section">
          <h3>Thời gian</h3>
          <p className="fs-6">
            <strong>Ngày đặt:</strong> {formatDate(booking.bookingDate)}
          </p>
          <p className="fs-6">
            <strong>Bắt đầu:</strong> {formatDate(booking.startDate)}
          </p>
          <p className="fs-6">
            <strong>Kết thúc:</strong> {formatDate(booking.endDate)}
          </p>
        </div>
        <div className="border border-1 border-grey w-100 mb-2"></div> */}

        <div className="row section d-flex justify-content-evenly">
          <div className="col-md-6">
            <div className="border border-1 border-grey w-100 mb-2"></div>
            <h3>Hành khách</h3>
            <p className="fs-6">
              <strong>Người lớn:</strong> {booking.numberAdult} x{" "}
              {formatPrice(booking.tour.priceAdult)}
            </p>
            <p className="fs-6">
              <strong>Trẻ em:</strong> {booking.numberChild} x{" "}
              {formatPrice(booking.tour.priceChild)}
            </p>
            <p className="fs-6">
              <strong>Em bé:</strong> {booking.numberBaby} x{" "}
              {formatPrice(booking.tour.priceBaby)}
            </p>
          </div>
          <div className="col-md-6">
            <div className="border border-1 border-grey w-100 mb-2"></div>
            <div className="section">
              <h3>Tổng giá</h3>
              <p className="fs-5">
                <strong>{formatPrice(booking.totalPrice)}</strong>
              </p>
            </div>

            <div className="btn-group-custom">
              {booking.status === "Chờ thanh toán" ? (
                <button
                  type="button"
                  className="btn-thanks w-auto"
                  onClick={onPay}
                >
                  Thanh toán
                </button>
              ) : (
                <button type="button" className="btn-thanks w-auto" disabled>
                  {booking.status}
                </button>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default BookingDetail;
