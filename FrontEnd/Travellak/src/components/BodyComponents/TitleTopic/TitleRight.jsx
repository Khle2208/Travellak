const TitleRigth = ({
  title,
  content,
  handlePrev,
  currentPage,
  handleNext,
  totalPages,
  nonePage,
}) => {
  return (
    <>
      <div className="text-start mt-5">
        <h1 className="mb-3 d-inline-block border-bottom border-4 border-grey pb-2">
          {title}
        </h1>
      </div>

      <div className="d-flex justify-content-between align-items-center">
        <p className="text-muted mb-5">{content}</p>
        {nonePage ? (
          ""
        ) : (
          <div className="d-flex justify-content-center mb-4">
            <button
              onClick={handlePrev}
              className="btn btn-dark mx-1"
              disabled={currentPage === 0}
            >
              <i className="fas fa-chevron-left"></i>
            </button>
            <button
              onClick={handleNext}
              className="btn btn-warning mx-1"
              disabled={currentPage === totalPages - 1}
            >
              <i className="fas fa-chevron-right"></i>
            </button>
          </div>
        )}
      </div>
    </>
  );
};

export default TitleRigth;
