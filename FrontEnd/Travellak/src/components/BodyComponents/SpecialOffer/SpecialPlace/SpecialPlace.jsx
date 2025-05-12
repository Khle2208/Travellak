import "../SpecialOffer.css";
import { useNavigate } from "react-router-dom";
import Rating from "./Rating";
const SpecialPlace = ({
  tourId,
  placeImage,
  travelPlace,
  desc,
  star,
  price,
  ...props
}) => {
  const navigate = useNavigate();

  const goToDetail = (id) => {
    navigate(`/tour/${id}`);
  };
  return (
    <div {...props}>
      <div className="card" >
        <img
          src={placeImage}
          className="card-img-top"
          alt="Image Place"
          style={{ height: "300px", objectFit: "cover" }}
        />
        <div
          className="d-flex flex-column justify-content-end p-3"
          id="descPlace"
        >
          <h2
            className="card-title text-dark text-truncate"
            style={{ maxWidth: "340px" }}
          >
            {travelPlace}
          </h2>

          <Rating starNumber={star}></Rating>
          <p className="card-text text-dark truncate-5-lines">{desc}</p>
          <div className="d-flex justify-content-between">
            <p>{price.toLocaleString("vi-VN")} đ</p>
            <button
              href="#"
              onClick={() => goToDetail(tourId)}
              className="btn d-flex justify-content-center align-items-center text-white"
              id="btn-Detail"
            >
              Detail
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};
export default SpecialPlace;
