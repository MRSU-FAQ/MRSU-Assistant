import { useState, useEffect } from 'react'; // 1. Добавили useEffect
import { Pencil, Trash2, Plus, Search } from 'lucide-react';
import './Questions.css';

export default function Questions() {
    // 2. Создаем состояние для списка вопросов, индикатора загрузки и ошибок
    const [questions, setQuestions] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    const [isModalOpen, setIsModalOpen] = useState(false);
    const [currentQuestion, setCurrentQuestion] = useState(null);
    const [searchTerm, setSearchTerm] = useState('');

    // 3. Загружаем данные с API при монтировании компонента
    useEffect(() => {
        const fetchQuestions = async () => {
            try {
                const response = await fetch('http://localhost:8080/v1/questions');

                if (!response.ok) {
                    throw new Error('Ошибка при загрузке данных');
                }

                const data = await response.json();
                console.log('ЧТО ПРИШЛО С СЕРВЕРА:', data);
                if (data.content) {
                    setQuestions(data.content);
                } else {
                    setQuestions([]);
                } // Предполагаем, что API возвращает массив объектов
            } catch (err) {
                console.error(err);
                setError('Не удалось загрузить вопросы');
            } finally {
                setIsLoading(false);
            }
        };

        fetchQuestions();
    }, []);

    // 4. Фильтруем данные из стейта questions, а не из статической переменной
    const filteredQuestions = questions.filter(q => {
        // Проверяем наличие полей, чтобы избежать ошибок, если придет null
      const questText = q.question ? q.question.toLowerCase() : '';
        const answerText = q.answer ? q.answer.toLowerCase() : '';
        const search = searchTerm.toLowerCase();

        return questText.includes(search) || answerText.includes(search);
    });

    const handleAddClick = () => {
        setCurrentQuestion(null);
        setIsModalOpen(true);
    };

    const handleEditClick = (question) => {
        setCurrentQuestion(question);
        setIsModalOpen(true);
    };

    const handleCloseModal = () => {
        setIsModalOpen(false);
        setCurrentQuestion(null);
    };

    const handleSave = () => {
        console.log('Сохранено (пока только в консоль):', currentQuestion);
        // Здесь позже будет логика отправки на сервер
        handleCloseModal();
    };

    return (
        <div className="questions">
            <button className="btn-add" onClick={handleAddClick}>
                <Plus size={16} /> Добавить вопрос
            </button>
            <div className="search-box">
                <Search size={16} />
                <input
                    type="text"
                    placeholder="Поиск по вопросу или ответу..."
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                />
            </div>

            {/* Обработка состояний загрузки и ошибки */}
            {isLoading && <div className="status-message">Загрузка вопросов...</div>}
            {error && <div className="status-message error">{error}</div>}

            {!isLoading && !error && (
                <table className="questions-table">
                    <thead>
                    <tr>
                        <th>Вопрос</th>
                        <th>Ответ</th>
                        <th>Действия</th>
                    </tr>
                    </thead>
                    <tbody>
                    {filteredQuestions.length > 0 ? (
                        filteredQuestions.map((question, index) => (
                            // Желательно использовать question.id вместо index, если он есть в API
                            <tr key={question.id || index}>
                                <td>{question.question}</td>
                                <td>{question.answer}</td>
                                <td>
                                    <div className="actions">
                                        <button onClick={() => handleEditClick(question)} title="Редактировать">
                                            <Pencil size={16} />
                                        </button>
                                        <button title="Удалить">
                                            <Trash2 size={16} />
                                        </button>
                                    </div>
                                </td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan="3" style={{ textAlign: 'center' }}>
                                Вопросы не найдены
                            </td>
                        </tr>
                    )}
                    </tbody>
                </table>
            )}

            {isModalOpen && (
                <div className="modal-overlay">
                    <div className="modal-content">
                        <h2>{currentQuestion ? 'Редактирование' : 'Новый вопрос'}</h2>

                        <div className="form-group">
                            <label>Вопрос:</label>
                            <input
                                type="text"
                                // Важно: defaultValue обновляется только при пересоздании компонента,
                                // для управляемых форм лучше использовать value и onChange,
                                // но пока оставим как в вашем примере
                                defaultValue={currentQuestion?.question || ""}
                                placeholder="Введите текст вопроса"
                                className="form-input"
                            />
                        </div>

                        <div className="form-group">
                            <label>Ответ:</label>
                            <textarea
                                defaultValue={currentQuestion?.answer || ""}
                                placeholder="Введите текст ответа"
                                className="form-textarea"
                                rows="6"
                            />
                            <div className="formatting-toolbar">
                                <button><strong>B</strong></button>
                                <button><em>I</em></button>
                                <button>•</button>
                                <button>→</button>
                                <button>🔗</button>
                            </div>
                        </div>

                        <div className="modal-actions">
                            <button className="btn-cancel" onClick={handleCloseModal}>Отмена</button>
                            <button className="btn-save" onClick={handleSave}>
                                {currentQuestion ? 'Сохранить' : 'Создать'}
                            </button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}
