import "./Header.css";
import Logo from "../../assets/image/Logo.png";
import {
  Slogan,
  TabHearder,
  TabHearderLogo,
} from "./TabsComponent/HeaderItems";
import BookingForm from "./BookingComponent/BookingForm";

const Header = () => {
  return (
    <div className="header-content">
      <div className="hero">
        <div className="overlay"></div>
        {/* <nav className="container navbar navbar-expand-lg navbar-dark bg-transparent">
          <TabHearderLogo
            className="navbar-brand text-white fs-1"
            Logo={Logo}
          ></TabHearderLogo>
          <TabHearder></TabHearder>
        </nav> */}

        <Slogan></Slogan>
      </div>

      <section className="container my-5">
        <BookingForm></BookingForm>
      </section>
    </div>
  );
};
export default Header;
