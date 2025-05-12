const TabList = ({ listName, list = [] }) => {
  return (
    <>
      <h4 className="fw-bold">{listName}</h4>
      <ul className="list-unstyled">
        {list.map((item, index) => (
          <li key={index}>
            <a key={index} href={item.link}>
              {item.name}
            </a>
          </li>
        ))}
      </ul>
    </>
  );
};
export default TabList;
