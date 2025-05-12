import { useEffect } from "react";
import "./TourPage.css";
import AOS from "aos";
import MainNavbar from "../HeaderComponents/MainNavBar";
import TourDetails from "./TourDetails";
import SpecialOffer from "../BodyComponents/SpecialOffer/SpecialOffer";
import Footer from "../FooterComponents/Footer";
const TourPage = () => {
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
      <MainNavbar />
      <div className="tour-details-container">
        <TourDetails />
      </div>
      <div data-aos="fade-left">
        <SpecialOffer />
      </div>
      <div data-aos="fade-down">
        <Footer></Footer>
      </div>
    </>
  );
};

export default TourPage;
