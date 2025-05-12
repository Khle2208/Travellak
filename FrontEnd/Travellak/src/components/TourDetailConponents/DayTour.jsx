const DayTour = ({ day}) => {
  const collapseId = `night-content-${day.dayNumber}`;
  const iconId = `icon-${collapseId}`;
  return (
    <div class="card mb-3 shadow">
      <div class="card-header d-flex justify-content-between align-items-center">
        <div>
          <h2 class="h5 mb-0">Ngày {day.dayNumber}</h2>
          <p class="mb-0 text-muted">{day.title}</p>
        </div>
        <button
          class="btn btn-link text-decoration-none"
          data-bs-toggle="collapse"
          data-bs-target={`#${collapseId}`}
        >
          <i class="fas fa-chevron-down text-secondary" id={iconId}></i>
        </button>
      </div>
      <div class="collapse" id={collapseId}>
        {" "}
        <div class="card-body">
          <p class="mb-3">{day.description}</p>
          <div className="d-flex justify-content-center">
            <img
              className="w-50"
              src={`data:image/png;base64,${day.attraction?.imageData}`}
              alt=""
            />
          </div>
        </div>
      </div>
    </div>
  );
};
export default DayTour;
