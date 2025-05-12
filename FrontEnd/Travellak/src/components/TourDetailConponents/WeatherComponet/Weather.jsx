// import React, { useEffect, useState } from "react";
// import cityMap from "../../../assets/data/cityMap.json";
// const Weather = ({ city }) => {
//   const [weatherData, setWeatherData] = useState(null);
//   const API_KEY = "a0672e5d75a445eca31162432250105";
//   useEffect(() => {
//     const realCity = cityMap[city] || city;
//     console.log("City being used for weather request:", realCity);
//     fetch(
//       `https://api.weatherapi.com/v1/current.json?key=${API_KEY}&q=${realCity}`
//     )
//       .then((res) => res.json())
//       .then((data) => {
//         console.log(encodeURIComponent(city));
//         setWeatherData(data);
//       })
//       .catch((err) => console.error("Error fetching weather:", err));
//   }, [city]);

//   if (!weatherData) return <p className="text-center">Đang tải thời tiết...</p>;

//   const { location, current } = weatherData;
//   if (!weatherData || !weatherData.location) {
//     return <p className="text-center">Không thể tải thời tiết...</p>;
//   }
//   return (
//     <div
//       className="card shadow-sm p-4 mx-auto mb-3"
//       style={{ maxWidth: "400px", borderRadius: "16px" }}
//     >
//       <h4 className="text-primary text-center mb-3">
//         Thời tiết tại {location.name}, {location.country}
//       </h4>
//       <div className="d-flex align-items-center justify-content-between">
//         <img
//           src={`https:${current.condition.icon}`}
//           alt={current.condition.text}
//           width={64}
//           height={64}
//         />
//         <div className="ms-3">
//           <h2 className="mb-0">{current.temp_c}°C</h2>
//           <small>{current.condition.text}</small>
//         </div>
//       </div>
//       <hr />
//       <div className="row text-center">
//         <div className="col-4">
//           <strong>{current.feelslike_c}°C</strong>
//           <div>Cảm giác</div>
//         </div>
//         <div className="col-4">
//           <strong>{current.humidity}%</strong>
//           <div>Độ ẩm</div>
//         </div>
//         <div className="col-4">
//           <strong>{current.wind_kph} km/h</strong>
//           <div>Gió</div>
//         </div>
//       </div>
//       <p
//         className="text-muted text-end mt-3 mb-0"
//         style={{ fontSize: "0.8rem" }}
//       >
//         Cập nhật lúc: {current.last_updated}
//       </p>
//     </div>
//   );
// };

// import React, { useEffect, useState } from "react";
// import cityMap from "../../../assets/data/cityMap.json";

// const Weather = ({ city }) => {
//   const [weatherData, setWeatherData] = useState(null);
//   const [forecastData, setForecastData] = useState(null);
//   const [locationData, setLocationData] = useState(null);
//   const API_KEY = "a0672e5d75a445eca31162432250105";

//   useEffect(() => {
//     const realCity = cityMap[city] || city;
//     console.log("City being used for weather request:", realCity);
//     fetch(
//       `https://api.weatherapi.com/v1/forecast.json?key=${API_KEY}&q=${realCity}&days=5`
//     )
//       .then((res) => res.json())
//       .then((data) => {
//         console.log("Weather and forecast data:", data);
//         setWeatherData(data.current);
//         setForecastData(data.forecast.forecastday);
//         setLocationData(data.location);
//       })
//       .catch((err) => console.error("Error fetching weather:", err));
//   }, [city]);

//   if (!weatherData || !forecastData || !locationData)
//     return <p className="text-center">Đang tải thời tiết...</p>;

