const TabDashboard = ({ title, value, trend, icon, ...props }) => {
  return (
    <div className="card-dashboard">
      <div className="card-header-dashboard">
        <div>
          <div className="card-title">{title}</div>
          <div className="card-value">{value}</div>
          <div className="card-trend">{trend} từ tháng trước</div>
        </div>
        <div {...props}>{icon}</div>
      </div>
    </div>
  );
};

export default TabDashboard;
