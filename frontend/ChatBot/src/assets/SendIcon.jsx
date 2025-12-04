import React from 'react';

const SendIcon = ({ width = 35, height = 35, color = "#5B21B6" }) => {
    return (
        <svg
            width={width}
            height={height}
            viewBox="0 0 24 24"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
        >
            <path
                d="M2 21L23 12L2 3L2 8L15 12L2 16L2 21Z"

                fill={color}

                stroke={color}
                strokeWidth="2.5"
                strokeLinecap="round"
                strokeLinejoin="round"
            />
        </svg>
    );
};

export default SendIcon;
