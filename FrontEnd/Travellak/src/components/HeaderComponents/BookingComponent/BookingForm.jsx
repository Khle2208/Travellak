import BookingFormItems from "./BookingFormItems";
import { useEffect, useState } from "react";
const BookingForm = () => {
  const [provinces, setProvinces] = useState([]);

  useEffect(() => {
    fetch("https://provinces.open-api.vn/api/p/")
      .then((res) => res.json())
      .then((data) => {
        const provinceNames = data.map((item) => item.name);
        setProvinces(provinceNames);
      })
      .catch((err) => console.error("Failed to fetch provinces:", err));
  }, []);
  return (
    <div className="row justify-content-center">
      <div className="col-md-10">
        <div className="booking-form d-flex flex-column flex-md-row justify-content-between align-items-center">
          <BookingFormItems
            label={"DESTINATION"}
            options={provinces}
            id={"destination"}
          ></BookingFormItems>
          <BookingFormItems
            label={"PERSON"}
            options={[1, 2, 3, 4, 5]}
            id={"person"}
          ></BookingFormItems>
          <BookingFormItems
            label={"CHECK IN"}
            options={[
              "Sun, 17th Sep 2020",
              "Sun, 18th Sep 2020",
              "Sun, 19th Sep 2020",
            ]}
            id={"checkin"}
          ></BookingFormItems>
          <BookingFormItems
            label={"CHECK OUT"}
            options={[
              "Sun, 17th Sep 2020",
              "Sun, 18th Sep 2020",
              "Sun, 19th Sep 2020",
            ]}
            id={"checkout"}
          ></BookingFormItems>
          <button className="btn btn-Booking">Book Now</button>
        </div>
      </div>
    </div>
  );
};
export default BookingForm;
