import { useEffect } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AOS from "aos";
import "aos/dist/aos.css";
import Header from "./components/HeaderComponents/Header";
import PopularDestinations from "./components/BodyComponents/PopularDestinations/PopularDestinations";
import SpecialOffer from "./components/BodyComponents/SpecialOffer/SpecialOffer";
import OurBlog from "./components/BodyComponents/OurBlog/OurBlog";
import TripPlaner from "./components/BodyComponents/TripPlaner/TripPlaner";
import AboutUs from "./components/BodyComponents/AboutUs/AboutUs";
import Footer from "./components/FooterComponents/Footer";
import MainNavbar from "./components/HeaderComponents/MainNavBar";
function App() {
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

    const handleScroll = () => {
      const navbar = document.querySelector(".main-navbar");
      // Thêm console.log để debug
      const heroSection = document.querySelector(".hero");

      

      const headerHeight = heroSection.offsetHeight;
      console.log(
        `Scroll Y: ${window.scrollY}, Header Height: ${headerHeight}`
      );

      if (window.scrollY > headerHeight * 0.8) {
        // Thử với 80% chiều cao header
        navbar.classList.add("scrolled");
      } else {
        navbar.classList.remove("scrolled");
      }
    };

    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, []);

  return (
    <>
      <MainNavbar />
      <div data-aos="fade-down">
        <Header />
      </div>
      <div data-aos="fade-right">
        <PopularDestinations />
      </div>
      <div data-aos="fade-left">
        <SpecialOffer />
      </div>
      <div data-aos="fade-right">
        <OurBlog />
      </div>
      <div data-aos="fade-up">
        <TripPlaner />
      </div>
      <div data-aos="fade-left">
        <AboutUs />
      </div>
      <div data-aos="fade-down">
        <Footer />
      </div>
    </>
  );
}

export default App;
