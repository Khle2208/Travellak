const InputTourModel = ({ title, isSelect, typeInput }) => {
  return (
    <div className="row mb-3">
      <label className="mb-1">{title}</label>
      <div className="px-3">
        {isSelect ? (
          <select className="form-control">
            <option value="">Chọn loại tour</option>
            <option value="group">Tour nhóm</option>
            <option value="private">Tour riêng</option>
            <option value="family">Tour gia đình</option>
            <option value="luxury">Tour cao cấp</option>
          </select>
        ) : (
          <input
            type={typeInput}
            name="name"
            //   value={form.name}
            //   onChange={handleChange}
            className="form-control"
            required
          />
        )}
      </div>
    </div>
  );
};
export default InputTourModel;
