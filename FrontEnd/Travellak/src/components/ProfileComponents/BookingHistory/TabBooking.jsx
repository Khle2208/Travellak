import { useState } from "react";
import BookingDetail from "../../PopupComponents/ConfirmPopup/BookingDetail";
import { handlePayment } from "../../Payment/PaymentUtils";
import ConfirmPopup from "../../PopupComponents/ConfirmPopup/ConfirmPopup";
const TabBooking = ({ booking, onUpdate }) => {
  const [isModalDetailOpen, setIsModalDetailOpen] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const handleDetailClick = () => {
    setIsModalDetailOpen(true);
  };
  const handleDetailClose = () => {
    setIsModalDetailOpen(false);
  };

  const handleContinuePayment = async (totalPrice, bookingId) => {
    await handlePayment(totalPrice, bookingId);
  };

  const handleCancel = async () => {
    const token = localStorage.getItem("token");
    try {
      const response = await fetch(
        `http://localhost:8080/Travellak/Booking/${booking.bookingId}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
          body: JSON.stringify({
            startDate: new Date(booking.startDate).toLocaleDateString("en-GB"),
            endDate: new Date(booking.endDate).toLocaleDateString("en-GB"),
            numberAdult: booking.numberAdult,
            numberChild: booking.numberChild,
            numberBaby: booking.numberBaby,
            tourId: booking.tour.tourId,
            userId: booking.user.userId,
            totalPrice: booking.totalPrice,
            status: "Đã huỷ",
          }),
        }
      );

      console.log("Response:", response);

      if (response.ok) {
        // const data = await response.json();
        // setCurrentBooking(data);
        onUpdate();
        alert("Đã huỷ thành công!");
        // Nếu bạn muốn reload hoặc cập nhật danh sách sau khi huỷ:
        // window.location.reload(); hoặc gọi callback props
      } else {
        const data = await response.json();
        alert(data.message || "Huỷ thất bại!");
      }
    } catch (error) {
      console.error("Error:", error);
      alert("Có lỗi xảy ra!");
    }
  };

  return (
    <>
      {/* <h1> {booking.user.userId} </h1> */}
      <div className="border rounded p-3 h-100">
        <div className="mb-3 order-title fs-3  text-truncate w-100">
          {booking.tour.tourName}
        </div>
        <div className="mb-3">Ngày đặt: {booking.bookingDate}</div>
        <div className="mb-3">Trạng thái: {booking.status}</div>
        <div className="mb-3">
          Tổng tiền: {booking.totalPrice.toLocaleString("vi-VN")} đ
        </div>
        <div className="d-flex gap-4 fs-7 fw-semibold">
          <button
            className="order-link-red d-flex align-items-center gap-1"
            onClick={handleDetailClick}
          >
            Chi tiết <i className="fas fa-chevron-right"></i>
          </button>
          {/* {!(booking.status == "Quá hạn" || booking.status == "Đã huỷ")?(

          <button
            className="order-link-gray d-flex align-items-center gap-1"
            onClick={() =>
              handleContinuePayment(booking.totalPrice, booking.bookingId)
            }
          >
            Thanh toán <i className="fas fa-chevron-right"></i>
          </button>
          )} */}
          {booking.status !== "Quá hạn" && booking.status !== "Đã huỷ" && (
            <button
              className="order-link-gray d-flex align-items-center gap-1"
              onClick={() =>
                handleContinuePayment(booking.totalPrice, booking.bookingId)
              }
            >
              Thanh toán <i className="fas fa-chevron-right"></i>
            </button>
          )}

          {booking.status == "Chờ thanh toán" && (
            <button
              className="order-link-gray d-flex align-items-center gap-1 w-auto btn border-0"
              onClick={() => setIsModalOpen(true)}
            >
              Huỷ <i className="fas fa-chevron-right"></i>
            </button>
          )}
        </div>
      </div>
      {isModalDetailOpen && (
        <BookingDetail
          booking={booking}
          onClose={handleDetailClose}
          onPay={() =>
            handleContinuePayment(booking.totalPrice, booking.bookingId)
          }
        ></BookingDetail>
      )}
      {isModalOpen && (
        <ConfirmPopup
          title={"Xác nhận huỷ"}
          message={"Bạn có muốn huỷ tour này?"}
          onClose={() => setIsModalOpen(false)}
          onConfirm={() => {
            setIsModalOpen(false);
            handleCancel();
          }}
          confirmLabel={"Xác nhận"}
          cancelLabel={"Trở lại"}
        />
      )}
    </>
  );
};

export default TabBooking;
