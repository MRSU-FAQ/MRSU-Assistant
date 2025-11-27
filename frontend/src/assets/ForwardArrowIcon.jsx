export const ForwardArrowIcon = ({ size = 27, className = '' }) => {
    const width = (size / 27) * 30;

    return (
        <svg
            className={className}
            width={width}
            height={size}
            viewBox="0 0 30 27"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
        >
            <defs>
                <linearGradient id="gradientArrowFwd" x1="0%" y1="0%" x2="100%" y2="0%">
                    <stop offset="0%" stopColor="#6731A1" />
                    <stop offset="100%" stopColor="#3D0BAA" />
                </linearGradient>
            </defs>

            <path
                fill="url(#gradientArrowFwd)"
                stroke="none"
                d="M 2.625 0.75 C 1.63731 0.75 0.8125 1.57481 0.8125 2.5625 L 0.8125 24.4375 C 0.8125 25.4252 1.63731 26.25 2.625 26.25 L 26.875 26.25 C 27.8627 26.25 28.6875 25.4252 28.6875 24.4375 L 28.6875 16.5 L 30 13.5 L 28.6875 10.5 L 28.6875 2.5625 C 28.6875 1.57481 27.8627 0.75 26.875 0.75 L 2.625 0.75 Z"
            />

            <path
                fill="white"
                stroke="none"
                d="M17.8182 13.5L12.7273 17V10L17.8182 13.5Z"
            />
        </svg>
    );
};
