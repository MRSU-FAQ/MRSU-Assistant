import React from 'react';
import SendIcon from "../assets/SendIcon.jsx";
import './ChatInput.css';

const ChatInput = ({ inputText, setInputText, handleSendMessage }) => {
    return (
        <footer className="chat-input-area-wrapper">
            <div className="chat-input-area">
                <input
                    type="text"
                    className="message-input"
                    placeholder="Введите вопрос..."
                    value={inputText}
                    onChange={(e) => setInputText(e.target.value)}
                    onKeyDown={(e) => e.key === 'Enter' && handleSendMessage()}
                />

                <button
                  className="send-button-footer-naked"
                  onClick={handleSendMessage}
                  disabled={!inputText.trim()}
                  aria-label="Send message"
                >
                    <SendIcon/>
                </button>
            </div>
        </footer>
    );
};

export default ChatInput;
