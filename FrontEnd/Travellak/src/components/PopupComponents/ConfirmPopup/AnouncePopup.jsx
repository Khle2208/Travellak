import React from "react";
import "./Popup.css";

const AnouncePopup = ({ onClose, onConfirm, message }) => {
  return (
    <div className="modal-wrapper">
      <div className="modal-content">
        <button
          type="button"
          className="close-btn btn bo"
          onClick={onClose}
          aria-label="Close"
        >
          &times;
        </button>

        <div className="emoji-circle border-0" aria-hidden="true">
          <img
            src="https://storage.googleapis.com/a1aa/image/e30075fd-924b-4a5a-c7c4-ae59499d7a7d.jpg"
            alt="Party popper emoji"
            className="border-0"
            draggable="false"
          />
        </div>

        <h2 className="modal-title">Thông báo</h2>
        <p className="text-center">{message}</p>

        <div className="btn-group-custom">
          <button type="button" className="btn-thanks" onClick={onConfirm}>
            Xác nhận
          </button>
        </div>
      </div>
    </div>
  );
};

export default AnouncePopup;
