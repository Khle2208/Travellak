import TabDashboard from "./TabDashboard";
import { useState, useEffect } from "react";
const Dashboard = () => {
  const [chart, setChart] = useState({});
  useEffect(() => {
    fetch(`http://localhost:8080/Travellak/Admin/GetChart`)
      .then((response) => response.json())
      .then((data) => {
        setChart(data.result);
      })

      .catch((error) => console.error("Error:", error));
  }, []);
  return (
    <div className="dashboard-cards">
      <TabDashboard
        title={"Tổng tour"}
        value={chart.totalTour}
        trend={"+12%"}
        icon={<i className="fas fa-route"></i>}
        className={"card-icon bg-primary"}
      ></TabDashboard>
      <TabDashboard
        title={"Người dùng"}
        value={chart.totalUser}
        trend={"+8%"}
        icon={<i className="fas fa-users"></i>}
        className={"card-icon bg-secondary"}
      ></TabDashboard>
      <TabDashboard
        title={"Đặt Tour"}
        value={chart.totalBooking}
        trend={"+15%"}
        icon={<i className="fas fa-clipboard-check"></i>}
        className={"card-icon bg-success"}
      ></TabDashboard>
      <TabDashboard
        title={"Doanh thu"}
        value={
          parseFloat(chart.totalRevenue / 1_000_000).toLocaleString("vi-VN", {
            maximumFractionDigits: 2,
          }) + "M"
        }
        trend={"+20%"}
        icon={<i className="fas fa-money-bill-wave"></i>}
        className={"card-icon bg-warning"}
      ></TabDashboard>
    </div>
  );
};
export default Dashboard;
