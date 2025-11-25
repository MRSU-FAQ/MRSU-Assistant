import { useState } from 'react';
import './ChatBot.css';
import ChatHeader from './ChatHeader';
import MessageRenderer from './MessageRenderer';
import ChatInput from './ChatInput';

const ChatBot = ({ onClose }) => {
    const [messages, setMessages] = useState([
        { type: 'date', text: 'сегодня' },
        {
            type: 'bot',
            text: "Здравствуйте! Я ваш Академический помощник. Задайте мне вопрос об учебном процессе, и я постараюсь помочь.",
        },
        {
            type: 'suggestions',
            title: "Возможно, вас интересует:",
            items: [
                "Сроки подачи документов",
                "Стоимость обучения",
                "Требуемые экзамены"
            ]
        },
        { type: 'user', text: "Сообщение пользователя" },
    ]);
    const [inputText, setInputText] = useState('');

    const handleSendMessage = () => {
        if (inputText.trim()) {
            const userMsg = { type: 'user', text: inputText };
            setMessages(prev => [...prev, userMsg]);

            const botResponse = { type: 'bot', text: `Вы спросили: "${inputText}". Я постараюсь найти информацию по вашему запросу.` };

            setTimeout(() => {
                setMessages(prev => [...prev, botResponse]);
            }, 500);

            setInputText('');
        }
    };

    const handleSuggestionClick = (suggestion) => {
        setMessages(prev => [...prev, { type: 'user', text: suggestion }]);

        const botResponse = { type: 'bot', text: `По поводу "${suggestion}": это отличный вопрос, вот подробная информация по нему...` };
        setTimeout(() => {
            setMessages(prev => [...prev, botResponse]);
        }, 500);
    };

    const initialDate = messages.length > 0 && messages[0].type === 'date' ? messages[0] : null;

    return (
        <div className="chat-window">
            <ChatHeader onClose={onClose} />

            {initialDate && (
                <div className="message-date-fixed">
                    <span>{initialDate.text}</span>
                </div>
            )}

            <main className="chat-messages-scroll">
                {messages.slice(1).map((msg, index) => (
                    <MessageRenderer
                        key={index}
                        msg={msg}
                        onSuggestionClick={handleSuggestionClick}
                    />
                ))}
            </main>

            <ChatInput
                inputText={inputText}
                setInputText={setInputText}
                handleSendMessage={handleSendMessage}
            />
        </div>
    );
};

export default ChatBot;