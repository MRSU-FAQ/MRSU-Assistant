import { useState, useRef, useEffect } from 'react';
import './ChatBot.css';
import ChatHeader from './ChatHeader.jsx';
import MessageRenderer from './MessageRenderer.jsx';
import ChatInput from './ChatInput.jsx';

const ChatBot = ({ isOpen, onClose }) => {
    const [messages, setMessages] = useState([
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
    ]);
    const [inputText, setInputText] = useState('');
    const [isLoading, setIsLoading] = useState(false);

    const messagesEndRef = useRef(null);

    useEffect(() => {
        messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
    }, [messages, isLoading]);

    const sendMessageToApi = async (userQuestion) => {
        try {
            const url = new URL('http://localhost:8080/v1/chat/ask');
            url.searchParams.append('question', userQuestion);

            const response = await fetch(url, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (!response.ok) {
                throw new Error('Ошибка сети или сервера');
            }

            const data = await response.json();

            if (data.content && data.content.length > 0) {
                return data.content[0].answer;
            } else {
                return "Извините, я не нашел ответа на этот вопрос.";
            }

        } catch (error) {
            console.error("Ошибка при запросе к БД:", error);
            return "Извините, произошла ошибка соединения.";
        }
    };

    const handleSendMessage = async () => {
        if (inputText.trim()) {
            const textToSend = inputText;
            const userMsg = { type: 'user', text: textToSend };

            setMessages(prev => [...prev, userMsg]);
            setInputText('');
            setIsLoading(true);

            const botAnswerText = await sendMessageToApi(textToSend);

            const botResponse = { type: 'bot', text: botAnswerText };
            setMessages(prev => [...prev, botResponse]);
            setIsLoading(false);
        }
    };

    const handleSuggestionClick = async (suggestion) => {
        setMessages(prev => [...prev, { type: 'user', text: suggestion }]);
        setIsLoading(true);

        const botAnswerText = await sendMessageToApi(suggestion);

        const botResponse = { type: 'bot', text: botAnswerText };
        setMessages(prev => [...prev, botResponse]);
        setIsLoading(false);
    };

    return (
        <div className={`chat-window ${isOpen ? 'open' : ''}`}>
            <ChatHeader onClose={onClose} />

            <main className="chat-messages-scroll">
                {messages.slice(1).map((msg, index) => (
                    <MessageRenderer
                        key={index}
                        msg={msg}
                        onSuggestionClick={!isLoading ? handleSuggestionClick : undefined}
                    />
                ))}

                {isLoading && <div className="loading-indicator">Печатает...</div>}
                <div ref={messagesEndRef} />
            </main>

            <ChatInput
                inputText={inputText}
                setInputText={setInputText}
                handleSendMessage={handleSendMessage}
                disabled={isLoading}
            />
        </div>
    );
};

export default ChatBot;
