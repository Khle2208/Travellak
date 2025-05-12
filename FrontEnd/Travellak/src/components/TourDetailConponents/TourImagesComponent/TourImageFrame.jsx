import TourImage from "./TourImage";
const TourImageFrame = ({
  handlePrev,
  currentPage,
  sliderWidth,
  translateX,
  tourImages,
  handleVisbleImage,
  handleNext,
  totalPages,
}) => {
  return (
    <div className="d-flex align-items-center position-relative">
      <button
        onClick={handlePrev}
        className="btn btn-dark mx-1 position-absolute start-0 z-3"
        style={{ top: "50%", transform: "translateY(-50%)" }}
        disabled={currentPage === 0}
      >
        <i className="fas fa-chevron-left"></i>
      </button>

      <div className="overflow-hidden w-100 mx-5">
        <div
          className="d-flex"
          style={{
            width: sliderWidth,
            transform: `translateX(${translateX})`,
            transition: "transform 0.5s ease",
          }}
        >
          {tourImages.map((tourImage, index) => (
            <TourImage
              key={index}
              onClick={handleVisbleImage}
              tourImage={tourImage}
              tourImages={tourImages}
            ></TourImage>
          ))}
        </div>
      </div>

      <button
        onClick={handleNext}
        className="btn btn-warning mx-1 position-absolute end-0 z-3"
        style={{ top: "50%", transform: "translateY(-50%)" }}
        disabled={currentPage >= totalPages - 1}
      >
        <i className="fas fa-chevron-right"></i>
      </button>
    </div>
  );
};

export default TourImageFrame;
