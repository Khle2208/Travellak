import { useMemo } from "react";

const Chart = ({ data = rawData }) => {
  const points = useMemo(
    () => Object.entries(data).map(([month, value]) => ({ month, value })),
    [data]
  );

  const maxY = Math.max(...points.map((p) => p.value));
  const chartWidth = 800 - 2 * 50;
  const chartHeight = 220 - 20;

  const pathD = points
    .map((p, i) => {
      const x = 50 + (i / (points.length - 1)) * chartWidth;
      const y = 220 - (p.value / maxY) * chartHeight;
      return `${i === 0 ? "M" : "L"}${x},${y}`;
    })
    .join(" ");

  const areaD = pathD + ` L${50 + chartWidth},220 L50,220 Z`;

  return (
    <div className="chart-container">
      <div className="chart-box">
        <div className="chart-header">
          <div className="chart-title">Doanh thu theo tháng</div>
          <div className="chart-options">
            <div className="chart-option active">Hàng tháng</div>
            <div className="chart-option">Hàng quý</div>
            <div className="chart-option">Hàng năm</div>
          </div>
        </div>
        <div className="chart-content">
          <svg width="100%" height="100%" viewBox="0 0 800 250">
            <defs>
              <linearGradient id="gradient" x1="0%" y1="0%" x2="0%" y2="100%">
                <stop offset="0%" stopColor="#FF5A5F" stopOpacity="0.4" />
                <stop offset="100%" stopColor="#FF5A5F" stopOpacity="0.1" />
              </linearGradient>
            </defs>

            {/* --- TRỤC Y và DÒNG KẺ NGANG --- */}
            {[0, 1, 2, 3, 4].map((i) => {
              const yValue = Math.round((maxY / 4) * i);
              const y = 220 - (yValue / maxY) * chartHeight;

              return (
                <g key={i}>
                  <line
                    x1={50}
                    y1={y}
                    x2={50 + chartWidth}
                    y2={y}
                    stroke="#eee"
                    strokeDasharray="4 2"
                  />
                  <text
                    x={45}
                    y={y + 4}
                    textAnchor="end"
                    fontSize="12"
                    fill="#999"
                  >
                    {yValue / 1_000_000 + "tr"}
                  </text>
                </g>
              );
            })}

            {/* đường line */}
            <path d={pathD} stroke="#FF5A5F" strokeWidth="3" fill="none" />

            {/* area dưới line */}
            <path d={areaD} fill="url(#gradient)" />

            {/* điểm data */}
            {points.map((p, i) => {
              const x = 50 + (i / (points.length - 1)) * chartWidth;
              const y = 220 - (p.value / maxY) * chartHeight;
              return <circle key={i} cx={x} cy={y} r="5" fill="#FF5A5F" />;
            })}

            {/* labels trục X */}
            {points.map((p, i) => {
              const x = 50 + (i / (points.length - 1)) * chartWidth;
              return (
                <text
                  key={i}
                  x={x}
                  y={240}
                  textAnchor="middle"
                  fill="#999"
                  fontSize="12"
                >
                  {p.month}
                </text>
              );
            })}
          </svg>
        </div>
      </div>

      {/* --- Biểu đồ tròn giữ nguyên --- */}
      <div className="chart-box">
        <div className="chart-header">
          <div className="chart-title">Tour phổ biến</div>
        </div>
        <div className="chart-content">
          <svg width="100%" height="100%" viewBox="0 0 320 250">
            <circle cx="160" cy="125" r="80" fill="#F9F9F9" />
            <path d="M160,125 L160,45 A80,80 0 0,1 218,83 Z" fill="#FF5A5F" />
            <path d="M160,125 L218,83 A80,80 0 0,1 240,125 Z" fill="#8A9BD4" />
            <path d="M160,125 L240,125 A80,80 0 0,1 160,205 Z" fill="#4CAF50" />
            <path d="M160,125 L160,205 A80,80 0 0,1 80,125 Z" fill="#FF9800" />
            <path d="M160,125 L80,125 A80,80 0 0,1 160,45 Z" fill="#03A9F4" />
            <circle cx="160" cy="125" r="50" fill="white" />

            <rect x="20" y="230" width="12" height="12" fill="#FF5A5F" />
            <text x="37" y="240" fill="#4D4D4D" fontSize="12">
              Seoul (30%)
            </text>

            <rect x="90" y="230" width="12" height="12" fill="#8A9BD4" />
            <text x="107" y="240" fill="#4D4D4D" fontSize="12">
              Jeju (25%)
            </text>

            <rect x="160" y="230" width="12" height="12" fill="#4CAF50" />
            <text x="177" y="240" fill="#4D4D4D" fontSize="12">
              Busan (20%)
            </text>

            <rect x="230" y="230" width="12" height="12" fill="#FF9800" />
            <text x="247" y="240" fill="#4D4D4D" fontSize="12">
              Khác (25%)
            </text>
          </svg>
        </div>
      </div>
    </div>
  );
};

export default Chart;
