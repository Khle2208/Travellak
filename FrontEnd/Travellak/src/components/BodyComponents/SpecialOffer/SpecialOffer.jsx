import { useState, useEffect } from "react";
import SpecialPlace from "./SpecialPlace/SpecialPlace";
import TitleLeft from "../TitleTopic/TitleLeft";
const SpecialOffer = () => {
  const [currentPage, setCurrentPage] = useState(0);
  const [itemsPerPage, setItemsPerPage] = useState(getItemsPerPage());

  function getItemsPerPage() {
    const width = window.innerWidth;
    if (width < 576) return 1;
    if (width < 992) return 2;
    return 3;
  }
  const [tours, setTours] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/Travellak/Tours") // Gọi API GET
      .then((response) => response.json())
      .then((data) => {
        setTours(data.result);
      })
      .catch((error) => console.error("Error:", error));
  }, []);

  const totalPages = Math.ceil(tours.length / itemsPerPage);

  useEffect(() => {
    const handleResize = () => {
      const newItemsPerPage = getItemsPerPage();
      setItemsPerPage(newItemsPerPage);
      setCurrentPage(0); // Reset lại khi đổi size
    };

    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  const handleNext = () => {
    if (currentPage < totalPages - 1) {
      setCurrentPage(currentPage + 1);
    }
  };

  const handlePrev = () => {
    if (currentPage > 0) {
      setCurrentPage(currentPage - 1);
    }
  };

  return (
    <div className="bg-white">
      <div className="container py-5">
        <TitleLeft
          title="Special Offer"
          content="Check out our special offer and discounts."
          currentPage={currentPage}
          handleNext={handleNext}
          handlePrev={handlePrev}
          totalPages={totalPages}
        ></TitleLeft>
        {/* Slide wrapper */}
        <div className="overflow-hidden">
          <div
            className="d-flex"
            style={{
              width: `${(100 / itemsPerPage) * tours.length}%`,
              transform: `translateX(-${
                (100 / tours.length) * itemsPerPage * currentPage
              }%)`,
              transition: "transform 0.5s ease",
            }}
          >
            {tours?.map((tour) => (
              <div
                key={tour.tourName}
                style={{ width: `${100 / tours.length}%` }}
              >
                <SpecialPlace
                  tourId={tour.tourId}
                  desc={tour.description}
                  star={tour.rating}
                  travelPlace={tour.tourName}
                  placeImage={`data:image/jpg;base64,${tour.imageData}`}
                  price={tour.priceAdult}
                  className="destination px-2"
                />
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default SpecialOffer;
