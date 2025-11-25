import React from 'react';
import './ChatHeader.css';

const ChatHeader = ({ onClose }) => {
    return (
        <header className="chat-header">
            <h3 className="header-title">Академический помощник</h3>

            <button className="header-action-btn round-icon" onClick={onClose}>
                {/* Здесь можно добавить иконку закрытия, если нужно */}
            </button>
        </header>
    );
};

export default ChatHeader;