//   return (
//     <div
//       className="card shadow-sm p-4 mx-auto mb-3 mt-5"
//       style={{ borderRadius: "16px" }}
//     >
//       <h4 className="text-primary text-center mb-3">
//         Thời tiết tại {locationData.name}, {locationData.country}
//       </h4>
//       <div className="d-flex align-items-center justify-content-between">
//         <img
//           src={`https:${weatherData.condition.icon}`}
//           alt={weatherData.condition.text}
//           width={64}
//           height={64}
//         />
//         <div className="ms-3">
//           <h2 className="mb-0">{weatherData.temp_c}°C</h2>
//           <small>{weatherData.condition.text}</small>
//         </div>
//       </div>
//       <hr />
//       <div className="row text-center">
//         <div className="col-4">
//           <strong>{weatherData.feelslike_c}°C</strong>
//           <div>Cảm giác</div>
//         </div>
//         <div className="col-4">
//           <strong>{weatherData.humidity}%</strong>
//           <div>Độ ẩm</div>
//         </div>
//         <div className="col-4">
//           <strong>{weatherData.wind_kph} km/h</strong>
//           <div>Gió</div>
//         </div>
//       </div>
//       <p
//         className="text-muted text-end mt-3 mb-0"
//         style={{ fontSize: "0.8rem" }}
//       >
//         Cập nhật lúc: {weatherData.last_updated}
//       </p>

//       <hr />
//       <h5 className="text-center">Dự báo thời tiết 5 ngày tới</h5>
//       <div className="row justify-content-center">
//         {forecastData.map((day, index) => (
//           <div className="col-2" key={index}>
//             <div className="card">
//               <img
//                 src={`https:${day.day.condition.icon}`}
//                 alt={day.day.condition.text}
//                 width={64}
//                 height={64}
//               />
//               <div className="card-body text-center">
//                 <h6>{new Date(day.date).toLocaleDateString()}</h6>
//                 <p>
//                   {day.day.maxtemp_c}°C / {day.day.mintemp_c}°C
//                 </p>
//                 <small>{day.day.condition.text}</small>
//               </div>
//             </div>
//           </div>
//         ))}
//       </div>
//     </div>
//   );
// };

// export default Weather;

// import React, { useEffect, useState } from "react";
// import cityMap from "../../../assets/data/cityMap.json";

// const Weather = ({ city }) => {
//   const [weatherData, setWeatherData] = useState(null);
//   const [forecastData, setForecastData] = useState(null);
//   const [locationData, setLocationData] = useState(null);
//   const API_KEY = "a0672e5d75a445eca31162432250105";

//   useEffect(() => {
//     const realCity = cityMap[city] || city;
//     console.log("City being used for weather request:", realCity);
//     fetch(
//       `https://api.weatherapi.com/v1/forecast.json?key=${API_KEY}&q=${realCity}&days=5`
//     )
//       .then((res) => res.json())
//       .then((data) => {
//         console.log("Weather and forecast data:", data);
//         setWeatherData(data.current);
//         setForecastData(data.forecast.forecastday);
//         setLocationData(data.location);
//       })
//       .catch((err) => console.error("Error fetching weather:", err));
//   }, [city]);

//   if (!weatherData || !forecastData || !locationData)
//     return <p className="text-center">Đang tải thời tiết...</p>;

//   return (
//     <div
//       className="card shadow-sm p-4 mx-auto mb-3 mt-5"
//       style={{ borderRadius: "16px" }}
//     >
//       <h4 className="text-primary text-center mb-3">
//         Thời tiết tại {locationData.name}, {locationData.country}
//       </h4>
//       <div className="d-flex align-items-center justify-content-between">
//         <img
//           src={`https:${weatherData.condition.icon}`}
//           alt={weatherData.condition.text}
//           width={64}
//           height={64}
//         />
//         <div className="ms-3">
//           <h2 className="mb-0">{weatherData.temp_c}°C</h2>
//           <small>{weatherData.condition.text}</small>
//         </div>
//       </div>
//       <hr />
//       <div className="row text-center">
//         <div className="col-4 col-sm-4 col-md-4">
//           <strong>{weatherData.feelslike_c}°C</strong>
//           <div>Cảm giác</div>
//         </div>
//         <div className="col-4 col-sm-4 col-md-4">
//           <strong>{weatherData.humidity}%</strong>
//           <div>Độ ẩm</div>
//         </div>
//         <div className="col-4 col-sm-4 col-md-4">
//           <strong>{weatherData.wind_kph} km/h</strong>
//           <div>Gió</div>
//         </div>
//       </div>
//       <p
//         className="text-muted text-end mt-3 mb-0"
//         style={{ fontSize: "0.8rem" }}
//       >
//         Cập nhật lúc: {weatherData.last_updated}
//       </p>

