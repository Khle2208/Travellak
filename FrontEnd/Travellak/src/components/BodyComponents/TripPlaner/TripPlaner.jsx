import "./TripPlaner.css";
const TripPlaner = () => {
  return (
    <div className="bg-white mt-5">
      <div className="container py-5">
        <div className="row align-items-center">
          <div className="col-md-4 mb-4 mb-md-0">
            <h1 className="display-4 fw-bold">Trip Planners</h1>
            <div className="custom-divider mb-4"></div>
            <p className="text-muted mb-4">
              20 years from now you will be more disappointed by the things that
              you didn't do. Stop regretting and start travelling, start
              throwing off the bowlines.
            </p>
            <button className="custom-button" id="btn-ViewTrip">
              View all trip plans
            </button>
          </div>
          <div className="col-md-8 d-flex justify-content-between">
            <div className="me-2 pt-5">
              <img
                src="https://storage.googleapis.com/a1aa/image/W_JIsmStrjXQTz-ll2dm7kO8MWNM8Zc3h9lREc8PFtk.jpg"
                className="img-fluid rounded shadow"
                style={{ height: "400px" }}
                alt="Colosseum in Rome"
              />
            </div>
            <div className="me-2">
              <img
                src="https://storage.googleapis.com/a1aa/image/k7yiAhurl3qAXpvngPZUZgWQVvZjs-o4JmI712FPCdA.jpg"
                className="img-fluid rounded shadow mb-3"
                style={{ height: "400px" }}
                alt="Eiffel Tower in Paris"
              />
              <div className="text-center">
                <p className="text-muted small mb-1">GUIDED TOUR</p>
                <p className="fw-bold mb-1">Paris City Tour</p>
                <p className="text-muted small mb-1">€95/Day</p>
                <div className="d-flex justify-content-center mb-2">
                  <i className="fas fa-star text-warning"></i>
                  <i className="fas fa-star text-warning"></i>
                  <i className="fas fa-star text-warning"></i>
                  <i className="fas fa-star text-warning"></i>
                  <i className="fas fa-star text-warning"></i>
                </div>
                <p className="text-muted small">7 Days tour</p>
              </div>
            </div>
            <div className="me-2 pt-5">
              <img
                src="https://storage.googleapis.com/a1aa/image/TbVCbjpwVegzbkDNHpV9sUhoK4guF7hBNT-LOhShRAE.jpg"
                className="img-fluid rounded shadow"
                style={{ height: "400px" }}
                alt="Colosseum in Rome"
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
export default TripPlaner;
