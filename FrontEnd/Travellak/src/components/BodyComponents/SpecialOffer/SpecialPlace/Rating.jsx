const Rating = ({ starNumber }) => {
  const stars = [];
  for (let i = 0; i < starNumber; i++) {
    stars.push(
      <i key={i} className="fa-solid fa-star" style={{ color: "#FFD43B" }}></i>
    );
  }
  return <p className="card-text">{stars}</p>;
};
export default Rating;
