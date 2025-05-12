import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import AOS from "aos";
import "aos/dist/aos.css";
import DayTour from "./DayTour";
import BookingForm from "./BookingFom";
import TourImageFrame from "./TourImagesComponent/TourImageFrame";
import Weather from "./WeatherComponet/Weather";
import TitleRigth from "../BodyComponents/TitleTopic/TitleRight";
const TourDetails = () => {
  const [tour, setTour] = useState({});
  const [tourImages, setTourImages] = useState([]);
  const { id } = useParams();
  const [currentPage, setCurrentPage] = useState(0);
  const [itemsPerPage, setItemsPerPage] = useState(getItemsPerPage());
  const [visibleImage, setVisibleImage] = useState();

  useEffect(() => {
    AOS.init({ duration: 800, once: true, offset: 200 });
    setTimeout(() => AOS.refresh(), 500);

    fetch(`http://localhost:8080/Travellak/Tours/${id}`)
      .then((response) => response.json())
      .then((data) => {
        setTour(data.result);
        const mainImage = { image: data.result.imageData };
        setTourImages([mainImage, ...data.result.tourImages]);
        setVisibleImage(`data:image/png;base64,${data.result.imageData}`);
      })

      .catch((error) => console.error("Error:", error));
  }, [id]);
  useEffect(() => {
    const handleResize = () => {
      setItemsPerPage(getItemsPerPage());
      setCurrentPage(0);
    };
    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  function getItemsPerPage() {
    const width = window.innerWidth;
    if (width < 576) return 1;
    if (width < 992) return 2;
    return 3;
  }

  const totalPages = Math.ceil(tourImages.length / itemsPerPage);

  const handleNext = () => {
    if (currentPage < totalPages - 1) setCurrentPage(currentPage + 1);
  };

  const handlePrev = () => {
    if (currentPage > 0) setCurrentPage(currentPage - 1);
  };

  const handleVisbleImage = (e) => {
    setVisibleImage(e.target.src);
  };

  const sliderWidth = `${(100 / itemsPerPage) * tourImages.length}%`;
  const translateX = `-${
    (100 / tourImages.length) * itemsPerPage * currentPage
  }%`;

  const dayTours = tour.dayTours || [];

  return (
    <div className="container py-4">
      <div className="mx-auto">
        <div className="mb-4" data-aos="fade-down">
          <img
            src={visibleImage}
            className="img-fluid rounded mx-auto w-100"
            style={{ maxHeight: "600px", objectFit: "cover" }}
            alt="Main Tour"
          />
        </div>

        <div
          className="overflow-hidden w-75 mx-auto position-relative"
          data-aos="zoom-in"
        >
          <TourImageFrame
            currentPage={currentPage}
            handleNext={handleNext}
            handlePrev={handlePrev}
            handleVisbleImage={handleVisbleImage}
            sliderWidth={sliderWidth}
            totalPages={totalPages}
            tourImages={tourImages}
            translateX={translateX}
          ></TourImageFrame>
        </div>

        <h1 className="h3 fw-bold mb-3 mt-4" data-aos="fade-right">
          {tour.tourName}
        </h1>
        <div className="border-bottom border-4 border-grey w-25 mb-5"></div>

        <div className="row" data-aos="fade-up">
          <div className="col-md-7">
            <div className="form-control h-auto shadow mb-3 fs-5">
              <p>{tour.description}</p>
            </div>

            {dayTours.map((day) => (
              <DayTour key={day.dayId} day={day} />
            ))}
          </div>

          <div className="col-md-5" data-aos="fade-left">
            {/* <Weather city={tour.destinationCity?.cityName || ""}></Weather> */}

            <div className="sticky-sidebar">
              <BookingForm tour={tour} />
            </div>
          </div>
        </div>
      </div>
      {tour.destinationCity?.cityName && (
        <div className="pt-5" data-aos="fade-right">
          <TitleRigth
            title="Forecast"
            content="Forecast of place."
            nonePage="true"
          ></TitleRigth>
          <Weather city={tour.destinationCity.cityName} />
        </div>
      )}
    </div>
  );
};

export default TourDetails;
