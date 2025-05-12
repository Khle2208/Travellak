const InputField = ({ value, fieldName, onChange }) => {
  return (
    <div className="col-12 col-md-12">
      <label
        className="form-label text-black"
        htmlFor="fullname"
        style={{ fontSize: "0.875rem" }}
      >
        {fieldName}
      </label>
      <input
        className="form-control"
        id="txt-email"
        placeholder="Your First Name"
        type="text"
        value={value}
        onChange={onChange}
        readOnly={onChange == null}
      />
    </div>
  );
};

export default InputField;
