import TitleRigth from "../TitleTopic/TitleRight";
import { useState, useEffect } from "react";
import PupularPlace from "./PupularPlace/PupularPlace";
const PopularDestinations = () => {
  const [currentPage, setCurrentPage] = useState(0);
  const [itemsPerPage, setItemsPerPage] = useState(getItemsPerPage());

  function getItemsPerPage() {
    const width = window.innerWidth;
    if (width < 576) return 1; // mobile
    if (width < 992) return 2; // tablet
    return 3; // desktop
  }
  const [attractions, setAttractions] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/Travellak/Attractions") // Gọi API GET
      .then((response) => response.json())
      .then((data) => setAttractions(data.result))
      .catch((error) => console.error("Error:", error));
  }, []);

  const totalPages = Math.ceil(attractions.length / itemsPerPage);

  useEffect(() => {
    const handleResize = () => {
      const newItemsPerPage = getItemsPerPage();
      setItemsPerPage(newItemsPerPage);
      setCurrentPage(0);
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
        <TitleRigth
          title="Popular Destinations"
          content="Most popular destinations around the world, from historical places
            to natural wonders."
          currentPage={currentPage}
          handleNext={handleNext}
          handlePrev={handlePrev}
          totalPages={totalPages}
        ></TitleRigth>

        {/* Slide wrapper */}
        <div className="overflow-hidden">
          <div
            className="d-flex"
            style={{
              width: `${(100 / itemsPerPage) * attractions.length}%`,
              transform: `translateX(-${
                (100 / attractions.length) * itemsPerPage * currentPage
              }%)`,
              transition: "transform 0.5s ease",
            }}
          >
            {attractions.map((place, index) => (
              <div
                key={index}
                style={{ width: `${100 / attractions.length}%` }}
              >
                <PupularPlace
                  nation={place.attractionsName}
                  travelPlace={`${place.city?.cityName}, ${place.city?.country?.countryName}`}
                  placeImage={`data:image/jpg;base64,${place.imageData}`}
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

export default PopularDestinations;
