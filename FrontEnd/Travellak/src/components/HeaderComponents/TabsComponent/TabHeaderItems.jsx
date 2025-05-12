const TabHeaderItems = ({ onClick, children, ...props }) => {
  return (
    <>
      <li className="nav-item">
        <button type="button" onClick={onClick} {...props}>
          {children}
        </button>
      </li>
    </>
  );
};

export default TabHeaderItems;
