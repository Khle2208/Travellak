import Logo from "../../assets/image/Logo.png";
import "./Footer.css";
import TabList from "./TabList";
import { TabListData } from "../../assets/data/TabListData";
const Footer = () => {
  return (
    <div id="footer">
      <div className="container-fluid text-center newsletter w-75">
        <div className="row">
          <div className="col-md-6">
            <h2 className="display-4 text-secondary">Our Newsletter</h2>
          </div>
          <div className="col-md-6 text-start">
            <label htmlFor="email" className="sr-onl">
              Email
            </label>

            <form className="form-inline d-flex w-100 gap-2">
              <input
                type="email"
                className="form-control"
                id="email"
                placeholder="Enter your email"
              />
              <button type="submit" className="btn" id="btn-Subscribe">
                Subscribe
              </button>
            </form>
          </div>
        </div>
      </div>

      <footer className="footer">
        <div className="px-5">
          <div className="row text-md-start text-center">
            <div className="col-md-4 mb-4">
              <h2>
                <img src={Logo} alt="" /> Travellak
              </h2>
              <p className="small">
                Copyright © Travellian 2020 All rights reserved
              </p>
            </div>
            {TabListData.map((item) => (
              <div key={item.listName} className="col-md-2 mb-4">
                <TabList listName={item.listName} list={item.list}></TabList>
              </div>
            ))}
            <div className="col-md-2 mb-4 text-md-start text-center fs-3">
              <h4 className="fw-bold">Follow us on</h4>
              <div className="social-icons">
                <a
                  href="https://www.facebook.com/khoa.le.349803"
                  className="px-2"
                >
                  <i className="bi bi-facebook"></i>
                </a>
                <a href="#" className="px-2">
                  <i className="bi bi-pinterest"></i>
                </a>
                <a href="https://www.instagram.com/khoale_kl/" className="px-2">
                  <i className="bi bi-instagram"></i>
                </a>
                <a href="#" className="px-2">
                  <i className="bi bi-twitter"></i>
                </a>
              </div>
            </div>
          </div>
        </div>
      </footer>
    </div>
  );
};
export default Footer;
