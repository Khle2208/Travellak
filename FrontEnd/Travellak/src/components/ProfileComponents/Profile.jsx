import { useEffect } from "react";
import AOS from "aos";
import React from "react";
import MainNavbar from "../HeaderComponents/MainNavBar";
import Footer from "../FooterComponents/Footer";
import InfoForm from "./Info/InfoForm";
import BookingHistory from "./BookingHistory/BookingHistory";
// import { useLocation, useNavigate } from "react-router-dom";
const ProfilePage = () => {
  // const location = useLocation();
  // const navigate = useNavigate();

  // useEffect(() => {
  //   const queryParams = new URLSearchParams(location.search);
  //   const responseCode = queryParams.get("vnp_ResponseCode");

  //   if (responseCode) {
  //     if (responseCode === "00") {
  //       alert("✅ Thanh toán thành công!");
  //     } else if (responseCode === "24") {
  //       alert("❌ Bạn đã hủy thanh toán.");
  //     } else {
  //       alert("⚠️ Thanh toán thất bại! Mã lỗi: " + responseCode);
  //     }
  //     // Xóa query sau khi xử lý
  //     // navigate("/Travellak/MyInfo", { replace: true });
  //     navigate(-2);
  //   }
  // }, [location, navigate]);
  useEffect(() => {
    AOS.init({
      duration: 800,
      once: true,
      offset: 200,
    });

    // Sau khi load trang 300ms thì refresh AOS 1 lần
    setTimeout(() => {
      AOS.refresh();
    }, 500);
    const navbar = document.querySelector(".main-navbar");
    if (navbar) {
      navbar.classList.add("scrolled");
    }
  }, []);

  return (
    <>
      <MainNavbar></MainNavbar>
      <div className="container" data-aos="fade-up">
        <div
          className="row justify-content-center"
          style={{ paddingTop: "120px" }}
        >
          {/* <div className="col-md-6">
            <InfoForm></InfoForm>
          </div>
          <div className="col-md-6">
            <BookingHistory></BookingHistory>
          </div> */}
          <div className="container my-4 rounded shadow-sm bg-white">
            {/* Top Banner */}
            <div className="profile-bg d-flex align-items-center justify-content-between flex-wrap">
              <div className="d-flex align-items-center gap-4 flex-grow-1">
                <div className="position-relative flex-shrink-0">
                  <img
                    alt="Profile photo"
                    className="rounded-circle border border-white"
                    height="96"
                    width="96"
                    src="https://storage.googleapis.com/a1aa/image/8411f075-212b-44b3-1b71-2e3ed6994d27.jpg"
                  />
                  <div className="camera-icon-bg position-absolute bottom-0 end-0">
                    <i
                      className="fas fa-camera text-secondary"
                      style={{ fontSize: "0.75rem" }}
                    ></i>
                  </div>
                </div>
                <div className="d-flex flex-column justify-content-center">
                  <h2 className="mb-1 fw-semibold fs-5 text-black">
                    Mike Edward
                  </h2>
                  <div className="d-flex align-items-center text-secondary fs-7">
                    <i
                      className="fas fa-map-marker-alt me-1"
                      style={{ fontSize: "0.75rem" }}
                    ></i>
                    Lisbon, Portugal
                  </div>
                  <div className="d-flex align-items-center gap-2 mt-2">
                    <span className="badge-foodie">05 Foodie</span>
                    <span className="badge-levelup">
                      (150 Pints to level up)
                    </span>
                  </div>
                </div>
              </div>
              {/* <div className="d-none d-sm-flex gap-5 text-center text-black fw-semibold fs-5 pe-3 flex-shrink-0">
                <div>
                  142
                  <div className="text-secondary fs-7 fw-normal">Reviews</div>
                </div>
                <div>
                  201
                  <div className="text-secondary fs-7 fw-normal">Photos</div>
                </div>
                <div>
                  3.8k
                  <div className="text-secondary fs-7 fw-normal">Followers</div>
                </div>
              </div> */}
            </div>

            {/* Main Content */}
            <div className="d-flex flex-column flex-sm-row bg-white p-4 gap-4 rounded-bottom">
              <InfoForm></InfoForm>

              {/* Order History */}
              <BookingHistory></BookingHistory>
            </div>
          </div>
        </div>
      </div>
      <div data-aos="fade-down">
        <Footer></Footer>
      </div>
    </>
  );
};

export default ProfilePage;
