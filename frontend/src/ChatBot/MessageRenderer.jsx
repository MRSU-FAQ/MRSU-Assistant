import React from 'react';
// Предполагаем, что общие стили сообщений будут включены в ChatBot.css или MessageRenderer.css
import './MessageRenderer.css'; // Добавьте этот файл для стилей сообщений

const MessageRenderer = ({ msg, onSuggestionClick }) => {
    // Сообщение бота
    if (msg.type === 'bot') {
        return (
            <div className="message-row bot-message">
                <div className="message-bubble bot-bubble">
                    <p className="message-text">{msg.text}</p>
                </div>
            </div>
        );
    }

    // Блок подсказок
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
                        >
                            <span>{item}</span>
                            <span className="suggestion-arrow">&gt;</span>
                        </button>
                    ))}
                </div>
            </div>
        );
    }

    // Сообщение пользователя
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