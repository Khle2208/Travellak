import "./Admin.css";
import Sidebar from "./Sidebar";
import Dashboard from "./Dashboard/Dashboard";
import Chart from "./Dashboard/Chart";
import testData from "../../assets/data/testData.json";
import { useState } from "react";
import ManageTour from "./ManageTour/ManageTour";
const AdminPage = () => {
  const [activeSection, setActiveSection] = useState("overview");
  return (
    <div className="container1">
      <Sidebar
        activeSection={activeSection}
        setActiveSection={setActiveSection}
      />
      <div className="main-content">
        <div className="header">
          <div className="search-bar">
            <i className="fas fa-search"></i>
            <input type="text" placeholder="Tìm kiếm..." />
          </div>
          <div className="user-profile">
            <div className="user-avatar">AD</div>
            <div className="user-info">
              <div className="user-name">Admin</div>
            </div>
          </div>
        </div>
        {activeSection === "overview" && (
          <>
            <Dashboard></Dashboard>
            <Chart data={testData}></Chart>
          </>
        )}
        {activeSection === "tour" && (
          <>
            <ManageTour></ManageTour>
          </>
        )}
      </div>
    </div>
  );
};

export default AdminPage;