//       <hr />
//       <h5 className="text-center">Dự báo thời tiết 5 ngày tới</h5>
//       <div className="row justify-content-center">
//         {forecastData.map((day, index) => (
//           <div className="col-12 col-sm-4 col-md-2 mb-3" key={index}>
//             <div className="card">
//               <img
//                 src={`https:${day.day.condition.icon}`}
//                 alt={day.day.condition.text}
//                 width={64}
//                 height={64}
//               />
//               <div className="card-body text-center">
//                 <h6>{new Date(day.date).toLocaleDateString()}</h6>
//                 <p>
//                   {day.day.maxtemp_c}°C / {day.day.mintemp_c}°C
//                 </p>
//                 <small>{day.day.condition.text}</small>
//               </div>
//             </div>
//           </div>
//         ))}
//       </div>
//     </div>
//   );
// };

// export default Weather;
import React, { useEffect, useState } from "react";
import cityMap from "../../../assets/data/cityMap.json";

const Weather = ({ city }) => {
  const [weatherData, setWeatherData] = useState(null);
  const [forecastData, setForecastData] = useState(null);
  const [locationData, setLocationData] = useState(null);
  const API_KEY = "a0672e5d75a445eca31162432250105";

  useEffect(() => {
    const realCity = cityMap[city] || city;
    console.log("City being used for weather request:", realCity);
    fetch(
      `https://api.weatherapi.com/v1/forecast.json?key=${API_KEY}&q=${realCity}&days=5`
    )
      .then((res) => res.json())
      .then((data) => {
        console.log("Weather and forecast data:", data);
        setWeatherData(data.current);
        setForecastData(data.forecast.forecastday);
        setLocationData(data.location);
      })
      .catch((err) => console.error("Error fetching weather:", err));
  }, [city]);

  if (!weatherData || !forecastData || !locationData)
    return <p className="text-center">Đang tải thời tiết...</p>;

  return (
    <div
      className="card shadow-sm p-4 mx-auto mb-3"
      style={{ borderRadius: "16px" }}
    >
      <h4 className="text-primary text-center mb-3">
        Thời tiết tại {locationData.name}, {locationData.country}
      </h4>
      <div className="d-flex align-items-center justify-content-between">
        <img
          src={`https:${weatherData.condition.icon}`}
          alt={weatherData.condition.text}
          width={64}
          height={64}
        />
        <div className="ms-3">
          <h2 className="mb-0">{weatherData.temp_c}°C</h2>
          <small>{weatherData.condition.text}</small>
        </div>
      </div>
      <hr />
      <div className="row text-center">
        <div className="col-4 col-sm-4 col-md-4">
          <strong>{weatherData.feelslike_c}°C</strong>
          <div>Cảm giác</div>
        </div>
        <div className="col-4 col-sm-4 col-md-4">
          <strong>{weatherData.humidity}%</strong>
          <div>Độ ẩm</div>
        </div>
        <div className="col-4 col-sm-4 col-md-4">
          <strong>{weatherData.wind_kph} km/h</strong>
          <div>Gió</div>
        </div>
      </div>
      <p
        className="text-muted text-end mt-3 mb-0"
        style={{ fontSize: "0.8rem" }}
      >
        Cập nhật lúc: {weatherData.last_updated}
      </p>

      <hr />
      <h5 className="text-center">Dự báo thời tiết 5 ngày tới</h5>
      <div className="row justify-content-center">
        {forecastData.map((day, index) => (
          <div className="col-12 col-sm-4 col-md-2 mb-3" key={index}>
            <div className="card d-flex flex-column" style={{ height: "100%" }}>
              <img
                src={`https:${day.day.condition.icon}`}
                alt={day.day.condition.text}
                width={64}
                height={64}
              />
              <div
                className="card-body text-center d-flex flex-column justify-content-between"
                style={{ flex: 1 }}
              >
                <h6>{new Date(day.date).toLocaleDateString()}</h6>
                <p>
                  {day.day.maxtemp_c}°C / {day.day.mintemp_c}°C
                </p>
                <small>{day.day.condition.text}</small>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Weather;
