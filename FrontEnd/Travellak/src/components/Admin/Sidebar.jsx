
const Sidebar = ({ activeSection, setActiveSection }) => {
  const menuItems = [
    { key: "overview", icon: "fa-chart-line", label: "Tổng quan" },
    { key: "tour", icon: "fa-route", label: "Quản lý Tour" },
    { key: "user", icon: "fa-users", label: "Quản lý Người dùng" },
    { key: "booking", icon: "fa-clipboard-list", label: "Đặt Tour" },
    { key: "revenue", icon: "fa-money-bill-wave", label: "Doanh thu" },
    { key: "review", icon: "fa-comments", label: "Đánh giá" },
    { key: "destination", icon: "fa-map-marker-alt", label: "Điểm đến" },
    { key: "setting", icon: "fa-cog", label: "Cài đặt" },
  ];
  return (
    <div className="sidebar">
      <div className="logo">Travellak Admin</div>
      {menuItems.map((item) => (
        <button
          className={`nav-item btn border-0 ${
            activeSection === item.key ? "active" : ""
          }`}
          key={item.key}
          onClick={() => setActiveSection(item.key)}
        >
          <i className={`fas ${item.icon}`}></i>
          <span>{item.label}</span>
        </button>
      ))}
    </div>
  );
};
export default Sidebar;
