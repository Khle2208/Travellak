const TicketOrder = ({
  typeTicket,
  desc,
  price,
  ticketNumber,
  setTicketNumber,
}) => {
  const handlePlus = () => {
    setTicketNumber(ticketNumber + 1);
  };
  const formattedPrice = Number(price).toLocaleString("vi-VN");
  const handleMinus = () => {
    if (ticketNumber > 0) {
      setTicketNumber(ticketNumber - 1);
    }
  };
  return (
    <div className="d-flex justify-content-between align-items-center bg-white border border-secondary rounded p-2 mb-2">
      <div>
        <p className="form-label small fw-medium text-secondary mb-0">
          {typeTicket}
        </p>
        <p className="form-label xsmall text-muted mb-0">{desc}</p>
      </div>
      <div className="d-flex align-items-center">
        <p className="form-label small fw-medium text-orange-600 me-2">
          x {formattedPrice} đ
        </p>
        <div className="d-flex align-items-center gap-2">
          <button
            onClick={handleMinus}
            type="button"
            className="btn btn-sm btn-outline-secondary rounded-pill"
          >
            -
          </button>
          <span className="form-label small fw-medium text-secondary">
            {ticketNumber}
          </span>
          <button
            onClick={handlePlus}
            type="button"
            className="btn btn-sm btn-outline-secondary rounded-pill"
          >
            +
          </button>
        </div>
      </div>
    </div>
  );
};

export default TicketOrder;
