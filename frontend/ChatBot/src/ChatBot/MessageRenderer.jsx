import React from 'react';
import './MessageRenderer.css';

const MessageRenderer = ({ msg, onSuggestionClick }) => {
    if (msg.type === 'bot') {
        return (
            <div className="message-row bot-message">
                <div className="message-bubble bot-bubble">
                    <p className="message-text">{msg.text}</p>
                </div>
            </div>
        );
    }

    if (msg.type === 'suggestions') {
        return (
            <div className="message-row suggestions-block">
                <p className="suggestions-title">{msg.title}</p>
                <div className="suggestions-list">
                    {msg.items.map((item, idx) => (
                        <button
                            key={idx}
                            className="suggestion-item-button"
                            onClick={() => onSuggestionClick(item)}
                            aria-label={`Select suggestion: ${item}`}
                        >
                            <span>{item}</span>
                            <span className="suggestion-arrow">&gt;</span>
                        </button>
                    ))}
                </div>
            </div>
        );
    }

    if (msg.type === 'user') {
        return (
            <div className="message-row user-message">
                <div className="message-bubble user-bubble">
                    <p className="message-text">{msg.text}</p>
                </div>
            </div>
        );
    }

    return null;
};

export default MessageRenderer;
