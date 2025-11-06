export const QuestionIcon = ({ size = 45, color = "white" }) => (
    <svg
        width={size * 0.9}
        height={size}
        viewBox="0 0 24 24"
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
    >
        <path
            d="
        M4 2
        H20
        A2 2 0 0 1 22 4
        V18
        A2 2 0 0 1 20 20
        H16
        Q12 23 8 20
        H4
        A2 2 0 0 1 2 18
        V4
        A2 2 0 0 1 4 2
        Z
      "
            stroke={color}
            strokeWidth="2"
            fill="none"
        />
        <text
            x="12"
            y="15"
            textAnchor="middle"
            fontSize="15"
            fontFamily="Arial, sans-serif"
            fontWeight="bold"
            fill={color}
        >
            ?
        </text>
    </svg>
);