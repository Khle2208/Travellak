import Logo from "../../assets/image/Logo.png";
import { TabHearder, TabHearderLogo } from "./TabsComponent/HeaderItems";
import "./MainNavbar.css";
const MainNavbar = () => {
  return (
    <nav className="main-navbar navbar navbar-expand-lg navbar-dark bg-transparent">
      <div className="container">
        <TabHearderLogo
          className="navbar-brand text-white fs-1"
          Logo={Logo}
        ></TabHearderLogo>
        <TabHearder></TabHearder>
      </div>
    </nav>
  );
};

export default MainNavbar;
