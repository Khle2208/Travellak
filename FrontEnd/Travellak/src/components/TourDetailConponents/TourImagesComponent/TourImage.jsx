const TourImage = ({ tourImages, tourImage, onClick }) => {
  return (
    <div style={{ width: `${100 / tourImages.length}%` }}>
      <div className="px-2">
        <div className="card">
          <img
            onClick={onClick}
            src={`data:image/jpg;base64,${tourImage.image}`}
            className="card-img-top"
            style={{ height: "200px", objectFit: "cover" }}
          />
        </div>
      </div>
    </div>
  );
};
export default TourImage;
