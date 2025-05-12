import "./BookingForm.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import TicketOrder from "./TicketOrder";
import { jwtDecode } from "jwt-decode";
import ConfirmPopup from "../PopupComponents/ConfirmPopup/ConfirmPopup";
import AnouncePopup from "../PopupComponents/ConfirmPopup/AnouncePopup";
import { handlePayment } from "../Payment/PaymentUtils";
const BookingForm = ({ tour = {} }) => {
  const [ticketAdultNumber, setTicketAdultNumber] = useState(0);
  const [ticketChildNumber, setTicketChildNumber] = useState(0);
  const [ticketBabyNumber, setTicketBabyNumber] = useState(0);
  const [startDate, setStartDate] = useState(new Date());
  const [endDate, setEndDate] = useState(new Date());
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [bookingSuccess, setBookingSuccess] = useState(false);
  const [isModalAnounceOpen, setIsModalAnounceOpen] = useState(false);
  const [hasPickedDate, setHasPickedDate] = useState(false);
  const [anounceMessage, setAnounceMessage] = useState();

  const totalPrice =
    ticketAdultNumber * tour.priceAdult +
    ticketChildNumber * tour.priceChild +
    ticketBabyNumber * tour.priceBaby;
  const endOfYear = new Date();
  endOfYear.setMonth(11); // 11 = December
  endOfYear.setDate(31);

  const handleOnchange = (e) => {
    const selectedDate = new Date(e.target.value);
    setStartDate(selectedDate);
    setHasPickedDate(true);
    const endDate = new Date(selectedDate);
    endDate.setDate(endDate.getDate() + 3);
    setEndDate(endDate);
  };

  const navigate = useNavigate();
  const goToLink = (link) => {
    navigate(link);
  };

  const handleBooking = async () => {
    if (
      ticketAdultNumber + ticketChildNumber + ticketBabyNumber === 0 ||
      !hasPickedDate
    ) {
      setAnounceMessage("Vui lòng nhập thông tin");
      setIsModalAnounceOpen(true);
      return; // dừng luôn, không gọi API
    }

    if (ticketAdultNumber < 1) {
      setIsModalAnounceOpen(true);
      setAnounceMessage("Phải có ít nhất 1 người lớn");
      return; // dừng luôn, không gọi API
    }

    const token = localStorage.getItem("token");
    if (token == null) {
      goToLink("/Travellak/Login");
    } else {
      try {
        const decodedToken = jwtDecode(token);
        const userId = decodedToken.userId;

        const bookingResponse = await fetch(
          `http://localhost:8080/Travellak/Booking`,
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify({
              startDate: startDate.toLocaleDateString("en-GB"),
              endDate: endDate.toLocaleDateString("en-GB"),
              numberAdult: ticketAdultNumber,
              numberChild: ticketChildNumber,
              numberBaby: ticketBabyNumber,
              userId: userId,
              tourId: tour.tourId,
              totalPrice: totalPrice,
            }),
          }
        );

        if (!bookingResponse.ok) {
          // const errorData = await bookingResponse.json();
          // throw new Error(errorData.message || "Booking failed!");
          setIsModalAnounceOpen(true);
          return;
        }

        // Nếu booking thành công
        const bookingData = await bookingResponse.json();
        setBookingSuccess(bookingData);

        setIsModalOpen(true); // 👈 mở modal
      } catch (error) {
        console.error("Error:", error);
        alert(error.message || "An error occurred");
      }
    }
  };

  const handleContinuePayment = async () => {
    await handlePayment(totalPrice, bookingSuccess.result.bookingId);
  };
  return (
    <>
      <div className="d-flex align-items-center justify-content-center">
        <div
          className="bg-warning bg-opacity-25 p-4 rounded shadow-lg"
          style={{ maxWidth: "400px", width: "100%" }}
        >
          <h2 className="h5 fw-bold text-blue-900 mb-3">
            Lịch Trình và Giá Tour
          </h2>
          <p className="form-label small text-muted mb-3">
            Chọn Lịch Trình và Xem Giá:
          </p>
          <div className="row g-2 mb-3">
            <div className="col-md-6">
              <p className="text-nowrap">Ngày khởi hành</p>
              <input
                type="date"
                onChange={handleOnchange}
                className="form-control w-100"
                style={{ colorScheme: "light" }}
                min={new Date().toISOString().split("T")[0]}
                max={endOfYear.toISOString().split("T")[0]}
                onKeyDown={(e) => e.preventDefault()}
              ></input>
            </div>

            <div className="col-md-6">
              <p>Ngày về</p>
              <input
                type="date"
                className="form-control w-100"
                style={{ colorScheme: "light" }}
                value={endDate.toISOString().split("T")[0]}
                readOnly
              ></input>
            </div>
          </div>
          <div className="mb-3">
            <TicketOrder
              setTicketNumber={setTicketAdultNumber}
              ticketNumber={ticketAdultNumber}
              typeTicket="Người lớn"
              desc="&gt; 10 tuổi"
              price={tour.priceAdult}
            ></TicketOrder>
            <TicketOrder
              setTicketNumber={setTicketChildNumber}
              ticketNumber={ticketChildNumber}
              typeTicket="Trẻ em"
              desc="2 - 10 tuổi"
              price={tour.priceChild}
            ></TicketOrder>
            <TicketOrder
              setTicketNumber={setTicketBabyNumber}
              ticketNumber={ticketBabyNumber}
              typeTicket="Trẻ nhỏ"
              desc="&lt; 2 tuổi"
              price={tour.priceBaby}
            ></TicketOrder>
          </div>
          <div className="d-flex align-items-center text-primary small mb-3">
            <i className="fas fa-info-circle me-2"></i>
            <a href="#" className="text-decoration-underline">
              Liên hệ để xác nhận chỗ
            </a>
          </div>
          <div className="d-flex justify-content-between align-items-center mb-3">
            <p className="form-label small fw-medium text-secondary mb-0">
              Tổng Giá Tour
            </p>
            <p className="h6 fw-bold text-orange-600 mb-0">
              {totalPrice.toLocaleString("vi-VN")} đ
            </p>
          </div>
          <button
            className="btn btn-orange btn-lg w-100"
            onClick={handleBooking}
          >
            Yêu cầu đặt
          </button>
        </div>
      </div>
      {isModalOpen && (
        <ConfirmPopup
          title={"Đặt tour thành công!"}
          message={
            "Yêu cầu đặt thành công vui lòng thanh toán để xác nhận giữ chỗ!"
          }
          onClose={() => setIsModalOpen(false)}
          onConfirm={() => {
            setIsModalOpen(false);
            handleContinuePayment();
          }}
          confirmLabel={"Thanh toán ngay"}
          cancelLabel={"Để sau"}
        />
      )}

      {isModalAnounceOpen && (
        <AnouncePopup
          onClose={() => setIsModalAnounceOpen(false)}
          onConfirm={() => {
            setIsModalAnounceOpen(false);
          }}
          message={anounceMessage}
        />
      )}
    </>
  );
};
export default BookingForm;
