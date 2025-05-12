import TabHeaderItems from "./TabHeaderItems";
import { useNavigate } from "react-router-dom";
import { welcome } from "../../../assets/data/WelcomeData";
import { useAuth } from "../../Authentication";
// import "../Header.css";
export const TabHearder = () => {
  const { isAuthenticated, logout, isLoading } = useAuth();
  const navigate = useNavigate();
  const goToLink = (link) => navigate(link);

  if (isLoading) return null;
  return (
    <div
      className="collapse navbar-collapse  justify-content-end "
      id="navbarNav"
    >
      {/* prettier-ignore */}
      <ul className="navbar-nav ml-auto">
        <TabHeaderItems className="nav-link text-white" onClick={() => goToLink("/")}>Home</TabHeaderItems>
        <TabHeaderItems className="nav-link text-white" onClick={() => goToLink("/")}>Explore</TabHeaderItems>
        <TabHeaderItems className="nav-link text-white" onClick={() => goToLink("/")}>Travel</TabHeaderItems>
        <TabHeaderItems className="nav-link text-white" onClick={() => goToLink("/")}>Blog</TabHeaderItems>
        <TabHeaderItems className="nav-link text-white" onClick={() => goToLink("/")}>Pricing</TabHeaderItems>
        {isAuthenticated?(
          <>
          <div className="dropdown nav-item fs-3 w-auto">
            <button className="btn btn-secondary bg-transparent border-0 w-auto" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
            <i className="fa-regular fa-circle-user fs-2"></i>
            </button>
            <ul className="dropdown-menu bg-warning border-0" aria-labelledby="dropdownMenuButton1">
              {/* <li className="d-flex justify-content-center"><TabHeaderItems className="nav-link text-white w-auto"  onClick={() => {goToLink("/Travellak/MyInfo")}}><i class="fa-solid fa-address-card"></i> Profile</TabHeaderItems></li>
              <li className="d-flex justify-content-center"><TabHeaderItems className="nav-link text-white w-auto"  onClick={() => {logout(); goToLink("/Travellak/Login")}}><i class="fa-solid fa-right-from-bracket"></i> Logout</TabHeaderItems></li> */}
              <TabHeaderItems className="nav-link text-white w-auto" onClick={() => {goToLink("/Travellak/MyInfo")}}>
                <i className="fa-solid fa-address-card"></i> Profile
              </TabHeaderItems>
              <TabHeaderItems className="nav-link text-white w-auto" onClick={() => {logout(); goToLink("/Travellak/Login")}}>
                <i className="fa-solid fa-right-from-bracket"></i> Logout
              </TabHeaderItems>
            </ul>
          </div>
      
          </>
        ): (
        <>
        <TabHeaderItems className="nav-link text-white" onClick={() => goToLink("/Travellak/Login")}>Login</TabHeaderItems>
        <TabHeaderItems className="nav-link btn text-white" id="btn-SignUp" onClick={() => goToLink("/Travellak/SignUp")}>Sign up
        </TabHeaderItems>
        </>
          )
        }
      </ul>
    </div>
  );
};

export const TabHearderLogo = ({ Logo, ...props }) => {
  return (
    <>
      <a {...props} href="/">
        <img src={Logo} alt="" /> Travellak
      </a>
      <button
        className="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon"></span>
      </button>
    </>
  );
};

export const Slogan = () => {
  const index = Math.floor(Math.random() * welcome.length);
  return (
    <>
      <div className="hero-content">
        <h1 className="display-4 font-weight-bold">{welcome[index].title}</h1>
        {welcome[index].text}
      </div>
    </>
  );
};
