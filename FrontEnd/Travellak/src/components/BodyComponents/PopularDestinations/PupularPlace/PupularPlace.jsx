const PupularPlace = ({ placeImage, travelPlace, nation, ...props }) => {
  return (
    <div {...props}>
      <div className="card">
        <img
          src={placeImage}
          className="card-img-top"
          alt="Image Place"
          style={{ height: "550px", objectFit: "cover" }}
        />
        <div
          className="card-img-overlay d-flex flex-column justify-content-end p-3"
          style={{
            background:
              "linear-gradient(to top, rgba(0, 0, 0, 0.7), transparent)",
          }}
        >
          <h5 className="card-title text-white">{travelPlace}</h5>
          <p className="card-text text-white">
            <i className="fas fa-map-marker-alt"></i> {nation}
          </p>
        </div>
      </div>
    </div>
  );
};

export default PupularPlace;
