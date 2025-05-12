const BookingFormItems = ({ label, options = [], id }) => {
  return (
    <div class="form-group mb-3 mb-md-0">
      <label for="destination" class="text-muted">
        {label}
      </label>
      <select class="form-control" id={id}>
        {options.map((option, index) => (
          <option key={index}>{option}</option>
        ))}
      </select>
    </div>
  );
};

export default BookingFormItems;
