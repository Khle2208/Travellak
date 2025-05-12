import "../Info/Profile.css";
import { jwtDecode } from "jwt-decode";
import { useEffect, useState } from "react";
import TabBooking from "./TabBooking";
import ReactPaginate from "react-paginate";

const ITEMS_PER_PAGE = 4;

const BookingHistory = () => {
  const [listBooking, setListBooking] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);

  // useEffect(() => {
  //   const token = localStorage.getItem("token");
  //   const decodedToken = jwtDecode(token);
  //   const userId = decodedToken.userId;

  //   fetch(`http://localhost:8080/Travellak/Booking/MyBooking/${userId}`, {
  //     method: "GET",
  //     headers: {
  //       "Content-Type": "application/json",
  //       Authorization: `Bearer ${token}`,
  //     },
  //   })
  //     .then((response) => response.json())
  //     .then((data) => {
  //       console.log(data.result);
  //       setListBooking(data.result);
  //     })
  //     .catch((error) => console.error("Error:", error));
  // }, []);

  const fetchBookings = async () => {
    const token = localStorage.getItem("token");
    const decodedToken = jwtDecode(token);
    const userId = decodedToken.userId;

    try {
      const response = await fetch(
        `http://localhost:8080/Travellak/Booking/MyBooking/${userId}`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        }
      );
      const data = await response.json();
      setListBooking(data.result);
    } catch (error) {
      console.error("Error:", error);
    }
  };

  useEffect(() => {
    fetchBookings();
  }, []);

  const handlePageClick = (event) => {
    setCurrentPage(event.selected);
  };

  const offset = currentPage * ITEMS_PER_PAGE;
  const currentItems = listBooking.slice(offset, offset + ITEMS_PER_PAGE);
  const pageCount = Math.ceil(listBooking.length / ITEMS_PER_PAGE);

  return (
    <div className="flex-grow-1">
      <h3 className="fw-semibold fs-6 mb-4 text-black">
        Booking History ({listBooking.length})
      </h3>

      <div
        className="d-flex flex-wrap booking-list"
        style={{ gap: "1.5rem" /* tương đương g-4 */ }}
      >
        {currentItems.map((booking, index) => (
          <div key={index} className="booking-item">
            <TabBooking booking={booking} onUpdate={fetchBookings} />
          </div>
        ))}
      </div>

      <div className="mt-2 d-flex justify-content-center">
        <ReactPaginate
          breakLabel="..."
          nextLabel=">"
          onPageChange={handlePageClick}
          pageRangeDisplayed={2}
          pageCount={pageCount}
          previousLabel="<"
          renderOnZeroPageCount={null}
          pageClassName="page-item"
          pageLinkClassName="page-link"
          previousClassName="page-item"
          previousLinkClassName="page-link"
          nextClassName="page-item"
          nextLinkClassName="page-link"
          breakClassName="page-item"
          breakLinkClassName="page-link"
          containerClassName="pagination"
          activeClassName="active"
        />
      </div>
    </div>
  );
};

export default BookingHistory;
