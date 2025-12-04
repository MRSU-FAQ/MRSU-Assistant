import { useState } from 'react'
import {QuestionIcon} from "./assets/QuestionIcon.jsx";
import './App.css'
import {CloseIcon} from "./assets/CloseIcon.jsx";
import ChatBot from "./ChatBot/ChatBot.jsx";

function App() {
    const [isOpen, setIsOpen] = useState(false)
    return (
        <>
            <ChatBot isOpen={isOpen} onClose={() => setIsOpen(false)} />
            <div className={"widgetChat"} onClick={() => setIsOpen(!isOpen)}>
                {isOpen ? (
                    <CloseIcon/>
                ) : (
                    <QuestionIcon/>
                )}
            </div>
        </>
    )
}

export default App
